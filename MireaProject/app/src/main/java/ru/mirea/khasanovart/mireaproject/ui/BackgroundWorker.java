package ru.mirea.khasanovart.mireaproject.ui;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BackgroundWorker extends Worker {
    private static final String TAG = "BackgroundWorker";

    public BackgroundWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        try {
            for (int i = 0; i < 5; i++) {
                Log.d(TAG, "Выполняется фоновая работа: " + i);
                Thread.sleep(1000);
            }
            return Result.success();
        } catch (InterruptedException e) {
            return Result.failure();
        }
    }
}