package com.udara.traveltime.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.udara.traveltime.R;
import com.udara.traveltime.ResultScreen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private EditText arrivalLocationEditText, departureLocationEditText, selectedDateEditText, selectedTimeEditText;
    private Button searchButton;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        searchButton = view.findViewById(R.id.searchButton);
        arrivalLocationEditText = view.findViewById(R.id.al_location);
        departureLocationEditText = view.findViewById(R.id.dp_location);
        selectedDateEditText = view.findViewById(R.id.Travel_Date);
        selectedTimeEditText = view.findViewById(R.id.travel_Time);

        searchButton.setOnClickListener(this);
        selectedDateEditText.setOnClickListener(this);
        selectedTimeEditText.setOnClickListener(this);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.searchButton) {
            // set the values to lower case.
            String departure = departureLocationEditText.getText().toString().trim();
            String arrivalLocation = arrivalLocationEditText.getText().toString().trim();
            String selectedDate = selectedDateEditText.getText().toString().trim();
            String selectedTime = selectedTimeEditText.getText().toString().trim();

            Log.d("TN", departure + " " + arrivalLocation + " " + selectedDate + " " + selectedTime);

            // Perform the necessary actions with the obtained values

            // Send the query object to the result screen
            Intent intent = new Intent(requireActivity(), ResultScreen.class);
            intent.putExtra("Departure", departure);
            intent.putExtra("Arrival", arrivalLocation);
            intent.putExtra("Date", selectedDate);
            intent.putExtra("Time", selectedTime);
            startActivity(intent);
        } else if (view.getId() == R.id.Travel_Date) {
            showDatePicker();
        } else if (view.getId() == R.id.travel_Time) {
            showTimePicker();
        }
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        String selectedDate = dateFormat.format(calendar.getTime());
                        selectedDateEditText.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void showTimePicker() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        String selectedTime = timeFormat.format(calendar.getTime());
                        selectedTimeEditText.setText(selectedTime);
                    }
                }, hour, minute, false);

        timePickerDialog.show();
    }
}
