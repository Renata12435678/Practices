package ru.mirea.khasanovart.multiactivity;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i(TAG, "onCreate()");
    }

    @Override protected void onStart() { super.onStart(); Log.i(TAG, "onStart()"); }
    @Override protected void onResume() { super.onResume(); Log.i(TAG, "onResume()"); }
    @Override protected void onPause() { super.onPause(); Log.i(TAG, "onPause()"); }
    @Override protected void onStop() { super.onStop(); Log.i(TAG, "onStop()"); }
    @Override protected void onRestart() { super.onRestart(); Log.i(TAG, "onRestart()"); }
    @Override protected void onDestroy() { super.onDestroy(); Log.i(TAG, "onDestroy()"); }
    @Override protected void onSaveInstanceState(Bundle outState) { super.onSaveInstanceState(outState); Log.i(TAG, "onSaveInstanceState()"); }
    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) { super.onRestoreInstanceState(savedInstanceState); Log.i(TAG, "onRestoreInstanceState()"); }
}