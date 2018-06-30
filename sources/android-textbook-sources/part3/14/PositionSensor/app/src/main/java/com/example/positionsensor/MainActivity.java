package com.example.positionsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements SensorEventListener {

    TextView azimuth;
    TextView pitch;
    TextView roll;

    TextView x_magnetic_field;
    TextView y_magnetic_field;
    TextView z_magnetic_field;

    TextView proximity;

    SensorManager sm;

    Sensor sensor_orientation;
    Sensor sensor_magnetic_field;
    Sensor sensor_proximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        azimuth = (TextView)findViewById(R.id.azimuth);
        pitch = (TextView)findViewById(R.id.pitch);
        roll = (TextView)findViewById(R.id.roll);

        x_magnetic_field = (TextView)findViewById(R.id.x_magnetic_field);
        y_magnetic_field = (TextView)findViewById(R.id.y_magnetic_field);
        z_magnetic_field = (TextView)findViewById(R.id.z_magnetic_field);

        proximity = (TextView)findViewById(R.id.proximity);

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        sensor_orientation = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensor_magnetic_field = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensor_proximity = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sm.registerListener(this, sensor_orientation, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_magnetic_field, SensorManager.SENSOR_DELAY_NORMAL);
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

        switch(event.sensor.getType()) {
            case Sensor.TYPE_ORIENTATION:
                azimuth.setText("방위각: " + event.values[0]);
                pitch.setText("세로 방향 경사각: " + event.values[1]);
                roll.setText("가로 방향 경사각: " + event.values[2]);
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                x_magnetic_field.setText("X: " + event.values[0]);
                y_magnetic_field.setText("Y: " + event.values[1]);
                z_magnetic_field.setText("Z: " + event.values[2]);
                break;

            case Sensor.TYPE_PROXIMITY:
                proximity.setText("" + event.values[0]);
                break;
        }
    }
}
