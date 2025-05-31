package ru.mirea.khasanovart.lesson4;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.khasanovart.lesson4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView songTitle = binding.songTitle;
        TextView artistName = binding.artistName;
        SeekBar seekBar = binding.seekBar;
        ImageButton playButton = binding.playButton;
        ImageButton prevButton = binding.prevButton;
        ImageButton nextButton = binding.nextButton;

        songTitle.setText("Моя песня");
        artistName.setText("По списку я 20-ой прихожусь...");
        seekBar.setProgress(30);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    playButton.setImageResource(android.R.drawable.ic_media_play);
                    isPlaying = false;
                } else {
                    playButton.setImageResource(android.R.drawable.ic_media_pause);
                    isPlaying = true;
                }
            }
        });

        prevButton.setOnClickListener(v -> {
            songTitle.setText("Моя песенка-1");
        });

        nextButton.setOnClickListener(v -> {
            songTitle.setText("Моя песенка-2");
        });
    }
}