package com.ivan_filatov.multiactivity;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        Log.i(TAG, "MainActivity: onCreate()");
    }

    public void onClickSend(View view) {
        String textToSend = editTextInput.getText().toString();


        Intent intent = new Intent(MainActivity.this, SecondActivity.class);


        intent.putExtra("student_info", "МИРЭА - " + textToSend);

        startActivity(intent);
        Log.i(TAG, "MainActivity: Запуск SecondActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MainActivity: onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "MainActivity: onRestart()");
    }
}