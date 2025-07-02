package ru.mirea.khasanovart.employeedb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class EmployeeDBActivity extends AppCompatActivity {
    private EditText nameEditText, powerEditText, levelEditText;
    private Button addButton, viewAllButton;
    private TextView resultTextView;
    private AppDatabase db;
    private EmployeeDao employeeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_db);

        nameEditText = findViewById(R.id.nameEditText);
        powerEditText = findViewById(R.id.powerEditText);
        levelEditText = findViewById(R.id.levelEditText);
        addButton = findViewById(R.id.addButton);
        viewAllButton = findViewById(R.id.viewAllButton);
        resultTextView = findViewById(R.id.resultTextView);

        db = App.getInstance().getDatabase();
        employeeDao = db.employeeDao();

        // Добавление начальных данных
        if (employeeDao.getAll().isEmpty()) {
            employeeDao.insert(new Employee("Супермен", "Суперсила, полет", 95));
            employeeDao.insert(new Employee("Бэтмен", "Гениальный интеллект", 90));
            employeeDao.insert(new Employee("Чудо-женщина", "Суперсила, ловкость", 92));
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllEmployees();
            }
        });
    }

    private void addEmployee() {
        String name = nameEditText.getText().toString();
        String power = powerEditText.getText().toString();
        String levelStr = levelEditText.getText().toString();

        if (name.isEmpty() || power.isEmpty() || levelStr.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        int level = Integer.parseInt(levelStr);
        if (level < 1 || level > 100) {
            Toast.makeText(this, "Уровень должен быть от 1 до 100", Toast.LENGTH_SHORT).show();
            return;
        }

        Employee employee = new Employee(name, power, level);
        employeeDao.insert(employee);

        Toast.makeText(this, "Супергерой добавлен", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void showAllEmployees() {
        List<Employee> employees = employeeDao.getAll();
        StringBuilder sb = new StringBuilder();

        if (employees.isEmpty()) {
            sb.append("Нет супергероев в базе");
        } else {
            sb.append("Список супергероев:\n\n");
            for (Employee employee : employees) {
                sb.append(employee.toString()).append("\n\n");
            }
        }

        resultTextView.setText(sb.toString());
    }

    private void clearFields() {
        nameEditText.setText("");
        powerEditText.setText("");
        levelEditText.setText("");
    }
}