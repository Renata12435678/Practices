package ru.mirea.khasanovart.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickShowDialog (View view) {
        MyAlertDialog dialogFragment = new MyAlertDialog();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }

    public void showTimeDialog(View view) {
        MyTimerDialog timeDialog = new MyTimerDialog();
        timeDialog.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDateDialog(View view) {
        MyDialogFragment dateDialog = new MyDialogFragment();
        dateDialog.show(getSupportFragmentManager(), "datePicker");
    }

    public void showProgressDialog(View view) {
        MyProgressDialog progressDialog = new MyProgressDialog();
        progressDialog.show(getSupportFragmentManager(), "progressDialog");
    }

}