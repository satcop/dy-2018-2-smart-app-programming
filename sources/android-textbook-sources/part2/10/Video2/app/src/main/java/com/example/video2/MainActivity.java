package com.example.video2;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoview = (VideoView)findViewById(R.id.videoview);
    }

    public void play(View v) {
        int id = v.getId();
        LinearLayout layout = (LinearLayout)findViewById(id);
        String tag = (String) layout.getTag();

        Resources res = getResources();

        if (videoview.isPlaying()) {
            videoview.pause();
            Drawable drawable = res.getDrawable(R.drawable.shape_list);
            layout.setBackground(drawable);
        } else {
            int id_video = res.getIdentifier(tag, "raw", getPackageName());
            Uri uri = Uri.parse("android.resource://com.example.video2/" + id_video);

            VideoView videoview = (VideoView) findViewById(R.id.videoview);
            videoview.setVideoURI(uri);
            videoview.start();
            videoview.setVisibility(View.VISIBLE);

            Drawable drawable = res.getDrawable(R.drawable.shape_on);
            layout.setBackground(drawable);

            MediaController mc = new MediaController(this);
            videoview.setMediaController(mc);
        }
    }

    protected void onDestroy() {
        videoview.pause();
        super.onDestroy();
    }
}
