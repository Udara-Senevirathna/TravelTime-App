package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BusRegistration extends AppCompatActivity {

    EditText Bus_No;
    EditText DriverName;
    EditText NIC_NO;
    EditText Sheet;

    Button regButton;

    DatabaseHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_registration);

        Bus_No = findViewById(R.id.Bus_No);
        DriverName = findViewById(R.id.DriverName);
        NIC_NO = findViewById(R.id.NIC_NO);
        Sheet = findViewById(R.id.Sheet);


        regButton = findViewById(R.id.regButton);

        MyDB = new DatabaseHelper(this);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String getBusNo = Bus_No.getText().toString();
                final String getDriverName = DriverName.getText().toString();
                final String getNIC_NO = NIC_NO.getText().toString();
                final String getSheet = Sheet.getText().toString();

                if(TextUtils.isEmpty(getBusNo) || TextUtils.isEmpty(getDriverName) || TextUtils.isEmpty(getNIC_NO) || TextUtils.isEmpty(getSheet)){
                    Toast.makeText(getApplicationContext(), "Empty fields", Toast.LENGTH_SHORT).show();
                }else {
                    if ((getNIC_NO.length() <= 9)){
                        Toast.makeText(getApplicationContext(), "INC is Wrong", Toast.LENGTH_SHORT).show();
                    }else {
                        // add data to the database

                        Boolean result = MyDB.registerBus(getBusNo, getDriverName, getNIC_NO, getSheet);
                        if (result) {
                            Toast.makeText(getApplicationContext(), "Data is added to the database", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Data is not added to the database", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
        });
    }
}