package ru.mirea.filatov.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class UploadWorker extends Worker {

    private static final String TAG = "UploadWorker";

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        Log.d(TAG, "UploadWorker: Создан");
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: НАЧАЛО РАБОТЫ в фоновом потоке");
        Log.d(TAG, "doWork: Имя потока - " + Thread.currentThread().getName());

        try {
            Log.d(TAG, "doWork: Начинаем задачу...");
            TimeUnit.SECONDS.sleep(10);
            Log.d(TAG, "doWork: Задача выполнена успешно!");

            return Result.success();

        } catch (InterruptedException e) {
            Log.e(TAG, "doWork: Ошибка при выполнении", e);
            return Result.failure();
        }
    }
}