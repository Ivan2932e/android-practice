package com.ivan_filatov.favoritebook;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    private TextView tvDevBook;
    private TextView tvDevQuote;
    private EditText etBookName;
    private EditText etQuote;
    private Button btnSendBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        tvDevBook = findViewById(R.id.tvDevBook);
        tvDevQuote = findViewById(R.id.tvDevQuote);
        etBookName = findViewById(R.id.etBookName);
        etQuote = findViewById(R.id.etQuote);
        btnSendBack = findViewById(R.id.btnSendBack);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String bookName = extras.getString(MainActivity.BOOK_NAME_KEY);
            String quotes = extras.getString(MainActivity.QUOTES_KEY);

            if (bookName != null && quotes != null) {
                tvDevBook.setText("Любимая книга разработчика: " + bookName);
                tvDevQuote.setText("Цитата из книги: " + quotes);
            }
        }

        btnSendBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = etBookName.getText().toString().trim();
                String quote = etQuote.getText().toString().trim();

                if (bookName.isEmpty()) {
                    bookName = "не указано";
                }
                if (quote.isEmpty()) {
                    quote = "не указана";
                }

                String resultMessage = "Название Вашей любимой книги: " + bookName + ". Цитата: " + quote;

                Intent data = new Intent();
                data.putExtra(MainActivity.USER_MESSAGE, resultMessage);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}