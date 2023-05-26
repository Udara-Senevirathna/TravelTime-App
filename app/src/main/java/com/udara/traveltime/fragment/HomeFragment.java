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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.udara.traveltime.R;
import com.udara.traveltime.ResultScreen;

import javax.xml.transform.Result;

public class HomeFragment extends Fragment {

    private EditText arrivalLocation, depatureLocation, date, time;
    private Button SearchButton;
    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        SearchButton = view.findViewById(R.id.searchButton);
        // Assume you have a Firebase database reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Routes");

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get Edit view

                arrivalLocation = view.findViewById(R.id.al_location);
                depatureLocation = view.findViewById(R.id.dp_location);
                date = view.findViewById(R.id.Travel_Date); // todo must fix this.
                time = view.findViewById(R.id.travel_Time);

                Log.d("FirebaseData","I am here");

                // Attach a ValueEventListener to the query
//                String arrivalLo = "", depatureLo = "", deteLo = "", timeLo = "";
//                // get the data
//                arrivalLo = arrivalLocation.getText().toString();
//                depatureLo = depatureLocation.getText().toString();
//                deteLo = date.getText().toString();
//                timeLo = time.getText().toString();

                // Create a query to search for nodes with a specific arrival location
                Query query = databaseReference.orderByChild("Arrival").equalTo("Mawathagama");
                QueryParcelable queryParcelable = new QueryParcelable(query);
                // sent the result to the result screen

                Intent intent = new Intent(requireActivity(), ResultScreen.class);
                intent.putExtra("Query", query);


                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            // Retrieve the data from the child node
                            String busNo = snapshot.child("Bus_No").getValue(String.class);
                            String routeNo = snapshot.child("Route_No").getValue(String.class);
                            String departureLocation = snapshot.child("Departure").getValue(String.class);
                            String arrivalLocation = snapshot.child("Arrival").getValue(String.class);

                            // Do something with the retrieved data
                            Log.d("FirebaseData", "Bus No: " + busNo);
                            Log.d("FirebaseData", "Route No: " + routeNo);
                            Log.d("FirebaseData", "Departure Location: " + departureLocation);
                            Log.d("FirebaseData", "Arrival Location: " + arrivalLocation);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors that occur during the data retrieval
                        Log.d("FirebaseData","I am here ooo");
                    }
                });

            }
        });

        return view;
    }


}