package ru.mirea.filatov.lesson41;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class BackgroundTaskWorker extends Worker {

    private static final String TAG = "BackgroundTaskWorker";

    public BackgroundTaskWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        Log.d(TAG, "BackgroundTaskWorker: Создан");
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: НАЧАЛО ВЫПОЛНЕНИЯ ФОНОВОЙ ЗАДАЧИ");
        Log.d(TAG, "doWork: Имя потока - " + Thread.currentThread().getName());

        try {

            Log.d(TAG, "doWork: Выполняем фоновую задачу...");


            TimeUnit.SECONDS.sleep(5);

            Log.d(TAG, "doWork: ФОНОВАЯ ЗАДАЧА ВЫПОЛНЕНА УСПЕШНО!");

            return Result.success();

        } catch (InterruptedException e) {
            Log.e(TAG, "doWork: Ошибка при выполнении задачи", e);
            return Result.failure();
        }
    }
}
