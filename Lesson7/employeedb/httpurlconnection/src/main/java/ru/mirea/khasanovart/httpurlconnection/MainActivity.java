package ru.mirea.khasanovart.httpurlconnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView ipTextView, cityTextView, regionTextView, countryTextView, locTextView;
    private TextView weatherTextView, temperatureTextView, windspeedTextView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipTextView = findViewById(R.id.ipTextView);
        cityTextView = findViewById(R.id.cityTextView);
        regionTextView = findViewById(R.id.regionTextView);
        countryTextView = findViewById(R.id.countryTextView);
        locTextView = findViewById(R.id.locTextView);
        weatherTextView = findViewById(R.id.weatherTextView);
        temperatureTextView = findViewById(R.id.temperatureTextView);
        windspeedTextView = findViewById(R.id.windspeedTextView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadPageTask().execute("https://ipinfo.io/json");
            }
        });
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ipTextView.setText("Загрузка...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(MainActivity.class.getSimpleName(), "Response: " + responseJson);

                String ip = responseJson.getString("ip");
                String city = responseJson.getString("city");
                String region = responseJson.getString("region");
                String country = responseJson.getString("country");
                String loc = responseJson.getString("loc");

                ipTextView.setText("IP: " + ip);
                cityTextView.setText("Город: " + city);
                regionTextView.setText("Регион: " + region);
                countryTextView.setText("Страна: " + country);
                locTextView.setText("Координаты: " + loc);

                String[] coordinates = loc.split(",");
                if (coordinates.length == 2) {
                    String latitude = coordinates[0];
                    String longitude = coordinates[1];
                    new DownloadWeatherTask().execute(
                            "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                                    "&longitude=" + longitude + "&current_weather=true");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String downloadIpInfo(String address) throws IOException {
            InputStream inputStream = null;
            String data = "";
            try {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(100000);
                connection.setConnectTimeout(100000);
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setUseCaches(false);
                connection.setDoInput(true);

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int read;
                    while ((read = inputStream.read()) != -1) {
                        bos.write(read);
                    }
                    bos.close();
                    data = bos.toString();
                } else {
                    data = connection.getResponseMessage() + ". Error Code: " + responseCode;
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return data;
        }
    }

    private class DownloadWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadWeatherInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject weatherJson = new JSONObject(result);
                JSONObject currentWeather = weatherJson.getJSONObject("current_weather");

                double temperature = currentWeather.getDouble("temperature");
                double windspeed = currentWeather.getDouble("windspeed");
                int weathercode = currentWeather.getInt("weathercode");

                String weatherDescription = getWeatherDescription(weathercode);

                weatherTextView.setText("Погода: " + weatherDescription);
                temperatureTextView.setText("Температура: " + temperature + "°C");
                windspeedTextView.setText("Скорость ветра: " + windspeed + " км/ч");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String downloadWeatherInfo(String address) throws IOException {
            InputStream inputStream = null;
            String data = "";
            try {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(100000);
                connection.setConnectTimeout(100000);
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setUseCaches(false);
                connection.setDoInput(true);

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    reader.close();
                    data = stringBuilder.toString();
                } else {
                    data = connection.getResponseMessage() + ". Error Code: " + responseCode;
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return data;
        }

        private String getWeatherDescription(int weathercode) {
            switch (weathercode) {
                case 0: return "Ясно";
                case 1: return "Преимущественно ясно";
                case 2: return "Переменная облачность";
                case 3: return "Пасмурно";
                case 45: return "Туман";
                case 48: return "Туман с инеем";
                case 51: return "Морось: слабая";
                case 53: return "Морось: умеренная";
                case 55: return "Морось: сильная";
                case 56: return "Ледяная морось: слабая";
                case 57: return "Ледяная морось: сильная";
                case 61: return "Дождь: слабый";
                case 63: return "Дождь: умеренный";
                case 65: return "Дождь: сильный";
                case 66: return "Ледяной дождь: слабый";
                case 67: return "Ледяной дождь: сильный";
                case 71: return "Снег: слабый";
                case 73: return "Снег: умеренный";
                case 75: return "Снег: сильный";
                case 77: return "Снежные зерна";
                case 80: return "Ливень: слабый";
                case 81: return "Ливень: умеренный";
                case 82: return "Ливень: сильный";
                case 85: return "Снегопад: слабый";
                case 86: return "Снегопад: сильный";
                case 95: return "Гроза: слабая или умеренная";
                case 96: return "Гроза с градом: слабая";
                case 99: return "Гроза с градом: сильная";
                default: return "Неизвестно";
            }
        }
    }
}