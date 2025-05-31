package ru.mirea.khasanovart.data_thread;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.khasanovart.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textView.setText("Starting tasks...\n\n");

                final Runnable runn1 = new Runnable() {
                    public void run() {
                        binding.textView.append("1. activity.runOnUiThread(Runnable)\n\n" +
                                "• Вызывается из активности\n" +
                                "• Немедленное выполнение в UI-потоке\n\n");
                    }
                };

                final Runnable runn2 = new Runnable() {
                    public void run() {
                        binding.textView.append("2. view.post(Runnable)\n\n" +
                                "• Вызывается у View-элемента\n" +
                                "• Выполняется после текущих задач\n\n");
                    }
                };

                final Runnable runn3 = new Runnable() {
                    public void run() {
                        binding.textView.append("3. view.postDelayed(Runnable, long)\n\n" +
                                "• Выполнение с задержкой\n" +
                                "• Полезен для таймеров\n\n");
                    }
                };

                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        runOnUiThread(runn1);

                        Thread.sleep(1000);
                        binding.textView.post(runn2);

                        binding.textView.postDelayed(runn3, 3000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        });
    }
}