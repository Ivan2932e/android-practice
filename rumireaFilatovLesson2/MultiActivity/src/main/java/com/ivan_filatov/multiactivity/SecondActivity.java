package com.ivan_filatov.multiactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();
    private TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewDisplay = findViewById(R.id.textViewDisplay);

        // Получение переданных данных
        String receivedText = getIntent().getStringExtra("student_info");
        if (receivedText != null) {
            textViewDisplay.setText(receivedText);
        }

        Log.i(TAG, "SecondActivity: onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "SecondActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "SecondActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "SecondActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "SecondActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "SecondActivity: onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "SecondActivity: onRestart()");
    }
}