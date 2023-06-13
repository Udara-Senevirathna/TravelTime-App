package com.udara.traveltime.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udara.traveltime.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
public class RoutesRegister extends Fragment implements View.OnClickListener {
    Spinner spinner;
    EditText routeNo;
    EditText departure;
    EditText arrival;
    EditText date;
    EditText time;
    EditText price;

    Button RouteRegButton;
    String selectedItem = "";

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Calendar calendar;
    DateFormat dateFormat;
    DateFormat timeFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routes_register, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        List<String> busplateNumbers = new ArrayList<>();

        spinner = view.findViewById(R.id.spinner);
        routeNo = view.findViewById(R.id.RouteNo);
        departure = view.findViewById(R.id.Departure_Location);
        arrival = view.findViewById(R.id.Arrival_Location);
        time = view.findViewById(R.id.Times);
        date = view.findViewById(R.id.Date);
        price = view.findViewById(R.id.Price);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        String uid = firebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Bus");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                busplateNumbers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String busNo = snapshot.child("busNo").getValue(String.class);
                    busplateNumbers.add(busNo);
                    Log.d("BusNo", busNo);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, busplateNumbers);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to read bus data", Toast.LENGTH_SHORT).show();
            }
        });

        RouteRegButton = view.findViewById(R.id.registerButton);
        RouteRegButton.setOnClickListener(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
                Log.d("Spinner", "Selected item: " + selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == RouteRegButton) {
            registerRoute();
        }
    }

    private void registerRoute() {
        String busNo = selectedItem;
        String routeNoValue = routeNo.getText().toString();
        String departureValue = departure.getText().toString();
        String arrivalValue = arrival.getText().toString();
        String timeValue = time.getText().toString();
        String dateValue = date.getText().toString();
        String priceValue = price.getText().toString();

        if (TextUtils.isEmpty(routeNoValue) || TextUtils.isEmpty(departureValue) || TextUtils.isEmpty(arrivalValue) || TextUtils.isEmpty(timeValue)) {
            Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                String userId = firebaseUser.getUid();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Routes");
                String pushId = databaseReference.push().getKey();

                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("Uid", userId);
                dataMap.put("Route_ID", pushId);
                dataMap.put("Bus_No", busNo);
                dataMap.put("Route_No", routeNoValue);
                dataMap.put("Departure", departureValue);
                dataMap.put("Arrival", arrivalValue);
                dataMap.put("Price", priceValue);
                dataMap.put("Date", dateValue);
                dataMap.put("Time", timeValue);

                if (pushId != null) {
                    databaseReference.child(pushId).setValue(dataMap)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Route registered successfully", Toast.LENGTH_SHORT).show();
                                    clearFields();
                                } else {
                                    Toast.makeText(getContext(), "Failed to register route", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        }
    }

    private void clearFields() {
        routeNo.setText("");
        departure.setText("");
        arrival.setText("");
        time.setText("");
        date.setText("");
        price.setText("");
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
                        date.setText(selectedDate);
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
                        time.setText(selectedTime);
                    }
                }, hour, minute, false);

        timePickerDialog.show();
    }
}
