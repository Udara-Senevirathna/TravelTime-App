package com.udara.traveltime.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.udara.traveltime.QueryParcelable;
import com.udara.traveltime.R;
import com.udara.traveltime.ResultScreen;

public class HomeFragment extends Fragment {

    private EditText arrivalLocation, departureLocation, date, time;
    private Button searchButton;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        searchButton = view.findViewById(R.id.searchButton);
        arrivalLocation = view.findViewById(R.id.al_location);
        departureLocation = view.findViewById(R.id.dp_location);
        date = view.findViewById(R.id.Travel_Date);
        time = view.findViewById(R.id.travel_Time);

        // tmp variable
        String depature, arrival_LO, date, time;
        depature = "kurunegala";
        arrival_LO = "Mawathagama";
        date = "2023-02-15";
        time = "10.00";

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String arrival = arrivalLocation.getText().toString().trim();
                String departure = departureLocation.getText().toString().trim();



                // Send the query object to the result screen
                Intent intent = new Intent(requireActivity(), ResultScreen.class);
                intent.putExtra("Departure", depature);
                intent.putExtra("Arrival", arrival_LO);
                intent.putExtra("Date", date);
                intent.putExtra("Time", time);
                startActivity(intent);
            }
        });

        return view;
    }
}
