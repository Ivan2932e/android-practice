package ru.mirea.filatov.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {

    private static final String TAG = "MyLooper";
    public Handler handler;
    private Handler mainHandler;

    public MyLooper(Handler mainHandler) {
        this.mainHandler = mainHandler;
    }

    @Override
    public void run() {
        Log.d(TAG, "run: Запуск Looper потока");
        Log.d(TAG, "run: Имя потока - " + currentThread().getName());

        Looper.prepare();

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(TAG, "handleMessage: Получено сообщение");
                Log.d(TAG, "handleMessage: Поток - " + Thread.currentThread().getName());

                Bundle data = msg.getData();
                if (data != null) {
                    String userName = data.getString("USER_NAME", "Не указано");
                    String userJob = data.getString("USER_JOB", "Не указано");
                    int userAge = data.getInt("USER_AGE", 0);

                    Log.d(TAG, "handleMessage: Имя - " + userName);
                    Log.d(TAG, "handleMessage: Возраст - " + userAge);
                    Log.d(TAG, "handleMessage: Работа - " + userJob);

                    try {
                        Thread.sleep(userAge * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String result = String.format(
                            "Обработка завершена!\nИмя: %s\nВозраст: %d лет\nРабота: %s\nЗадержка: %d сек",
                            userName, userAge, userJob, userAge
                    );

                    Message responseMsg = new Message();
                    Bundle responseData = new Bundle();
                    responseData.putString("RESULT", result);
                    responseMsg.setData(responseData);
                    mainHandler.sendMessage(responseMsg);

                    Log.d(TAG, "handleMessage: Результат отправлен");
                }
            }
        };

        Log.d(TAG, "run: Запуск Looper");
        Looper.loop();
        Log.d(TAG, "run: Looper остановлен");
    }

    public void quit() {
        if (handler != null) {
            handler.getLooper().quit();
        }
    }
}