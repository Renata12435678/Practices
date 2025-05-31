package ru.mirea.khasanovart.mireaproject.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import ru.mirea.khasanovart.mireaproject.R;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraFragment extends Fragment {

    private static final int REQUEST_CODE_PERMISSION = 100;
    private boolean isWork = false;
    private Uri imageUri;
    private ImageView imageView;
    private EditText eventTitleEditText;
    private EditText eventDescriptionEditText;
    private File photoFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        imageView = view.findViewById(R.id.imageView);
        eventTitleEditText = view.findViewById(R.id.eventTitleEditText);
        eventDescriptionEditText = view.findViewById(R.id.eventDescriptionEditText);
        Button btnCapture = view.findViewById(R.id.btnCapture);
        Button btnSaveNote = view.findViewById(R.id.btnSaveNote);

        int cameraPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA);
        int storagePermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (cameraPermission == PackageManager.PERMISSION_GRANTED && storagePermission == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            requestPermissions(
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION);
        }

        btnCapture.setOnClickListener(v -> {
            if (isWork) {
                try {
                    photoFile = createImageFile();
                    String authorities = requireContext().getApplicationContext().getPackageName() + ".fileprovider";
                    imageUri = FileProvider.getUriForFile(requireContext(), authorities, photoFile);

                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(cameraIntent, REQUEST_CODE_PERMISSION);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Ошибка при создании файла", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Разрешения не предоставлены", Toast.LENGTH_SHORT).show();
            }
        });

        btnSaveNote.setOnClickListener(v -> {
            if (photoFile != null && photoFile.exists()) {
                String title = eventTitleEditText.getText().toString();
                String description = eventDescriptionEditText.getText().toString();

                if (!title.isEmpty()) {
                    String noteInfo = "Заметка сохранена:\n" +
                            "Название: " + title + "\n" +
                            "Описание: " + description + "\n" +
                            "Фото: " + photoFile.getAbsolutePath();

                    Toast.makeText(getContext(), noteInfo, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Введите название события", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Сначала сделайте фото события", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "EVENT_" + timeStamp + "_";
        File storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PERMISSION && resultCode == getActivity().RESULT_OK) {
            imageView.setImageURI(imageUri);
            Toast.makeText(getContext(), "Фото сохранено: " + photoFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (!isWork) {
                Toast.makeText(getContext(), "Для работы камеры требуются все разрешения", Toast.LENGTH_SHORT).show();
            }
        }
    }
}