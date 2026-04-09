package ru.mirea.filatov.lesson41;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button showFragmentButton;
    private boolean isFragmentShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragmentButton = findViewById(R.id.showFragmentButton);

        showFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFragmentShown) {
                    showBackgroundTaskFragment();
                } else {
                    hideBackgroundTaskFragment();
                }
            }
        });
    }

    private void showBackgroundTaskFragment() {
        Log.d(TAG, "showBackgroundTaskFragment: Показ фрагмента");

        BackgroundTaskFragment fragment = new BackgroundTaskFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        isFragmentShown = true;
        showFragmentButton.setText("Скрыть фрагмент");
    }

    private void hideBackgroundTaskFragment() {
        Log.d(TAG, "hideBackgroundTaskFragment: Скрытие фрагмента");

        BackgroundTaskFragment fragment = (BackgroundTaskFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
        }

        isFragmentShown = false;
        showFragmentButton.setText("Показать фрагмент");
    }
}