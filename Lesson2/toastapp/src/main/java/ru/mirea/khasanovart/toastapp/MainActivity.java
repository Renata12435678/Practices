package ru.mirea.khasanovart.toastapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCountClick(View view) {
        EditText inputField = findViewById(R.id.inputField);
        String text = inputField.getText().toString();
        int charCount = text.length();

        String toastMessage = String.format(
                "СТУДЕНТ № 20 ГРУППА БСБО-07-22 Количество символов - %d",
                charCount
        );

        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
    }
}