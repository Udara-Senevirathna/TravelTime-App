package com.udara.traveltime.fragment;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.udara.traveltime.DatabaseHelper;
import com.udara.traveltime.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusRegister extends Fragment {
    EditText Bus_No;
    EditText DriverName;
    EditText NIC_NO;
    EditText Sheet;
    Button regButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bus_register, container, false);
        // Find the TextView with ID text_view and assign it to a variable

        Bus_No = view.findViewById(R.id.Bus_No);
        DriverName = view.findViewById(R.id.DriverName);
        NIC_NO = view.findViewById(R.id.NIC_NO);
        Sheet = view.findViewById(R.id.Sheet);
        regButton = view.findViewById(R.id.regButton);


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getBusNo = Bus_No.getText().toString();
                final String getDriverName = DriverName.getText().toString();
                final String getNIC_NO = NIC_NO.getText().toString();
                final String getSheet = Sheet.getText().toString();

                if(TextUtils.isEmpty(getBusNo) || TextUtils.isEmpty(getDriverName) || TextUtils.isEmpty(getNIC_NO) || TextUtils.isEmpty(getSheet)){
                    Toast.makeText(getContext(), "Empty fields", Toast.LENGTH_SHORT).show();
                }else {
                    if ((getNIC_NO.length() <= 9)){
                        Toast.makeText(getContext(), "INC is Wrong", Toast.LENGTH_SHORT).show();
                    }else {
                        // add data to the database

                    }
                }
            }
        });
        return view;
    }
}