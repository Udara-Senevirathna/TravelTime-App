package com.udara.traveltime.fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udara.traveltime.DatabaseHelper;

import com.udara.traveltime.R;

import java.util.ArrayList;
import java.util.List;

public class RoutesRegister extends Fragment {
//    DatabaseHelper MyDB;
    Spinner Bus_No;
    EditText routeNo;
    EditText departure;
    EditText arrival;
    EditText date;
    EditText time;

    Button RouteRegButton;
    String selectedItem = "";

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_routes_register, container, false);
        // Find the TextView with ID text_view and assign it to a variable
//        MyDB = new DatabaseHelper(getContext());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        List<String> busplateNumbers = new ArrayList<String>();

        Spinner spinner = view.findViewById(R.id.spinner);
        Bus_No = view.findViewById(R.id.spinner);
        routeNo = view.findViewById(R.id.RouteNo);
        departure = view.findViewById(R.id.Departure_Location);
        arrival = view.findViewById(R.id.Arrival_Location);
        time = view.findViewById(R.id.Times);

// Get the bus plate numbers from the database
//        Cursor cursor = MyDB.getBusPlateNO();
//        busplateNumbers.add("Select BusNO");
//        if (cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range") String busplateno = cursor.getString(cursor.getColumnIndex("BUSPLATEID"));
//                busplateNumbers.add(busplateno);
//            } while (cursor.moveToNext());
//        }


        // get the current uid

        String uid = firebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Bus");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Iterate through each child of the dataSnapshot
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the value of the busNo from each child
                    String busNo = snapshot.child("busNo").getValue(String.class);
                    // Use the busNo value as needed (e.g., display it in a TextView)
                    busplateNumbers.add(busNo);
                    // Example: Log the busNo value
                    Log.d("BusNo", busNo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur during the data retrieval
            }
        });

// Create an ArrayAdapter using the bus plate numbers
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, busplateNumbers);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        RouteRegButton = view.findViewById(R.id.registerButton);


        Bus_No.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                selectedItem = parent.getItemAtPosition(position).toString();
                // Do something with the selected item
                Log.d("Spinner", "Selected item: " + selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        RouteRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getBus_No = selectedItem;
                final String getRouteNo = routeNo.getText().toString();
                final String getdeparture = departure.getText().toString();
                final String getarrival = arrival.getText().toString();
                final String gettime = time.getText().toString();

                Log.d("Spinner", "Selected item: " + getBus_No);

                if(TextUtils.isEmpty(getBus_No) || TextUtils.isEmpty(getRouteNo) || TextUtils.isEmpty(getdeparture) || TextUtils.isEmpty(getarrival) || TextUtils.isEmpty(gettime)){
                    Toast.makeText(getContext(), "Empty fields", Toast.LENGTH_SHORT).show();
                }
                else{

//                    boolean result = MyDB.registerRoute(getBus_No, getRouteNo, getdeparture, getarrival,"2023-02-10", gettime);
//                    if(result){
//                        Toast.makeText(getContext(), "data added", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(getContext(), "not added", Toast.LENGTH_SHORT).show();
//                    }
                    RegisterRoutes(getBus_No, getRouteNo, getdeparture, getarrival,"2023-02-10", gettime);
                }
            }
        });
        return view;
    }

    private void RegisterRoutes(String getBus_no, String getRouteNo, String getdeparture, String getarrival, String s, String gettime) {
        firebaseUser = firebaseAuth.getCurrentUser();


    }
}