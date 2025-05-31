package ru.mirea.khasanovart.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class UploadWorker extends Worker {
    private static final String TAG = "UploadWorker";
    public static final String KEY_INPUT_DATA = "input_data";
    public static final String KEY_RESULT = "result";

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        String inputData = getInputData().getString(KEY_INPUT_DATA);
        Log.d(TAG, "Начало обработки: " + inputData);

        try {
            TimeUnit.SECONDS.sleep(5);
            String result = "Обработанный текст: " + inputData.toUpperCase();
            Data outputData = new Data.Builder()
                    .putString(KEY_RESULT, result)
                    .build();

            Log.d(TAG, "Задача успешно выполнена");
            return Result.success(outputData);
        } catch (InterruptedException e) {
            Log.e(TAG, "Ошибка выполнения задачи", e);
            return Result.failure();
        }
    }
}