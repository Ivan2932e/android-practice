package ru.mirea.filatov.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText nameInput, ageInput, jobInput;
    private Button sendButton;
    private TextView statusText, resultText;
    private MyLooper myLooper;
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameInput = findViewById(R.id.nameInput);
        ageInput = findViewById(R.id.ageInput);
        jobInput = findViewById(R.id.jobInput);
        sendButton = findViewById(R.id.sendButton);
        statusText = findViewById(R.id.statusText);
        resultText = findViewById(R.id.resultText);


        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Bundle data = msg.getData();
                if (data != null) {
                    String result = data.getString("RESULT", "");
                    resultText.setText(result);
                    statusText.setText("Статус: Результат получен!");
                    Log.d(TAG, "Результат: " + result);
                }
            }
        };

        myLooper = new MyLooper(mainHandler);
        myLooper.start();


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Looper поток запущен");


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String name = nameInput.getText().toString().trim();
        String ageStr = ageInput.getText().toString().trim();
        String job = jobInput.getText().toString().trim();

        if (name.isEmpty() || ageStr.isEmpty() || job.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageStr);

        if (myLooper.handler == null) {
            Toast.makeText(this, "Ошибка: поток не готов", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "myLooper.handler = null");
            return;
        }


        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("USER_NAME", name);
        bundle.putInt("USER_AGE", age);
        bundle.putString("USER_JOB", job);
        msg.setData(bundle);


        myLooper.handler.sendMessage(msg);
        statusText.setText("Статус: Отправлено...");
        resultText.setText("Обработка... (" + age + " сек)");
        Toast.makeText(this, "Отправлено! Задержка " + age + " сек", Toast.LENGTH_SHORT).show();

        Log.d(TAG, "Отправлено: имя=" + name + ", возраст=" + age + ", работа=" + job);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myLooper != null) {
            myLooper.quit();
        }
    }
}