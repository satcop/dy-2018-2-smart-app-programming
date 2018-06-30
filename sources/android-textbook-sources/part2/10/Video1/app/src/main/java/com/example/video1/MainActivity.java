package com.example.video1;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("android.resource://com.example.video1/" + R.raw.fountain_night);

        VideoView videoview = (VideoView)findViewById(R.id.videoview);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setVisibility(View.VISIBLE);
    }
}
