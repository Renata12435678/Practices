package ru.mirea.khasanovart.mireaproject.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import ru.mirea.khasanovart.mireaproject.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PermissionsFragment extends Fragment {

    private static final int REQUEST_CODE = 123;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permissions, container, false);

        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_FINE_LOCATION
        };

        requestPermissions(permissions, REQUEST_CODE);
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < permissions.length; i++) {
                result.append(permissions[i]).append(": ")
                        .append(grantResults[i] == PackageManager.PERMISSION_GRANTED ? "Granted" : "Denied")
                        .append("\n");
            }
            Toast.makeText(getContext(), result.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
