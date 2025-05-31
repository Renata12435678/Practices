package ru.mirea.khasanovart.intentfilter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBrowser(View view) {
        try {
            Uri webpage = Uri.parse("https://www.mirea.ru");
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Не удалось открыть браузер", Toast.LENGTH_SHORT).show();
        }
    }

    public void shareData(View view) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");

            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA - Российский технологический университет");

            String shareText = "Студент: Хасанова Рената Темировна\n" +
                    "Группа: БСБО-07-22\n" +
                    "Университет: РТУ МИРЭА";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

            startActivity(Intent.createChooser(shareIntent, "Поделиться данными"));
        } catch (Exception e) {
            Toast.makeText(this, "Не удалось открыть приложения для отправки", Toast.LENGTH_SHORT).show();
        }
    }
}