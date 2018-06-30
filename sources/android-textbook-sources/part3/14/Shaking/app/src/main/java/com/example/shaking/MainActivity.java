package com.example.shaking;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements SensorEventListener {

    ImageView img;

    SensorManager sm;

    Sensor sensor_proximity;

    Vibrator mVibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView)findViewById(R.id.img);

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        sensor_proximity = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sm.registerListener(this, sensor_proximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        sm.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] <= 4) {
                Animation ani = AnimationUtils.loadAnimation(this, R.anim.shaking);
                img.setImageResource(R.drawable.angry);
                img.startAnimation(ani);
                mVibe.vibrate(1000);
            } else
                img.setImageResource(R.drawable.smile);
        }
    }
}
