package ru.mirea.filatov.workmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button startButton;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        statusText = findViewById(R.id.statusText);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWorkManagerTask();
            }
        });
    }

    private void startWorkManagerTask() {
        Log.d(TAG, "startWorkManagerTask: Запуск WorkManager");
        statusText.setText("Статус: Задача запущена...");
        Toast.makeText(this, "WorkManager задача запущена", Toast.LENGTH_SHORT).show();


        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(false)
                .build();


        WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
                .setConstraints(constraints)
                .setInitialDelay(2, TimeUnit.SECONDS)
                .build();


        WorkManager.getInstance(this).enqueue(uploadWorkRequest);

        Log.d(TAG, "startWorkManagerTask: Задача поставлена в очередь");
        Log.d(TAG, "startWorkManagerTask: Критерии: требуется Wi-Fi, задержка 2 сек, работа 10 сек");

        statusText.setText("Статус: Задача в очереди (требуется Wi-Fi, задержка 2 сек)");
    }
}