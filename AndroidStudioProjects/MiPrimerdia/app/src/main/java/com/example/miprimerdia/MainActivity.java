package com.example.miprimerdia;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    public ToggleButton tglBtn;
    public MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tglBtn = findViewById(R.id.playBtn);
    }

    @Override
    protected void onStart() {

        super.onStart();

        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.pkmtheme);
    }

    public void onClickPlayBtn(View view)
    {
        if (tglBtn.isChecked())
        {
            PlayMusic();
        }
        else
        {
            StopMusic();
        }
    }

    public void PlayMusic()
    {
        mPlayer.start();
    }

    public void StopMusic()
    {
        mPlayer.stop();
    }
}
