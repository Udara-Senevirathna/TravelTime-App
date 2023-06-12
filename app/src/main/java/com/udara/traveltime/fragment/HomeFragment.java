package com.udara.traveltime.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.udara.traveltime.R;
import com.udara.traveltime.ResultScreen;

public class HomeFragment extends Fragment {

    private EditText arrivalLocationEditText, departureLocationEditText, selectedDateEditText, selectedTimeEditText;
    private Button searchButton;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        searchButton = view.findViewById(R.id.searchButton);
        arrivalLocationEditText = view.findViewById(R.id.al_location);
        departureLocationEditText = view.findViewById(R.id.dp_location);
        selectedDateEditText = view.findViewById(R.id.Travel_Date);
        selectedTimeEditText = view.findViewById(R.id.travel_Time);

        String departure = departureLocationEditText.getText().toString();
        String arrivalLocation = arrivalLocationEditText.getText().toString();
        String selectedDate = selectedDateEditText.getText().toString();
        String selectedTime = selectedTimeEditText.getText().toString();

        Log.d("TN", departure + " " + arrivalLocation + " " + selectedDate + " " + selectedTime);

// Perform the necessary actions with the obtained values


        Log.d("TN", departure + " " + arrivalLocation + " " + selectedDate + " " + selectedTime);

// Perform the necessary actions with the obtained values


        // tmp variable
        String depature, arrival_LO, date, time;
        depature = "kurunegala";
        arrival_LO = "Mawathagama";
        date = "2023-02-15";
        time = "10.00";

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String arrival = arrivalLocation.getText().toString().trim();
//                String departure = departureLocation.getText().toString().trim();



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
