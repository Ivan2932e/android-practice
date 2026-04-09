package com.ivan_filatov.favoritebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String BOOK_NAME_KEY = "book_name";
    public static final String QUOTES_KEY = "quotes";
    public static final String USER_MESSAGE = "user_message";

    private TextView tvBookInfo;
    private Button btnOpenInput;

    private ActivityResultLauncher<Intent> getInfoAboutBookLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBookInfo = findViewById(R.id.tvBookInfo);
        btnOpenInput = findViewById(R.id.btnOpenInput);


        getInfoAboutBookLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            String userMessage = data.getStringExtra(USER_MESSAGE);

                            if (userMessage != null && !userMessage.isEmpty()) {
                                tvBookInfo.setText(userMessage);
                            }
                        }
                    }
                }
        );

        btnOpenInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShareActivity.class);
                intent.putExtra(BOOK_NAME_KEY, "451 градус по Фарингейту");
                intent.putExtra(QUOTES_KEY, "У людей теперь нет времени друг для друга.");
                getInfoAboutBookLauncher.launch(intent);
            }
        });
    }
}