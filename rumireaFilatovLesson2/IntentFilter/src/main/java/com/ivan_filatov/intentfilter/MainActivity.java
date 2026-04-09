package com.ivan_filatov.intentfilter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickOpenBrowser(View view) {
        Uri webpage = Uri.parse("https://www.mirea.ru");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        startActivity(intent);
    }


    public void onClickShare(View view) {

        String fullName = "ФИЛАТОВ ИВАН АНДРЕЕВИЧ";
        String university = "МИРЭА";


        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, university);
        shareIntent.putExtra(Intent.EXTRA_TEXT, fullName);


        startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"));
    }
}