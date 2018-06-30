package com.example.motionsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements SensorEventListener {

    TextView x_gravity;
    TextView y_gravity;
    TextView z_gravity;

    TextView x_accelerometer;
    TextView y_accelerometer;
    TextView z_accelerometer;

    TextView x_linear_acceleration;
    TextView y_linear_acceleration;
    TextView z_linear_acceleration;

    TextView x_gyroscope;
    TextView y_gyroscope;
    TextView z_gyroscope;

    // Sensor Manager 객체 선언
    SensorManager sm;

    // Sensor 객체 선언
    Sensor sensor_gravity;
    Sensor sensor_accelerometer;
    Sensor sensor_linear_acceleration;
    Sensor sensor_gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setTitle("중력");

        x_gravity = (TextView)findViewById(R.id.x_gravity);
        y_gravity = (TextView)findViewById(R.id.y_gravity);
        z_gravity = (TextView)findViewById(R.id.z_gravity);

        x_accelerometer = (TextView)findViewById(R.id.x_accelerometer);
        y_accelerometer = (TextView)findViewById(R.id.y_accelerometer);
        z_accelerometer = (TextView)findViewById(R.id.z_accelerometer);

        x_linear_acceleration = (TextView)findViewById(R.id.x_linear_acceleration);
        y_linear_acceleration = (TextView)findViewById(R.id.y_linear_acceleration);
        z_linear_acceleration = (TextView)findViewById(R.id.z_linear_acceleration);

        x_gyroscope = (TextView)findViewById(R.id.x_gyroscope);
        y_gyroscope = (TextView)findViewById(R.id.y_gyroscope);
        z_gyroscope = (TextView)findViewById(R.id.z_gyroscope);

        // Sensor Manager 객체 생성
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        // 가속도 센서를 지정하여 Sensor 객체 생성
        sensor_gravity = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        // 가속도 센서를 지정하여 Sensor 객체 생성
        sensor_accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // 가속도 센서를 지정하여 Sensor 객체 생성
        sensor_linear_acceleration = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        // 가속도 센서를 지정하여 Sensor 객체 생성
        sensor_gyroscope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // 센서 리스너에 등록
        sm.registerListener(this, sensor_gravity, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_linear_acceleration, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        // 센서 리스너 해제
        sm.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch(event.sensor.getType()) {
            case Sensor.TYPE_GRAVITY:
                x_gravity.setText("X: " + event.values[0]);
                y_gravity.setText("Y: " + event.values[1]);
                z_gravity.setText("Z: " + event.values[2]);
                break;

            case Sensor.TYPE_ACCELEROMETER:
                x_accelerometer.setText("X: " + event.values[0]);
                y_accelerometer.setText("Y: " + event.values[1]);
                z_accelerometer.setText("Z: " + event.values[2]);
                break;

            case Sensor.TYPE_LINEAR_ACCELERATION:
                x_linear_acceleration.setText("X: " + event.values[0]);
                y_linear_acceleration.setText("Y: " + event.values[1]);
                z_linear_acceleration.setText("Z: " + event.values[2]);
                break;

            case Sensor.TYPE_GYROSCOPE:
                x_gyroscope.setText("X: " + event.values[0]);
                y_gyroscope.setText("Y: " + event.values[1]);
                z_gyroscope.setText("Z: " + event.values[2]);
                break;
        }
    }
}