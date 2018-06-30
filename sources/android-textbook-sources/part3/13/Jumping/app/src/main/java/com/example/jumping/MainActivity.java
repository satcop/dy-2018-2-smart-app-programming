package com.example.jumping;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv_count;

    SensorManager sm;

    Sensor sensor_accelerometer;

    int count = 0;
    int dir_UP = 0;
    int dir_DOWN = 0;

    double gravity = 9.8;
    double acceleration = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_count = (TextView)findViewById(R.id.count);
        tv_count.setText("" + count);

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        sensor_accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void initialize(View v) {
        count = 0;
        tv_count.setText("" + count);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sm.registerListener(this, sensor_accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        sm.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            acceleration = Math.sqrt(x * x + y * y + z * z);
        }

        if (acceleration - gravity > 5) {
            dir_UP = 1;
        }
        if (dir_UP == 1 && gravity - acceleration > 5) {
            dir_DOWN = 1;
        }

        if (dir_DOWN == 1) {
            count++;
            tv_count.setText("" + count);

            dir_UP = 0;
            dir_DOWN = 0;
        }
    }
}