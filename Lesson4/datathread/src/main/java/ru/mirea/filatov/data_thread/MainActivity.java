package ru.mirea.filatov.data_thread;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView logTextView;
    private TextView explanationTextView;
    private Button startButton;
    private StringBuilder logBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logTextView = findViewById(R.id.logTextView);
        explanationTextView = findViewById(R.id.explanationTextView);
        startButton = findViewById(R.id.startButton);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logBuilder = new StringBuilder(); // Очищаем лог
                logTextView.setText("");
                startDemonstration();
            }
        });
    }


    private void addLog(String message) {
        logBuilder.append(message).append("\n");
        logTextView.setText(logBuilder.toString());
        Log.d("DataThreadDemo", message);
    }


    private void startDemonstration() {
        addLog("=== НАЧАЛО ДЕМОНСТРАЦИИ ===\n");


        addLog("1. runOnUiThread - запуск из фонового потока");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try { Thread.sleep(1000); } catch (InterruptedException e) {}


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addLog("   runOnUiThread выполнен в потоке: " + Thread.currentThread().getName());
                    }
                });
            }
        }).start();


        addLog("\n2. View.post - запуск из фонового потока");

        new Thread(new Runnable() {
            @Override
            public void run() {

                try { Thread.sleep(2000); } catch (InterruptedException e) {}


                logTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        addLog("   View.post выполнен в потоке: " + Thread.currentThread().getName());
                    }
                });
            }
        }).start();


        addLog("\n3. postDelayed - отложенный запуск");

        logTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                addLog("   View.postDelayed выполнен через 3 секунды");
                addLog("   Поток выполнения: " + Thread.currentThread().getName());


                showExplanation();
            }
        }, 3000);

        addLog("\n=== ОЖИДАНИЕ ВЫПОЛНЕНИЯ ===\n");
    }


    private void showExplanation() {
        StringBuilder explanation = new StringBuilder();
        explanation.append("РАЗЛИЧИЯ МЕТОДОВ:\n\n");
        explanation.append("runOnUiThread(Runnable)                                        \n");

        explanation.append("Это МЕТОД Activity                                           \n");
        explanation.append("Выполняет Runnable в UI потоке                               \n");
        explanation.append("Работает только если Activity активна                       \n");
        explanation.append("Можно вызывать из любого потока                              \n");



        explanation.append("View.post(Runnable)                                            \n");

        explanation.append("Это МЕТОД View                                               \n");
        explanation.append("Выполняет Runnable в UI потоке                               \n");
        explanation.append("Безопасен, даже если View еще не прикреплен к окну           \n");
        explanation.append("Можно вызывать из любого потока                              \n");


;
        explanation.append("View.postDelayed(Runnable, long)                              \n");
        explanation.append("Это МЕТОД View                                               \n");
        explanation.append("То же что и post(), но с ЗАДЕРЖКОЙ                           \n");
        explanation.append("Задержка указывается в миллисекундах                         \n");
        explanation.append("Полезно для анимаций и отложенных действий                   \n");


        explanation.append("ПОСЛЕДОВАТЕЛЬНОСТЬ ЗАПУСКА (согласно задержкам):\n");
        explanation.append("   1. runOnUiThread  → через 1 секунду\n");
        explanation.append("   2. post           → через 2 секунды\n");
        explanation.append("   3. postDelayed    → через 3 секунды\n\n");


        explanationTextView.setText(explanation.toString());
        addLog("\n=== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ===");
    }
}