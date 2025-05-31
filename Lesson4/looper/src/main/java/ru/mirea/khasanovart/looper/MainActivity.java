package ru.mirea.khasanovart.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.khasanovart.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyLooper myLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String result = msg.getData().getString("result");
                Log.d(MainActivity.class.getSimpleName(), "Result: " + result);
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }
        };

        myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = binding.ageInput.getText().toString();
                String job = binding.jobInput.getText().toString();

                if (age.isEmpty() || job.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter age and job", Toast.LENGTH_SHORT).show();
                    return;
                }

                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("KEY", age);
                bundle.putString("JOB", job);
                msg.setData(bundle);

                if (myLooper.mHandler != null) {
                    myLooper.mHandler.sendMessage(msg);
                } else {
                    Toast.makeText(MainActivity.this, "Looper not ready yet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myLooper != null) {
            myLooper.mHandler.getLooper().quit();
        }
    }
}