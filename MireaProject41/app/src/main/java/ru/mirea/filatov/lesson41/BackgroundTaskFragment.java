package ru.mirea.filatov.lesson41;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.concurrent.TimeUnit;

public class BackgroundTaskFragment extends Fragment {

    private static final String TAG = "BackgroundTaskFragment";
    private Button startTaskButton;
    private TextView statusText;
    private ConstraintLayout fragmentLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Создание фрагмента");


        fragmentLayout = new ConstraintLayout(getContext());
        fragmentLayout.setPadding(32, 32, 32, 32);


        statusText = new TextView(getContext());
        statusText.setId(View.generateViewId());
        statusText.setText("Ожидание запуска фоновой задачи...");
        statusText.setTextSize(16);
        statusText.setPadding(16, 16, 16, 16);
        statusText.setBackgroundColor(0xFFF0F0F0);


        startTaskButton = new Button(getContext());
        startTaskButton.setId(View.generateViewId());
        startTaskButton.setText("Запустить фоновую задачу");
        startTaskButton.setTextSize(16);


        ConstraintLayout.LayoutParams textParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        textParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        textParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        textParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        textParams.topMargin = 32;
        statusText.setLayoutParams(textParams);


        ConstraintLayout.LayoutParams buttonParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        buttonParams.topToBottom = statusText.getId();
        buttonParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        buttonParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        buttonParams.topMargin = 32;
        startTaskButton.setLayoutParams(buttonParams);


        fragmentLayout.addView(statusText);
        fragmentLayout.addView(startTaskButton);


        startTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBackgroundTask();
            }
        });

        return fragmentLayout;
    }

    private void startBackgroundTask() {
        Log.d(TAG, "startBackgroundTask: Запуск фоновой задачи через WorkManager");

        statusText.setText("Статус: Задача запускается...");
        Toast.makeText(getContext(), "Фоновая задача запущена", Toast.LENGTH_SHORT).show();


        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();


        WorkRequest workRequest = new OneTimeWorkRequest.Builder(BackgroundTaskWorker.class)
                .setConstraints(constraints)
                .setInitialDelay(1, TimeUnit.SECONDS)
                .build();


        WorkManager.getInstance(requireContext()).enqueue(workRequest);

        statusText.setText("Статус: Задача поставлена в очередь\nВыполняется в фоновом потоке...\nРезультат смотри в Logcat");

        Log.d(TAG, "startBackgroundTask: Задача добавлена в WorkManager");
    }
}