package com.udara.traveltime;

import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyListHolder extends RecyclerView.ViewHolder {


    TextView depatureLocation, arrivalLocation, time, price, bus_no, route_no, route_button_id ;

    public MyListHolder(@NonNull View itemView) {
        super(itemView);

        depatureLocation = itemView.findViewById(R.id.startLocation);
        arrivalLocation = itemView.findViewById(R.id.end_location);
        time = itemView.findViewById(R.id.listTile);
        price = itemView.findViewById(R.id.price);
        bus_no = itemView.findViewById(R.id.Route_no);
        route_no = itemView.findViewById(R.id.bus_no);
        route_button_id = itemView.findViewById(R.id.op_btn);
    }
}