package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RouteRegistration extends AppCompatActivity {

    EditText RouteNo;
    EditText Departure_Location;
    EditText Arrival_Location;
    EditText Times;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_registration);
    }
}