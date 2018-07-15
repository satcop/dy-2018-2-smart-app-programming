package com.example.ktpark.followme;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

    Vibrator vibrator;

    ImageView smailImage;
    float previousX = 0;
    float previousY = 0;

    int imageWidth;
    int imageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        smailImage = (ImageView) findViewById(R.id.smaile);
        imageWidth = smailImage.getDrawable().getIntrinsicWidth() / 2;
        imageHeight = smailImage.getDrawable().getIntrinsicHeight() / 2;

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            //case  MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_DOWN:
                float currentX = event.getX();
                float currentY = event.getY();

                ObjectAnimator aniX = ObjectAnimator.ofFloat(smailImage, "translationX", previousX, currentX - imageWidth);
                aniX.start();
                ObjectAnimator aniY = ObjectAnimator.ofFloat(smailImage, "translationY", previousY, currentY - imageHeight);
                aniY.start();

                previousX = currentX - imageWidth;
                previousY = currentY - imageHeight;

                vibrator.vibrate(50);

                break;
        }

        return super.onTouchEvent(event);
    }
}
