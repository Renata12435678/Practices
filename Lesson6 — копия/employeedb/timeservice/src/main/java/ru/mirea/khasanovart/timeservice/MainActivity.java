package ru.mirea.khasanovart.timeservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TimeService";
    private TextView textView, dateTextView, timeTextView;
    private Button button;
    private final String host = "time.nist.gov";
    private final int port = 13;
    private final int timeout = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        dateTextView = findViewById(R.id.dateTextView);
        timeTextView = findViewById(R.id.timeTextView);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            if (isNetworkAvailable()) {
                new TimeRequestTask().execute();
            } else {
                showToast("Нет интернет-соединения");
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class TimeRequestTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            Socket socket = null;

            try {
                Log.d(TAG, "Подключение к " + host + ":" + port);
                socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), timeout);

                BufferedReader reader = SocketUtils.getReader(socket);

                reader.readLine();
                result = reader.readLine();

                Log.d(TAG, "Получены данные: " + result);
            } catch (SocketTimeoutException e) {
                result = "Ошибка: Таймаут подключения";
                Log.e(TAG, result, e);
            } catch (IOException e) {
                result = "Ошибка: " + e.getMessage();
                Log.e(TAG, result, e);
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        Log.e(TAG, "Ошибка при закрытии сокета", e);
                    }
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.startsWith("Ошибка")) {
                textView.setText(result);
                showToast(result);
                return;
            }

            processTimeResponse(result);
        }

        private void processTimeResponse(String timeData) {
            textView.setText("Сервер: " + timeData);

            try {
                String[] parts = timeData.split("\\s+");
                if (parts.length >= 3) {
                    String dateStr = parts[1];
                    String timeStr = parts[2];
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.US);
                    Date date = inputFormat.parse(dateStr + " " + timeStr);
                    dateTextView.setText("Дата: " + new SimpleDateFormat("dd.MM.yyyy", Locale.US).format(date));
                    timeTextView.setText("Время: " + new SimpleDateFormat("HH:mm:ss", Locale.US).format(date));
                } else {
                    throw new ParseException("Неверный формат данных", 0);
                }
            } catch (ParseException e) {
                dateTextView.setText("Ошибка формата даты");
                timeTextView.setText("Ошибка формата времени");
                Log.e(TAG, "Ошибка парсинга", e);
            }
        }
    }
}