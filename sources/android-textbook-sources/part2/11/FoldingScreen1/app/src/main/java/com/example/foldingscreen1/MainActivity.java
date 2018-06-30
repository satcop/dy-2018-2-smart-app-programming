package com.example.foldingscreen1;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_sea = (ImageView) findViewById(R.id.sea);
        ImageView iv_ball = (ImageView) findViewById(R.id.ball);
        ImageView iv_fire = (ImageView) findViewById(R.id.fire);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        iv_sea.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int sea_Height = iv_sea.getMeasuredHeight();
        int sea_y_pos = height - sea_Height;

        ObjectAnimator sea_Y = ObjectAnimator.ofFloat(iv_sea, "translationY", sea_y_pos, sea_y_pos);
        sea_Y.setDuration(2000);
        sea_Y.setRepeatCount(ObjectAnimator.INFINITE);

        ObjectAnimator sea_alpha = ObjectAnimator.ofFloat(iv_sea, "alpha", 0.2f, 1.0f);
        sea_alpha.setDuration(2000);
        sea_alpha.setRepeatCount(ObjectAnimator.INFINITE);

        ObjectAnimator ball_X = ObjectAnimator.ofFloat(iv_ball, "translationX", 50.0f, 450.0f);
        ObjectAnimator ball_Y = ObjectAnimator.ofFloat(iv_ball, "translationY", height, 450.0f);
        ball_X.setDuration(2000);
        ball_X.setRepeatCount(ObjectAnimator.INFINITE);
        ball_Y.setDuration(2000);
        ball_Y.setRepeatCount(ObjectAnimator.INFINITE);

        ObjectAnimator ball_scaleX = ObjectAnimator.ofFloat(iv_ball, "scaleX", 1.0f, 0.0f);
        ObjectAnimator ball_scaleY = ObjectAnimator.ofFloat(iv_ball, "scaleY", 1.0f, 0.0f);
        ball_scaleX.setDuration(2000);
        ball_scaleX.setRepeatCount(ObjectAnimator.INFINITE);
        ball_scaleY.setDuration(2000);
        ball_scaleY.setRepeatCount(ObjectAnimator.INFINITE);

        ObjectAnimator fire_X = ObjectAnimator.ofFloat(iv_fire, "translationX", 100.0f, 100.0f);
        ObjectAnimator fire_Y = ObjectAnimator.ofFloat(iv_fire, "translationY", 20.0f, 20.0f);
        fire_X.setDuration(2000);
        fire_X.setRepeatCount(ObjectAnimator.INFINITE);
        fire_Y.setDuration(2000);
        fire_Y.setRepeatCount(ObjectAnimator.INFINITE);

        ObjectAnimator fire_scale = ObjectAnimator.ofPropertyValuesHolder(iv_fire,
                PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f));
        fire_scale.setDuration(2000);
        fire_scale.setRepeatCount(ObjectAnimator.INFINITE);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(sea_Y, sea_alpha, ball_X, ball_Y, ball_scaleX, ball_scaleY, fire_X, fire_Y, fire_scale);
        set.start();
    }
}
