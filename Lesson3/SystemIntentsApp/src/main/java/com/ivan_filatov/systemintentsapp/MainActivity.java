package com.ivan_filatov.systemintentsapp;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCall;
    private Button btnOpenBrowser;
    private Button btnMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCall = findViewById(R.id.btnCall);
        btnOpenBrowser = findViewById(R.id.btnOpenBrowser);
        btnMaps = findViewById(R.id.btnMaps);


        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCall();
            }
        });


        btnOpenBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOpenBrowser();
            }
        });


        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMaps();
            }
        });
    }


    public void onClickCall() {
        try {

            Intent intent = new Intent(Intent.ACTION_DIAL);

            intent.setData(Uri.parse("tel:8988888888"));

            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickOpenBrowser() {
        try {

            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setData(Uri.parse("http://developer.android.com"));

            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickMaps() {
        try {

            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setData(Uri.parse("geo:55.794259, 37.701448"));

            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}