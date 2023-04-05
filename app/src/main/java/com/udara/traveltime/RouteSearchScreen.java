package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RouteSearchScreen extends AppCompatActivity {

    EditText DepartureLocation;
    EditText ArrivalLocation;
    EditText TravelDate;
    EditText Time;
    Button Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_search_screen);
    }
}