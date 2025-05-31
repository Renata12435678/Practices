package ru.mirea.khasanovart.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView textViewBook = findViewById(R.id.textViewBook);
            String bookName = extras.getString(MainActivity.BOOK_NAME_KEY);
            String quotes = extras.getString(MainActivity.QUOTES_KEY);
            textViewBook.setText(String.format("Моя любимая книга: %s и цитата %s", bookName, quotes));
        }

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUserBook = findViewById(R.id.etUserBook);
                EditText etUserQuote = findViewById(R.id.etUserQuote);
                String userBook = etUserBook.getText().toString();
                String userQuote = etUserQuote.getText().toString();
                String message = "Название Вашей любимой книги: " + userBook + ". Цитата: " + userQuote;

                Intent data = new Intent();
                data.putExtra(MainActivity.USER_MESSAGE, message);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
}