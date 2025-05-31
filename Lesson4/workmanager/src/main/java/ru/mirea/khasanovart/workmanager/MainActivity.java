package ru.mirea.khasanovart.workmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import ru.mirea.khasanovart.workmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startButton.setOnClickListener(v -> {
            String inputText = binding.inputEditText.getText().toString();

            if (inputText.isEmpty()) {
                Toast.makeText(this, "Введите текст!", Toast.LENGTH_SHORT).show();
                return;
            }
            binding.statusTextView.setText("Статус: Задача запущена...");
            Data inputData = new Data.Builder()
                    .putString(UploadWorker.KEY_INPUT_DATA, inputText)
                    .build();
            WorkRequest uploadWorkRequest =
                    new OneTimeWorkRequest.Builder(UploadWorker.class)
                            .setInputData(inputData)
                            .build();
            WorkManager.getInstance(this)
                    .getWorkInfoByIdLiveData(uploadWorkRequest.getId())
                    .observe(this, workInfo -> {
                        if (workInfo != null) {
                            String status = workInfo.getState().name();
                            binding.statusTextView.append("\n" + status);

                            if (workInfo.getState().isFinished()) {
                                String result = workInfo.getOutputData().getString(UploadWorker.KEY_RESULT);
                                binding.statusTextView.append("\nРезультат: " + result);
                            }
                        }
                    });
            WorkManager.getInstance(this).enqueue(uploadWorkRequest);
        });
    }
}