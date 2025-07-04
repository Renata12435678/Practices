package ru.mirea.khasanovart.mireaproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister;
    private TextView tvToggleAuth;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private boolean isLoginMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: Activity created");
        FirebaseAuth.getInstance().signOut();

        initFirebase();
        initViews();
        setupAuthToggle();
        setupButtons();
    }

    private void initFirebase() {
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG, "FireAuth initialized");
    }

    private void initViews() {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        tvToggleAuth = findViewById(R.id.tv_toggle_auth);
        progressBar = findViewById(R.id.progress_bar);
        Log.d(TAG, "Views initialized");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Checking current user");

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Log.d(TAG, "Current user: " + currentUser.getEmail() +
                    ", verified: " + currentUser.isEmailVerified());

            if (currentUser.isEmailVerified()) {
                startMainActivity();
            } else {
                showToast("Подтвердите email и войдите снова");
                mAuth.signOut();
            }
        } else {
            Log.d(TAG, "No current user found");
        }
    }

    private void setupAuthToggle() {
        tvToggleAuth.setOnClickListener(v -> {
            isLoginMode = !isLoginMode;
            updateAuthModeUI();
            Log.d(TAG, "Auth mode toggled: " + (isLoginMode ? "Login" : "Register"));
        });
        updateAuthModeUI();
    }

    private void updateAuthModeUI() {
        btnLogin.setVisibility(isLoginMode ? View.VISIBLE : View.GONE);
        btnRegister.setVisibility(isLoginMode ? View.GONE : View.VISIBLE);
        tvToggleAuth.setText(isLoginMode ?
                "Нет аккаунта? Зарегистрируйтесь" : "Уже есть аккаунт? Войдите");
    }

    private void setupButtons() {
        btnLogin.setOnClickListener(v -> {
            Log.d(TAG, "Login button clicked");
            attemptLogin();
        });

        btnRegister.setOnClickListener(v -> {
            Log.d(TAG, "Register button clicked");
            attemptRegistration();
        });
    }

    private void attemptLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (validateForm(email, password)) {
            loginUser(email, password);
        }
    }

    private void attemptRegistration() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (validateForm(email, password)) {
            registerUser(email, password);
        }
    }

    private boolean validateForm(String email, String password) {
        boolean valid = true;

        if (email.isEmpty()) {
            etEmail.setError("Введите email");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        if (password.isEmpty()) {
            etPassword.setError("Введите пароль");
            valid = false;
        } else if (password.length() < 6) {
            etPassword.setError("Минимум 6 символов");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }

    private void loginUser(String email, String password) {
        showProgress(true);
        Log.d(TAG, "Attempting login with: " + email);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    showProgress(false);

                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null && user.isEmailVerified()) {
                            Log.d(TAG, "Login success, user verified");
                            startMainActivity();
                        } else if (user != null) {
                            Log.d(TAG, "Email not verified");
                            showToast("Подтвердите email перед входом");
                            mAuth.signOut();
                        }
                    } else {
                        Log.e(TAG, "Login failed", task.getException());
                        showToast("Ошибка входа: " + getErrorMessage(task.getException()));
                    }
                });
    }

    private void registerUser(String email, String password) {
        showProgress(true);
        Log.d(TAG, "Attempting registration with: " + email);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    showProgress(false);

                    if (task.isSuccessful()) {
                        Log.d(TAG, "Registration success");
                        sendVerificationEmail();
                    } else {
                        Log.e(TAG, "Registration failed", task.getException());
                        showToast("Ошибка регистрации: " + getErrorMessage(task.getException()));
                    }
                });
    }

    private void sendVerificationEmail() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Verification email sent");
                            showToast("Письмо отправлено на " + user.getEmail());
                            mAuth.signOut();
                            isLoginMode = true;
                            updateAuthModeUI();
                        } else {
                            Log.e(TAG, "Email not sent", task.getException());
                            showToast("Ошибка отправки письма");
                        }
                    });
        }
    }

    private String getErrorMessage(Exception e) {
        if (e == null) return "Неизвестная ошибка";
        String msg = e.getMessage();
        return msg != null ? msg : "Ошибка без описания";
    }

    private void startMainActivity() {
        Log.d(TAG, "Starting MainActivity");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        btnLogin.setEnabled(!show);
        btnRegister.setEnabled(!show);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}