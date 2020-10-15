package com.example.chatfirebase;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton pause, back, next;
    private SeekBar progress;
    private MediaPlayer media;
    RelativeLayout relativeLayout;
    private int[] musics = {R.raw.musicone,
            R.raw.musictwo};
    private int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout=findViewById(R.id.relativelayout);
        pause = findViewById(R.id.pauseButton);
        back = findViewById(R.id.backButton);
        next = findViewById(R.id.forwardButton);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_theme(relativeLayout);
            }
        });


        media = MediaPlayer.create(this, musics[k]);
        play();

    }


    public void change_theme(View view) {
        if(relativeLayout.getBackground().getConstantState()==getResources().getDrawable(R.drawable.photo).getConstantState())
        {
            relativeLayout.setBackgroundResource(R.drawable.ocean);
        }
        else if(relativeLayout.getBackground().getConstantState()==getResources().getDrawable(R.drawable.ocean).getConstantState())
        {
            relativeLayout.setBackgroundResource(R.drawable.sunrise);
        }
        else if(relativeLayout.getBackground().getConstantState()==getResources().getDrawable(R.drawable.sunrise).getConstantState())
        {
            relativeLayout.setBackgroundResource(R.drawable.photo);
        }
    }



    private void play() {
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (media.isPlaying()) {
                    Toast.makeText(MainActivity.this, "music paused", Toast.LENGTH_SHORT).show();
                    media.pause();
                    pause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                } else {
                    Toast.makeText(MainActivity.this, "music start", Toast.LENGTH_SHORT).show();
                    media.start();
                    pause.setImageResource(R.drawable.ic_pause_black_24dp);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media.stop();
                k--;
                music_change();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media.stop();
                k++;
                music_change();
            }
        });
    }

    private void music_change() {
        if (k == musics.length) {
            k = 0;
        } else if (k == -1) {
            k = 1;
        }
        media = MediaPlayer.create(this, musics[k]);
        media.start();
    }
}
