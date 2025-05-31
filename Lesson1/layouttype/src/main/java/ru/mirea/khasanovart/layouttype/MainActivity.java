package ru.mirea.khasanovart.layouttype;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton btnUp = findViewById(R.id.btn_up);
        ImageButton btnDown = findViewById(R.id.btn_down);

        btnUp.setOnClickListener(v -> {
            Toast.makeText(this, "Лайк отправлен!", Toast.LENGTH_SHORT).show();
        });

        btnDown.setOnClickListener(v -> {
            Toast.makeText(this, "Дизлайк отправлен!", Toast.LENGTH_SHORT).show();
        });
    }
}