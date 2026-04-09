package ru.mirea.filatov.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {

    private static final String TAG = "MyLoader";
    private Bundle bundle;

    public MyLoader(@NonNull Context context, Bundle bundle) {
        super(context);
        this.bundle = bundle;
        Log.d(TAG, "MyLoader: Создан");
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(TAG, "onStartLoading: Запуск загрузки");
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        Log.d(TAG, "loadInBackground: Начало работы в фоновом потоке");

        try {

            byte[] encryptedText = bundle.getByteArray("ENCRYPTED_TEXT");
            byte[] keyBytes = bundle.getByteArray("KEY");

            if (encryptedText == null || keyBytes == null) {
                Log.e(TAG, "loadInBackground: Ошибка - данные не получены");
                return "Ошибка: данные не получены";
            }

            Log.d(TAG, "loadInBackground: Получен зашифрованный текст, длина: " + encryptedText.length);
            Log.d(TAG, "loadInBackground: Получен ключ, длина: " + keyBytes.length);


            SecretKey originalKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");


            String decryptedText = decrypt(encryptedText, originalKey);

            Log.d(TAG, "loadInBackground: Дешифрованный текст: " + decryptedText);

            return decryptedText;

        } catch (Exception e) {
            Log.e(TAG, "loadInBackground: Ошибка при дешифровании", e);
            return "Ошибка дешифрования: " + e.getMessage();
        }
    }

    private String decrypt(byte[] encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        return new String(decryptedData);
    }
}