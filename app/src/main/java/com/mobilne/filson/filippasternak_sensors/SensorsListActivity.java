package com.mobilne.filson.filippasternak_sensors;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SensorsListActivity extends BaseActivity {

    private SensorManager sensorManager;
    private Sensor sensor;
    private ArrayList<String> sensorsList;
    private ArrayAdapter<String> adapter;

    private ListView navigationList;
    private ArrayAdapter<String> navigationListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.applyView(this, R.layout.sensors_list);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        final List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorsList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sensorsList);
        ListView listView = (ListView) findViewById(R.id.sensorsListView);
        listView.setAdapter(adapter);

        for (Sensor sensor : deviceSensors) {
            sensorsList.add(sensor.getName());
        }

        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Sensor selectedSensor = deviceSensors.get(position);
                Intent intent = new Intent(SensorsListActivity.this, SensorDetailsActivity.class);

                intent.putExtra("sensorName", selectedSensor.getName());
                intent.putExtra("sensorType", selectedSensor.getType());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the Pressure Sensor
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
    }

    public void setTimeInterval(int interval) {

    }
}
