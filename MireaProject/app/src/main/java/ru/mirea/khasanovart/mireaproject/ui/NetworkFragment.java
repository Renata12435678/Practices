package ru.mirea.khasanovart.mireaproject.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.mirea.khasanovart.mireaproject.R;

public class NetworkFragment extends Fragment {

    private TextView tvData;
    private ProgressBar progressBar;
    private WeatherApiService weatherApiService;
    private static final String API_KEY = "9d96f8447e680bdd6cc3b39a490e3991";

    public interface WeatherApiService {
        @GET("data/2.5/weather")
        Call<WeatherResponse> getWeather(
                @Query("q") String city,
                @Query("units") String units,
                @Query("appid") String appid
        );
    }

    public static class WeatherResponse {
        Main main;
        Weather[] weather;
        String name;

        static class Main {
            double temp;
            double feels_like;
            int humidity;
        }

        static class Weather {
            String main;
            String description;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network, container, false);

        tvData = view.findViewById(R.id.tv_data);
        progressBar = view.findViewById(R.id.progress_bar);
        Button btnLoad = view.findViewById(R.id.btn_load_data);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApiService = retrofit.create(WeatherApiService.class);

        btnLoad.setOnClickListener(v -> loadWeatherData("Moscow", "metric"));

        return view;
    }

    private void loadWeatherData(String city, String units) {
        progressBar.setVisibility(View.VISIBLE);
        tvData.setText("Загрузка данных о погоде...");

        weatherApiService.getWeather(city, units, API_KEY).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weather = response.body();
                    String weatherText = String.format(
                            "Город: %s\nТемпература: %.1f°C\nОщущается как: %.1f°C\n" +
                                    "Влажность: %d%%\nПогода: %s\nОписание: %s",
                            weather.name,
                            weather.main.temp,
                            weather.main.feels_like,
                            weather.main.humidity,
                            weather.weather[0].main,
                            weather.weather[0].description);
                    tvData.setText(weatherText);
                } else {
                    tvData.setText("Ошибка: " + response.code());
                    Toast.makeText(getContext(), "Не удалось получить данные", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                tvData.setText("Ошибка: " + t.getMessage());
                Toast.makeText(getContext(), "Ошибка сети", Toast.LENGTH_SHORT).show();
            }
        });
    }
}