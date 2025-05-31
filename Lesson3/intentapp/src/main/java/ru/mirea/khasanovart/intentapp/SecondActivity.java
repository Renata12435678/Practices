package ru.mirea.khasanovart.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvTime = findViewById(R.id.tvTime);
        Intent intent = getIntent();
        String time = intent.getStringExtra("time");
        int studentNumber = 20;
        int square = studentNumber * studentNumber;
        String message = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ ЧИСЛО " + square + ", а текущее время " + time;
        tvTime.setText(message);
    }
}