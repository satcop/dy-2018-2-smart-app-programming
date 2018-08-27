package com.example.park.musicalbum;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SongActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Intent intent = getIntent();
        String tag = intent.getStringExtra("tag");

        //Toast.makeText(this, tag, Toast.LENGTH_LONG).show();

        TextView songTitle = (TextView) findViewById(R.id.songTitle);
        ImageView songImage = (ImageView) findViewById(R.id.songImage);
        TextView songText = (TextView) findViewById(R.id.songText);

        songTitle.setText(getString("name", tag));
        songText.setText(getString("time", tag));
        //songImage.setBackground(getImage("song", tag));
        songImage.setImageDrawable(getImage("song", tag));

        int rawId = getRawId("song", tag);

        mediaPlayer = MediaPlayer.create(this, rawId);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
    }

    int getRawId(String type, String tag) {
        return getResources().getIdentifier(type + tag, "raw", getPackageName());
    }

    Drawable getImage(String type, String tag) {
        int id = getResources().getIdentifier(type + tag, "drawable", getPackageName());
        return getResources().getDrawable(id);
    }

    String getString(String type, String tag) {
        int id = getResources().getIdentifier(type + tag, "string", getPackageName());
        return getResources().getString(id);
    }

    @Override
    protected void onDestroy() {

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        finish();

        super.onDestroy();
    }
}
