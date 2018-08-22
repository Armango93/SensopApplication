package com.example.sensopapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OneSensorActivity extends AppCompatActivity {
    TextView name;
    TextView type;
    TextView vendor;
    TextView version;
    TextView max;
    TextView resolution;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_sensor);

        name = findViewById(R.id.textView_name);
        type = findViewById(R.id.textView_type);
        vendor = findViewById(R.id.textView_vendor);
        version = findViewById(R.id.textView_version);
        max = findViewById(R.id.textView_max);
        resolution = findViewById(R.id.textView_resolution);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            SensorDescription sensorDescription = (SensorDescription) extras.get("sensor");
            name.setText(String.valueOf(sensorDescription.getName()));
            type.setText(String.valueOf(sensorDescription.getType()));
            vendor.setText(String.valueOf(sensorDescription.getVendor()));
            version.setText(String.valueOf(sensorDescription.getVersion()));
            max.setText(String.valueOf(sensorDescription.getMax()));
            resolution.setText(String.valueOf(sensorDescription.getResolution()));
        }
    }
}
