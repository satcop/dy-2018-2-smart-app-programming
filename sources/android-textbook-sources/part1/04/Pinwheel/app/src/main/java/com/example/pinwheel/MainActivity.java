package com.example.pinwheel;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_pinwheel = (ImageView)findViewById(R.id.pinwheel);

        ObjectAnimator object = ObjectAnimator.ofFloat(iv_pinwheel, "rotation", 360);
        object.setInterpolator(new LinearInterpolator());
        object.setDuration(2000);
        object.setRepeatCount(ValueAnimator.INFINITE);
        object.start();
    }
}
