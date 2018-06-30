package com.example.roulette1;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
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

        ObjectAnimator object = ObjectAnimator.ofFloat(iv_roulette, "rotation", startDegree, endDegree);
        object.setInterpolator(new AccelerateDecelerateInterpolator());
        object.setDuration(5000); // miliseconds
        object.start();
    }

}
