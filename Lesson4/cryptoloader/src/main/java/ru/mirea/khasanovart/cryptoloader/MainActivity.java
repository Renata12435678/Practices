package ru.mirea.khasanovart.cryptoloader;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import ru.mirea.khasanovart.cryptoloader.databinding.ActivityMainBinding;

import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {

    private ActivityMainBinding binding;
    private final int LoaderID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phrase = binding.editText.getText().toString();
                if (phrase.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter phrase", Toast.LENGTH_SHORT).show();
                    return;
                }

                SecretKey secretKey = MyLoader.generateKey();

                byte[] encrypted = MyLoader.encryptMsg(phrase, secretKey);

                Bundle bundle = new Bundle();
                bundle.putByteArray(MyLoader.ARG_WORD, encrypted);
                bundle.putByteArray(MyLoader.ARG_KEY, secretKey.getEncoded());

                LoaderManager.getInstance(MainActivity.this).initLoader(LoaderID, bundle, MainActivity.this);
            }
        });
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == LoaderID) {
            Toast.makeText(this, "Loader started", Toast.LENGTH_SHORT).show();
            return new MyLoader(this, args);
        }
        throw new RuntimeException("Wrong loader id");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if (loader.getId() == LoaderID) {
            Toast.makeText(this, "Decrypted: " + data, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }
}