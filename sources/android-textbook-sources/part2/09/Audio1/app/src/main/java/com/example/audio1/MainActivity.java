package com.example.audio1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.schoolbell);
        mp.setLooping(false);;
        mp.start();
    }

    protected void onDestroy() {
        mp.stop();
        mp.release();
        super.onDestroy();
    }
}
