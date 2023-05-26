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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RoutesRegister extends Fragment {
//    DatabaseHelper MyDB;
Spinner spinner;
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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        List<String> busplateNumbers = new ArrayList<String>();

        spinner = view.findViewById(R.id.spinner);
        routeNo = view.findViewById(R.id.RouteNo);
        departure = view.findViewById(R.id.Departure_Location);
        arrival = view.findViewById(R.id.Arrival_Location);
        time = view.findViewById(R.id.Times);
        String  price = "150.00";

        // get the current uid
        String uid = firebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Bus");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                busplateNumbers.clear(); // Clear the list before populating it again
                // Iterate through each child of the dataSnapshot
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the value of the busNo from each child
                    String busNo = snapshot.child("busNo").getValue(String.class);
                    // Use the busNo value as needed (e.g., display it in a TextView)
                    busplateNumbers.add(busNo);
                    // Example: Log the busNo value
                    Log.d("BusNo", busNo);
                }
                // Update the adapter when the data changes
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, busplateNumbers);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur during the data retrieval
            }
        });

        RouteRegButton = view.findViewById(R.id.registerButton);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                if( TextUtils.isEmpty(getRouteNo) || TextUtils.isEmpty(getdeparture) || TextUtils.isEmpty(getarrival) || TextUtils.isEmpty(gettime)){
                    Toast.makeText(getContext(), "Empty fields", Toast.LENGTH_SHORT).show();
                }
                else{

//                    boolean result = MyDB.registerRoute(getBus_No, getRouteNo, getdeparture, getarrival,"2023-02-10", gettime);
//                    if(result){
//                        Toast.makeText(getContext(), "data added", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(getContext(), "not added", Toast.LENGTH_SHORT).show();
//                    }
                    RegisterRoutes(getBus_No, getRouteNo, getdeparture, getarrival,"2023-02-10", gettime, price);
                }
            }
        });
        return view;
    }

    private void RegisterRoutes(String getBus_no, String getRouteNo, String getdeparture, String getarrival, String s, String gettime, String price) {
        firebaseUser = firebaseAuth.getCurrentUser();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Routes");
        String pushId = databaseReference.push().getKey(); // Generate a unique key for the data entry

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("Uid", firebaseUser.getUid()); // Use firebaseUser.getUid() to get the user's unique ID
        dataMap.put("Route_ID", pushId);
        dataMap.put("Bus_No", getBus_no);
        dataMap.put("Route_No", getRouteNo);
        dataMap.put("Departure", getdeparture);
        dataMap.put("Arrival", getarrival);
        dataMap.put("Price", price);
        dataMap.put("Date", s);
        dataMap.put("Time", gettime);

        databaseReference.child(pushId).setValue(dataMap); // Save the data under the generated key

        Toast.makeText(getContext(), "Data Insert Success", Toast.LENGTH_SHORT).show();
    }

}