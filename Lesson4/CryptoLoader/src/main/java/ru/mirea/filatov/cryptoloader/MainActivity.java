package ru.mirea.filatov.cryptoloader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = "MainActivity";
    private static final int LOADER_ID = 1;

    private EditText inputText;
    private Button encryptButton;
    private TextView resultText;

    private SecretKey secretKey;
    private byte[] encryptedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        encryptButton = findViewById(R.id.encryptButton);
        resultText = findViewById(R.id.resultText);


        generateKey();

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptAndSendToLoader();
            }
        });
    }


    private void generateKey() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("mirea_seed_data".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(256, sr);
            secretKey = new SecretKeySpec(kg.generateKey().getEncoded(), "AES");
            Log.d(TAG, "generateKey: Ключ сгенерирован");
        } catch (Exception e) {
            Log.e(TAG, "generateKey: Ошибка", e);
            Toast.makeText(this, "Ошибка генерации ключа", Toast.LENGTH_SHORT).show();
        }
    }

    private byte[] encryptText(String message) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(message.getBytes());
            Log.d(TAG, "encryptText: Текст зашифрован, длина: " + encrypted.length);
            return encrypted;
        } catch (Exception e) {
            Log.e(TAG, "encryptText: Ошибка шифрования", e);
            return null;
        }
    }


    private void encryptAndSendToLoader() {
        String text = inputText.getText().toString().trim();

        if (text.isEmpty()) {
            Toast.makeText(this, "Введите текст для шифрования", Toast.LENGTH_SHORT).show();
            return;
        }


        encryptedData = encryptText(text);

        if (encryptedData == null) {
            Toast.makeText(this, "Ошибка шифрования", Toast.LENGTH_SHORT).show();
            return;
        }


        Bundle bundle = new Bundle();
        bundle.putByteArray("ENCRYPTED_TEXT", encryptedData);
        bundle.putByteArray("KEY", secretKey.getEncoded());

        resultText.setText("Шифрование выполнено. Отправка в Loader...");


        LoaderManager.getInstance(this).restartLoader(LOADER_ID, bundle, this);

        Log.d(TAG, "encryptAndSendToLoader: Данные отправлены в Loader");
    }



    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d(TAG, "onCreateLoader: Создание Loader, id=" + id);
        Toast.makeText(this, "Loader создан, начинается дешифрование", Toast.LENGTH_SHORT).show();
        return new MyLoader(this, args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d(TAG, "onLoadFinished: Загрузка завершена, результат: " + data);


        Toast.makeText(this, "Дешифрованная фраза: " + data, Toast.LENGTH_LONG).show();


        resultText.setText("Дешифровано: " + data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset: Сброс Loader");
    }
}