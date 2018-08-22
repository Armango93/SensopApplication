package com.example.sensopapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView tvText;
    SensorManager sensorManager;
    List<Sensor> sensors;
    List<SensorDescription> sensDescList = new ArrayList<>();
    Sensor sensorLight;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tvText = (TextView) findViewById(R.id.tvText);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : sensors) {
            sensDescList.add(new SensorDescription(sensor.getName(), ""+sensor.getType(), sensor.getVendor(), ""+sensor.getVersion(), ""+sensor.getMaximumRange(), ""+sensor.getResolution()));
        }

        SensorDescAdapter sensorDescAdapter = new SensorDescAdapter(this, sensDescList);

        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        recyclerView = findViewById(R.id.scroll);
        recyclerView.setAdapter(sensorDescAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClickSensList(View v) {

        sensorManager.unregisterListener(listenerLight, sensorLight);
        StringBuilder sb = new StringBuilder();

        for (Sensor sensor : sensors) {
            sb.append("name = ").append(sensor.getName())
                    .append(", type = ").append(sensor.getType())
                    .append("\nvendor = ").append(sensor.getVendor())
                    .append(" ,version = ").append(sensor.getVersion())
                    .append("\nmax = ").append(sensor.getMaximumRange())
                    .append(", resolution = ").append(sensor.getResolution())
                    .append("\n--------------------------------------\n");
        }
        tvText.setText(sb);
    }

    public void onClickSensLight(View v) {
        sensorManager.registerListener(listenerLight, sensorLight,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listenerLight, sensorLight);
    }

    SensorEventListener listenerLight = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            tvText.setText(String.valueOf(event.values[0]));
        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        System.out.println("Changed");
    }
}