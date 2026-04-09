package com.ivan_filatov.notificationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "com.mirea.notification.ANDROID";
    private static final int PERMISSION_CODE = 200;
    private static final int NOTIFICATION_ID = 1;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.POST_NOTIFICATIONS)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Разрешения получены");
                createNotificationChannel();
            } else {
                Log.d(TAG, "Нет разрешений!");
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        PERMISSION_CODE);
            }
        } else {
            createNotificationChannel();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Разрешение получено!");
                createNotificationChannel();
            } else {
                Log.d(TAG, "Разрешение отклонено!");
            }
        }
    }

    private void createNotificationChannel() {
        // Создание канала уведомлений (для Android 8+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Мой канал";
            String description = "Описание канала";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    name,
                    importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(
                    NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            Log.d(TAG, "Канал уведомлений создан");
        }
    }

    public void onClickSendNotification(View view) {
        // Проверяем разрешение перед отправкой
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Нет разрешения на уведомления!");
                return;
            }
        }

        // Создание уведомления
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Уведомление от МИРЭА")
                .setContentText("Привет, это тестовое уведомление!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true); // Уведомление исчезнет при нажатии

        // Отправка уведомления
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        try {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
            Log.d(TAG, "Уведомление отправлено");
        } catch (SecurityException e) {
            Log.e(TAG, "Ошибка отправки уведомления: " + e.getMessage());
        }
    }
}