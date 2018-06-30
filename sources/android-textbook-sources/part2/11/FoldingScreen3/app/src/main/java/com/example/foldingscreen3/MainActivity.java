package com.example.foldingscreen3;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_moon = (ImageView)findViewById(R.id.moon);
        Animation anim_moon = AnimationUtils.loadAnimation(this, R.anim.moon);
        iv_moon.startAnimation(anim_moon);

        ImageView iv_bird = (ImageView)findViewById(R.id.bird);
        Animation anim_bird = AnimationUtils.loadAnimation(this, R.anim.bird);
        iv_bird.startAnimation(anim_bird);

        iv_bird.setBackgroundResource(R.anim.bird_list);
        AnimationDrawable animation_drawable = (AnimationDrawable) iv_bird.getBackground();
        animation_drawable.start();

        ImageView iv_mountain = (ImageView)findViewById(R.id.mountain);
        Animation anim_mountain = AnimationUtils.loadAnimation(this, R.anim.mountain);
        iv_mountain.startAnimation(anim_mountain);
    }
}