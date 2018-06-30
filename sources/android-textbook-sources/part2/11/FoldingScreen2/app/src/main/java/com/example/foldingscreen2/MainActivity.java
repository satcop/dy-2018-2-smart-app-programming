package com.example.foldingscreen2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_sea = (ImageView)findViewById(R.id.sea);
        ImageView iv_ball = (ImageView)findViewById(R.id.ball);
        ImageView iv_fire = (ImageView)findViewById(R.id.fire);

        AnimationSet sea = new AnimationSet(false);

        AlphaAnimation sea_alpha = new AlphaAnimation(0.2f, 1.0f);
        sea_alpha.setDuration(2000);
        sea_alpha.setRepeatCount(Animation.INFINITE);

        TranslateAnimation sea_trans = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.7f,
                Animation.RELATIVE_TO_PARENT, 0.7f);
        sea_trans.setDuration(2000);
        sea_trans.setRepeatCount(Animation.INFINITE);

        sea.addAnimation(sea_alpha);
        sea.addAnimation(sea_trans);
        iv_sea.startAnimation(sea);

        AnimationSet ball = new AnimationSet(false);

        ScaleAnimation ball_scale = new ScaleAnimation(
                1.0f, 0.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ball_scale.setDuration(2000);
        ball_scale.setRepeatCount(Animation.INFINITE);

        TranslateAnimation ball_trans = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.4f,
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.3f);
        ball_trans.setDuration(2000);
        ball_trans.setRepeatCount(Animation.INFINITE);

        ball.addAnimation(ball_scale);
        ball.addAnimation(ball_trans);
        iv_ball.startAnimation(ball);

        AnimationSet fire = new AnimationSet(false);

        ScaleAnimation fire_scale = new ScaleAnimation(
                0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        fire_scale.setDuration(2000);
        fire_scale.setRepeatCount(Animation.INFINITE);

        TranslateAnimation fire_trans = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.1f,
                Animation.RELATIVE_TO_PARENT, 0.1f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        fire_trans.setDuration(2000);
        fire_trans.setRepeatCount(Animation.INFINITE);

        fire.addAnimation(fire_scale);
        fire.addAnimation(fire_trans);
        iv_fire.startAnimation(fire);
    }
}
