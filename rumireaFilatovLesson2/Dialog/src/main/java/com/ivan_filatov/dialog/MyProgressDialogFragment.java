package com.ivan_filatov.dialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {

    private ProgressDialog progressDialog;
    private Handler handler = new Handler();

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        // Создаем ProgressDialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Загрузка");
        progressDialog.setMessage("Пожалуйста, подождите...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        // Имитируем загрузку
        startLoading();

        return progressDialog;
    }

    private void startLoading() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Загрузка завершена!", Toast.LENGTH_SHORT).show();
                }
            }
        }, 3000); // 3 секунды
    }
}