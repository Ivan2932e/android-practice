package com.ivan_filatovia.buttonclicker;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewHello;
    private Button btnWhoAmI;
    private Button btnItsNotMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewHello = findViewById(R.id.textViewHello);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        btnItsNotMe = findViewById(R.id.btnItsNotMe);


        btnWhoAmI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewHello.setText("Мой номер по списку № 19");


                Toast.makeText(MainActivity.this,
                        "Вы нажали WHO AM I?",
                        Toast.LENGTH_SHORT).show();
            }
        });


        btnItsNotMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewHello.setText("Это не я сделал");


                Toast.makeText(MainActivity.this,
                        "Вы нажали IT'S NOT ME",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}