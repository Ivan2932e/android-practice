package com.ivan_filatovia.control_lesson1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private TextView contactName, phoneNumber, emailAddress;
    private Button callButton, messageButton;
    private CheckBox favoriteCheckBox;
    private ImageView contactIcon;


    private EditText editTextMessage;
    private Button button1, button2, button3, button4, button5, button6;


    private boolean showFirstScreen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (showFirstScreen) {
            setContentView(R.layout.activity_main);
            initFirstScreen();
        } else {
            setContentView(R.layout.activity_second);
            initSecondScreen();
        }
    }


    private void initFirstScreen() {
        contactIcon = findViewById(R.id.contactIcon);
        contactName = findViewById(R.id.contactName);
        phoneNumber = findViewById(R.id.phoneNumber);
        emailAddress = findViewById(R.id.emailAddress);
        callButton = findViewById(R.id.callButton);
        messageButton = findViewById(R.id.messageButton);
        favoriteCheckBox = findViewById(R.id.favoriteCheckBox);


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Звоним " + contactName.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Отправляем сообщение на " + phoneNumber.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        favoriteCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favoriteCheckBox.isChecked()) {
                    Toast.makeText(MainActivity.this,
                            "Добавлено в избранное",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Удалено из избранного",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        contactIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Иконка контакта",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(this, "Показываю экран с контактом", Toast.LENGTH_SHORT).show();
    }


    private void initSecondScreen() {

        editTextMessage = findViewById(R.id.editTextMessage);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Нажата кнопка 1",
                        Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Нажата кнопка 2",
                        Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Нажата кнопка 3",
                        Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Нажата кнопка 4",
                        Toast.LENGTH_SHORT).show();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Нажата кнопка 5",
                        Toast.LENGTH_SHORT).show();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Нажата кнопка 6",
                        Toast.LENGTH_SHORT).show();
            }
        });

        editTextMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Текстовое поле: " + editTextMessage.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(this, "Показываю экран с 6 кнопками", Toast.LENGTH_SHORT).show();
    }
}