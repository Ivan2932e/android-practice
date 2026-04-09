package com.ivan_filatov.toastapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;


    private final String STUDENT_NAME = "Филатов И.А";
    private final String GROUP = "БСБО-54-24";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
    }

    public void onClickCountChars(View view) {
        String text = editTextInput.getText().toString();
        int charCount = text.length();


        String message = "СТУДЕНТ № " + STUDENT_NAME +
                " ГРУППА " + GROUP +
                " Количество символов - " + charCount;

        Toast toast = Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG);
        toast.show();

    }
}