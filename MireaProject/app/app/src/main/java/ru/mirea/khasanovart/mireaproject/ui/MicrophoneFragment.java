package ru.mirea.khasanovart.mireaproject.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;

import ru.mirea.khasanovart.mireaproject.R;

public class MicrophoneFragment extends Fragment {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    private String fileName = null;
    private boolean isRecording = false;
    private boolean isPlaying = false;

    private Button recordButton;
    private Button playButton;
    private TextView statusText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_microphone, container, false);

        recordButton = view.findViewById(R.id.recordButton);
        playButton = view.findViewById(R.id.playButton);
        statusText = view.findViewById(R.id.statusText);

        fileName = requireActivity().getExternalCacheDir().getAbsolutePath();
        fileName += "/audiorecordtest.3gp";

        recordButton.setOnClickListener(v -> {
            if (checkAudioPermission()) {
                toggleRecording();
            }
        });

        playButton.setOnClickListener(v -> {
            if (checkAudioPermission()) {
                togglePlayback();
            }
        });

        return view;
    }

    private boolean checkAudioPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO_PERMISSION);
            return false;
        }
        return true;
    }

    private void toggleRecording() {
        if (!isRecording) {
            startRecording();
            recordButton.setText("Остановить запись");
            playButton.setEnabled(false);
            statusText.setText("Статус: запись...");
        } else {
            stopRecording();
            recordButton.setText("Начать запись");
            playButton.setEnabled(true);
            statusText.setText("Статус: запись завершена");
        }
        isRecording = !isRecording;
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            Toast.makeText(requireContext(), "Ошибка записи", Toast.LENGTH_SHORT).show();
            Log.e("AUDIO", "Ошибка записи: " + e.getMessage());
        }
    }

    private void stopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }

    private void togglePlayback() {
        if (!isPlaying) {
            startPlaying();
            playButton.setText("Остановить воспроизведение");
            recordButton.setEnabled(false);
            statusText.setText("Статус: воспроизведение...");
        } else {
            stopPlaying();
            playButton.setText("Воспроизвести");
            recordButton.setEnabled(true);
            statusText.setText("Статус: готов к записи");
        }
        isPlaying = !isPlaying;
    }

    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();

            player.setOnCompletionListener(mp -> {
                stopPlaying();
                playButton.setText("Воспроизвести");
                recordButton.setEnabled(true);
                statusText.setText("Статус: воспроизведение завершено");
                isPlaying = false;
            });
        } catch (IOException e) {
            Toast.makeText(requireContext(), "Ошибка воспроизведения", Toast.LENGTH_SHORT).show();
            Log.e("AUDIO", "Ошибка воспроизведения: " + e.getMessage());
        }
    }

    private void stopPlaying() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Разрешение получено", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Разрешение отклонено", Toast.LENGTH_SHORT).show();
            }
        }
    }
}