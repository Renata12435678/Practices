package ru.mirea.khasanovart.lesson6;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.mirea.khasanovart.lesson6.R;

public class MainActivity extends AppCompatActivity {
    private EditText groupEditText, numberEditText, movieEditText;
    private Button saveButton;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupEditText = findViewById(R.id.groupEditText);
        numberEditText = findViewById(R.id.numberEditText);
        movieEditText = findViewById(R.id.movieEditText);
        saveButton = findViewById(R.id.saveButton);

        sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
        loadSavedValues();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveValues();
            }
        });
    }

    private void loadSavedValues() {
        String group = sharedPref.getString("GROUP", "");
        int number = sharedPref.getInt("NUMBER", 0);
        String movie = sharedPref.getString("MOVIE", "");

        groupEditText.setText(group);
        numberEditText.setText(String.valueOf(number));
        movieEditText.setText(movie);
    }

    private void saveValues() {
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("GROUP", groupEditText.getText().toString());
        editor.putInt("NUMBER", Integer.parseInt(numberEditText.getText().toString()));
        editor.putString("MOVIE", movieEditText.getText().toString());

        editor.apply();
    }
}