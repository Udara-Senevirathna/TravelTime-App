package com.udara.traveltime;

import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyListHolder extends RecyclerView.ViewHolder {


    TextView depatureLocation, arrivalLocation, time;

    public MyListHolder(@NonNull View itemView) {
        super(itemView);

        depatureLocation = itemView.findViewById(R.id.startLocation);
        arrivalLocation = itemView.findViewById(R.id.end_location);
        time = itemView.findViewById(R.id.listTile);
    }
}