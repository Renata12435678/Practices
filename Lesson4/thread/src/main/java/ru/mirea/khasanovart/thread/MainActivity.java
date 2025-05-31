package ru.mirea.khasanovart.thread;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.khasanovart.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();
        binding.threadInfo.setText("Текущий поток: " + mainThread.getName());

        mainThread.setName("студентом БСБО-07-22 РТУ МИРЭА, номер в списке 20");
        binding.threadInfo.append("\nВыполняется: " + mainThread.getName());

        binding.calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.totalPairsInput.getText().toString().isEmpty() ||
                        binding.daysInput.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Пожалуйста, введите оба значения!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int totalPairs = Integer.parseInt(binding.totalPairsInput.getText().toString());
                int days = Integer.parseInt(binding.daysInput.getText().toString());

                if (days == 0) {
                    Toast.makeText(MainActivity.this, "Количество дней не может быть 0!", Toast.LENGTH_SHORT).show();
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final double average = (double) totalPairs / days;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.resultText.setText(String.format("Среднее кол-во пар в день: %.2f", average));
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
