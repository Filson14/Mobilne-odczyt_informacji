package com.mobilne.filson.filippasternak_sensors;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Filson on 2016-03-12.
 */
public class SensorDetailsActivity extends BaseActivity implements SensorEventListener {

    private int UPDATE_INTERVAL = 300;
    private Sensor sensor;
    private SensorManager sensorManager;
    private int sensorType;
    private TextView titleTextView;
    private TextView valueOneView;
    private TextView valueTwoView;
    private TextView valueThreeView;
    private TextView valueFourView;
    private long lastUpdate;

//    private static final float NS2S = 1.0f / 1000000000.0f;
//    private final float[] deltaRotationVector = new float[4]();
//    private float timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.applyView(this, R.layout.sensor_details);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        titleTextView = (TextView) findViewById(R.id.titleView);
        valueOneView = (TextView) findViewById(R.id.sensorValue1);
        valueTwoView = (TextView) findViewById(R.id.sensorValue2);
        valueThreeView = (TextView) findViewById(R.id.sensorValue3);
        valueFourView = (TextView) findViewById(R.id.sensorValue4);

        Intent intent = getIntent();
        sensorType = intent.getIntExtra("sensorType", 1);
        String sensorName = intent.getStringExtra("sensorName");

        titleTextView.setText(sensorName);
        sensor = sensorManager.getDefaultSensor(sensorType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensor = sensorManager.getDefaultSensor(sensorType);
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
        sensor = event.sensor;

        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                onAccelerometerChanged(event);
                break;
            case Sensor.TYPE_GYROSCOPE:
                onGyroChange(event);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                onMagneticSensorChange(event);
                break;
            case Sensor.TYPE_PROXIMITY:
                onProximityChange(event);
                break;
            case Sensor.TYPE_LIGHT:
                onLightChange(event);
                break;
            case 3: //Sensor.TYPE_ORIENTATION
                onOrientationChange(event);
                break;
            case Sensor.TYPE_GRAVITY:
                onGravityChange(event);
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                onLinearAccelerationChange(event);
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                onRotationVectorChange(event);
                break;
        }

    }

    private void onAccelerometerChanged(SensorEvent event) {
        onThreeAxisSensorChange(event);

//        float x = event.values[0];
//        float y = event.values[1];
//        float z = event.values[2];

        /*// In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.
        final float alpha = 0.8;
        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];*/
    }

    private void onGyroChange(SensorEvent event) {
        onThreeAxisSensorChange(event);

        /*// This timestep's delta rotation to be multiplied by the current rotation
        // after computing it from the gyro sample data.
        if (timestamp != 0) {
            final float dT = (event.timestamp - timestamp) * NS2S;
            // Axis of the rotation sample, not normalized yet.
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            // Calculate the angular speed of the sample
            float omegaMagnitude = sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);

            // Normalize the rotation vector if it's big enough to get the axis
//            if (omegaMagnitude > EPSILON) {
            if (omegaMagnitude > 0.3) {
                axisX /= omegaMagnitude;
                axisY /= omegaMagnitude;
                axisZ /= omegaMagnitude;
            }

            // Integrate around this axis with the angular speed by the timestep
            // in order to get a delta rotation from this sample over the timestep
            // We will convert this axis-angle representation of the delta rotation
            // into a quaternion before turning it into the rotation matrix.
            float thetaOverTwo = omegaMagnitude * dT / 2.0f;
            float sinThetaOverTwo = sin(thetaOverTwo);
            float cosThetaOverTwo = cos(thetaOverTwo);
            deltaRotationVector[0] = sinThetaOverTwo * axisX;
            deltaRotationVector[1] = sinThetaOverTwo * axisY;
            deltaRotationVector[2] = sinThetaOverTwo * axisZ;
            deltaRotationVector[3] = cosThetaOverTwo;
        }
        timestamp = event.timestamp;
        float[] deltaRotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
        // User code should concatenate the delta rotation we computed with the current rotation
        // in order to get the updated rotation.
        // rotationCurrent = rotationCurrent * deltaRotationMatrix;
*/

    }

    private void onMagneticSensorChange(SensorEvent event) {
        onThreeAxisSensorChange(event);
    }

    private void onProximityChange(SensorEvent event) {
        long curTime = System.currentTimeMillis();
        if(curTime - lastUpdate > UPDATE_INTERVAL) {
            String label = "Proximity: ";
            lastUpdate = curTime;
            valueOneView.setText("Max Range: " + Float.toString(sensor.getMaximumRange()));

            label += event.values[0] > sensor.getMaximumRange() ? "Far" : "Close";
            valueTwoView.setText(label);
//            valueTwoView.setText("Proximity: " + Float.toString(event.values[0]));
        }
    }

    private void onLightChange(SensorEvent event) {
            valueOneView.setText("Max Range: " + Float.toString(sensor.getMaximumRange()));
            valueTwoView.setText("Light level: " + event.values[0]);
    }

    private void onOrientationChange(SensorEvent event) {
        onThreeAxisSensorChange(event);
    }

    private void onGravityChange(SensorEvent event) {
        onThreeAxisSensorChange(event);
    }

    private void onLinearAccelerationChange(SensorEvent event) {
        onThreeAxisSensorChange(event);
    }

    private void onRotationVectorChange(SensorEvent event) {
        onThreeAxisSensorChange(event);
        String accuracy = event.values[4] != -1 ? Float.toString(event.values[4]) : "Unavailable";
        valueFourView.setText("cos(theta/2) = " + event.values[3] + System.getProperty("line.separator") +
                "Estimated accuracy: " + accuracy);
    }

    private void onThreeAxisSensorChange(SensorEvent event) {
        long curTime = System.currentTimeMillis();
        if(curTime - lastUpdate > UPDATE_INTERVAL) {
            lastUpdate = curTime;
            valueOneView.setText("X: " + Float.toString(event.values[0]));
            valueTwoView.setText("Y: " + Float.toString(event.values[1]));
            valueThreeView.setText("Z: " + Float.toString(event.values[2]));
        }
    }
}
