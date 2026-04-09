package ru.mirea.filatov.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.filatov.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ThreadModule";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Thread mainThread = Thread.currentThread();
        Log.d(TAG, "=== Информация о главном потоке ===");
        Log.d(TAG, "Имя потока: " + mainThread.getName());
        Log.d(TAG, "ID потока: " + mainThread.getId());
        Log.d(TAG, "Приоритет: " + mainThread.getPriority());
        Log.d(TAG, "Состояние: " + mainThread.getState());


        mainThread.setName("CustomMainThread");
        Log.d(TAG, "Новое имя потока: " + mainThread.getName());


        binding.threadInfoTextView.setText(
                "Главный поток: " + mainThread.getName() +
                        " (приоритет: " + mainThread.getPriority() + ")"
        );


        binding.calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateInBackground();
            }
        });
    }

    private void calculateInBackground() {

        String pairsStr = binding.totalPairsEditText.getText().toString();
        String daysStr = binding.schoolDaysEditText.getText().toString();

        if (pairsStr.isEmpty() || daysStr.isEmpty()) {
            binding.resultTextView.setText("Пожалуйста, заполните все поля");
            return;
        }

        int totalPairs = Integer.parseInt(pairsStr);
        int schoolDays = Integer.parseInt(daysStr);

        if (schoolDays == 0) {
            binding.resultTextView.setText("Количество учебных дней не может быть 0");
            return;
        }

        final int finalTotalPairs = totalPairs;
        final int finalSchoolDays = schoolDays;


        Runnable calculationRunnable = new Runnable() {
            @Override
            public void run() {
                Thread backgroundThread = Thread.currentThread();
                Log.d(TAG, "=== Информация о фоновом потоке ===");
                Log.d(TAG, "Имя фонового потока: " + backgroundThread.getName());
                Log.d(TAG, "ID фонового потока: " + backgroundThread.getId());


                backgroundThread.setPriority(Thread.MIN_PRIORITY);
                Log.d(TAG, "Приоритет фонового потока после установки: " + backgroundThread.getPriority());


                Log.d(TAG, "Начало вычислений...");


                double averagePairs = (double) finalTotalPairs / finalSchoolDays;


                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "Вычисления завершены. Результат: " + averagePairs);

                final double result = averagePairs;


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String resultText = String.format(
                                "Результат: %.2f пар в день\n(всего %d пар за %d дней)",
                                result, finalTotalPairs, finalSchoolDays
                        );
                        binding.resultTextView.setText(resultText);

                        Log.d(TAG, "UI обновлен с результатом: " + result);
                    }
                });
            }
        };


        Thread backgroundThread = new Thread(calculationRunnable);
        backgroundThread.setName("CalculationThread");
        backgroundThread.start();

        binding.resultTextView.setText("Вычисление... Пожалуйста, подождите");
        Log.d(TAG, "Фоновый поток запущен");
    }
}