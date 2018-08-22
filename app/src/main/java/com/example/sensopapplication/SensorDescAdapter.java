package com.example.sensopapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.zip.Inflater;

public class SensorDescAdapter extends RecyclerView.Adapter<SensorDescAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<SensorDescription> sensDescList;


    public SensorDescAdapter(Context context, List<SensorDescription> sensDescList) {
        this.inflater = LayoutInflater.from(context);
        this.sensDescList = sensDescList;
    }

    @NonNull
    @Override
    public SensorDescAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.sensor_description, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorDescAdapter.ViewHolder viewHolder, int i) {
        SensorDescription sensorDescription = sensDescList.get(i);

        viewHolder.name.setText(sensorDescription.getName());
        viewHolder.type.setText(sensorDescription.getType());
        viewHolder.vendor.setText(sensorDescription.getVendor());
        viewHolder.version.setText(sensorDescription.getVersion());
        viewHolder.max.setText(sensorDescription.getMax());
        viewHolder.resolution.setText(sensorDescription.getResolution());

    }

    @Override
    public int getItemCount() {
        return sensDescList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, type, vendor, version, max, resolution;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            vendor = itemView.findViewById(R.id.vendor);
            version = itemView.findViewById(R.id.version);
            max = itemView.findViewById(R.id.max);
            resolution = itemView.findViewById(R.id.resolution);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Test for OnClick", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(v.getContext(), OneSensorActivity.class);
            intent.putExtra("sensor", sensDescList.get(getAdapterPosition()));
//            intent.putExtra("name", name.getText());
//            intent.putExtra("type", type.getText());
//            intent.putExtra("vendor", vendor.getText());
//            intent.putExtra("version", name.getText());
//            intent.putExtra("name", version.getText());
//            intent.putExtra("resolution", resolution.getText());
            v.getContext().startActivity(intent);
        }
    }
}
