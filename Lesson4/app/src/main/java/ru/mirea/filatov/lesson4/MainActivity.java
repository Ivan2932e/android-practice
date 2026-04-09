package ru.mirea.filatov.lesson4;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.filatov.lesson4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Thread mainThread = Thread.currentThread();
        Log.d(TAG, "Имя главного потока до изменения: " + mainThread.getName());
        Log.d(TAG, "Приоритет главного потока: " + mainThread.getPriority());
        Log.d(TAG, "Поток жив: " + mainThread.isAlive());

        mainThread.setName("MainUIThread");
        Log.d(TAG, "Имя главного потока после изменения: " + mainThread.getName());


        binding.trackTitle.setText("Плеер");


        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClickListener: нажата кнопка Play");
                Toast.makeText(MainActivity.this, "Воспроизведение начато", Toast.LENGTH_SHORT).show();
            }
        });


        binding.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClickListener: нажата кнопка Stop");
                Toast.makeText(MainActivity.this, "Воспроизведение остановлено", Toast.LENGTH_SHORT).show();
            }
        });
    }
}