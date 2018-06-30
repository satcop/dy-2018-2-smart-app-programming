package com.example.audio3;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer();
    LinearLayout layout_before = null;
    int id_before = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View v) {
        int id = v.getId();
        LinearLayout layout = (LinearLayout)findViewById(id);

        Resources res = getResources();

        if (mp.isPlaying())
            mp.pause();

        if (id_before != -1) {
            layout_before = (LinearLayout) findViewById(id_before);
            Drawable drawable = res.getDrawable(R.drawable.shape_list);
            layout_before.setBackground(drawable);
        }

        if (id != id_before) {
            String tag = (String) layout.getTag();
            int id_audio = res.getIdentifier(tag, "raw", getPackageName());

            mp = MediaPlayer.create(this, id_audio);
            mp.setLooping(false);
            mp.start();

            Drawable drawable = res.getDrawable(R.drawable.shape_on);
            layout.setBackground(drawable);

            id_before = id;
        }
    }

    protected void onDestroy() {
        mp.stop();
        mp.release();
        super.onDestroy();
    }
}
