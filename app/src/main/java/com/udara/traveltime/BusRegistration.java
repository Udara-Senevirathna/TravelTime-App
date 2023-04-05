package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class BusRegistration extends AppCompatActivity {

    EditText DriverName;
    EditText NIC_NO;
    EditText Bus_No;
    EditText Sheet;
    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_registration);
    }
}