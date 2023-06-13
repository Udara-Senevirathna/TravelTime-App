package com.udara.traveltime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResultScreen extends AppCompatActivity {

    Button seatBookBtn;
    TextView depatureText, arrivalText;
    private RecyclerView recyclerView;
    List<list_items> list_items;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        recyclerView = findViewById(R.id.recycleView2);
        list_items = new ArrayList<>();

        seatBookBtn = findViewById(R.id.op_btn);
        depatureText = findViewById(R.id.StartLocation1);
        arrivalText = findViewById(R.id.end_location1);

        final String getDeparture = getIntent().getStringExtra("Departure");
        final String getArrival = getIntent().getStringExtra("Arrival");
        final String getDate = getIntent().getStringExtra("Date");
        final String getTime = getIntent().getStringExtra("Time");

        depatureText.setText(getDeparture);
        arrivalText.setText(getArrival);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Routes");

        List<Query> queryList = new ArrayList<>();

// Check for available departure location
        if (!getDeparture.isEmpty()) {
            Query departureQuery = databaseReference.orderByChild("Departure").equalTo(getDeparture);
            queryList.add(departureQuery);
        }
        else if (!getArrival.isEmpty()) {
            Query arrivalQuery = databaseReference.orderByChild("Arrival").equalTo(getArrival);
            queryList.add(arrivalQuery);
        }

// Check for date and time if provided
        if (!getDate.isEmpty() && !getTime.isEmpty()) {
            Query dateQuery = databaseReference.orderByChild("Date").equalTo(getDate);
            Query timeQuery = databaseReference.orderByChild("Time").equalTo(getTime);
            queryList.add(dateQuery);
            queryList.add(timeQuery);
        } else if (!getDate.isEmpty()) {
            // Check for date only
            Query dateQuery = databaseReference.orderByChild("Date").equalTo(getDate);
            queryList.add(dateQuery);
        }

// Merge the query results
        Query mergedQuery = mergeQueries(queryList);

//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Routes");
//
//        Query query = databaseReference.orderByChild("Departure").equalTo("Kurunegala");

        mergedQuery.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                List<DataSnapshot> matchingRecords = new ArrayList<>();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String arrivalLocation = snapshot.child("Arrival").getValue(String.class);
//                    if (arrivalLocation != null && arrivalLocation.equals("Mawathagama")) {
//                        matchingRecords.add(snapshot);
//                    }
//                }

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Retrieve the data for each matching record
                    String busNo = snapshot.child("Bus_No").getValue(String.class);
                    String routeNo = snapshot.child("Route_No").getValue(String.class);
                    String departureLocation = snapshot.child("Departure").getValue(String.class);
                    String arrivalLocation = snapshot.child("Arrival").getValue(String.class);
                    String price = snapshot.child("Price").getValue(String.class);
                    String routeId = snapshot.child("Route_ID").getValue(String.class);
                    String time = snapshot.child("Time").getValue(String.class);

                    Log.d("FirebaseData", "Bus No: " + busNo);
                    Log.d("FirebaseData", "Route No: " + routeNo);
                    Log.d("FirebaseData", "Departure Location: " + departureLocation);
                    Log.d("FirebaseData", "Arrival Location: " + arrivalLocation);

                    list_items.add(new list_items(departureLocation, arrivalLocation, time, price, routeNo, busNo, routeId));
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(ResultScreen.this));
                ListViewAdapter adapter = new ListViewAdapter(ResultScreen.this, list_items);
                recyclerView.setAdapter(adapter);

                // Set the click listener for the button after setting up the adapter
                adapter.setOnItemClickListener(new ListViewAdapter.OnItemClickListener() {
                    public void onItemClick(int position) {
                        // Start SeatSelectionActivity with the corresponding data
                        list_items selectedItem = list_items.get(position);
                        Intent intent = new Intent(ResultScreen.this, SeatSelection.class);
                        intent.putExtra("BusNo", selectedItem.getBus_no());
                        intent.putExtra("RouteNo", selectedItem.getRoute_no());
                        intent.putExtra("Departure", getDeparture);
                        intent.putExtra("Arrival", getArrival);
                        intent.putExtra("ButtonClickedId", selectedItem.getRoute_button_id());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FirebaseData", "I am here ooo");
            }
        });

    }
    private Query mergeQueries(List<Query> queries) {
        if (queries.size() == 0) {
            // No queries to merge
            return null;
        } else if (queries.size() == 1) {
            // Only one query, return it
            return queries.get(0);
        } else {
            // Merge multiple queries into a single query result
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            Query mergedQuery = queries.get(0);

            for (int i = 1; i < queries.size(); i++) {
                Query query = queries.get(i);

                // Get the child path of the query
                String queryChildPath = query.getRef().toString().substring(query.getRef().getRoot().toString().length());

                // Append the child path to the merged query
                mergedQuery = mergedQuery.getRef().getParent().child(queryChildPath);
            }

            return mergedQuery;
        }
    }


}
