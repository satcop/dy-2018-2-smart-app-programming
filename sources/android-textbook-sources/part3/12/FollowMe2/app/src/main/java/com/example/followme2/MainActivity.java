package com.example.followme2;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

    float         screen_width;
    float         screen_height;
    float         iv_width;
    float         iv_height;
    float         x;
    float         y;
    float         previous_x;
    float         previous_y;

    ImageView iv_smile;
    Vibrator mVibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        iv_smile = (ImageView)findViewById(R.id.smile);

        mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        Display display = getWindowManager().getDefaultDisplay();
        screen_width = display.getWidth();
        screen_height = display.getHeight();

        iv_smile.measure(iv_smile.getMeasuredWidth(), iv_smile.getMeasuredHeight());
        iv_height = iv_smile.getMeasuredHeight();
        iv_width = iv_smile.getMeasuredWidth();

        x = screen_width / 2 - iv_width / 2;
        y = screen_height / 2 - iv_height / 2;
        iv_smile.setX(x);
        iv_smile.setY(y);

        previous_x = x;
        previous_y = y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                int touch_x = (int)event.getX();
                int touch_y = (int)event.getY();

                x = touch_x - iv_width / 2;
                y = touch_y - iv_height / 2;

                ObjectAnimator smileX = ObjectAnimator.ofFloat(iv_smile, "translationX",  previous_x, x);
                smileX.start();
                ObjectAnimator smileY = ObjectAnimator.ofFloat(iv_smile, "translationY", previous_y, y);
                smileY.start();

                mVibe.vibrate(50);

                previous_x = x;
                previous_y = y;
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }
}
