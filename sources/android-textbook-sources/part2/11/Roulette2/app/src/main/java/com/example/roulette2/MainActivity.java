package com.example.roulette2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv_roulette;
    float startDegree = 0f;
    float endDegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_roulette = (ImageView)findViewById(R.id.roulette);
    }

    public void rotate(View v) {
        startDegree = endDegree;
        int degree_min = 720;
        Random rand = new Random();
        int  degree_rand = rand.nextInt(360);
        endDegree = startDegree + degree_min + degree_rand;

        RotateAnimation rotate = new RotateAnimation(startDegree, endDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setDuration(5000);
        rotate.setFillAfter(true);
        iv_roulette.startAnimation(rotate);
    }

}