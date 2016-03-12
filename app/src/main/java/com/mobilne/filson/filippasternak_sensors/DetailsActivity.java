package com.mobilne.filson.filippasternak_sensors;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Filson on 2016-03-12.
 */
public class DetailsActivity extends AppCompatActivity implements SensorEventListener{

    private Sensor sensor;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        TextView detailsTextView = (TextView) findViewById(R.id.detailsText);
        Intent intent = getIntent();
        int sensorType = intent.getIntExtra("sensorType", 1);
        String sensorName = intent.getStringExtra("sensorName");

        detailsTextView.setText(sensorName);
        sensor = sensorManager.getDefaultSensor(
                sensorType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //obsluga sensora, pobranie i prezentacja danych
    }
}
