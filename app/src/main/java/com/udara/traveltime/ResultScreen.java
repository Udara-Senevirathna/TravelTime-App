package com.udara.traveltime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    // access the recycleview
    private RecyclerView recyclerView;

    // initialize list veriable
    List<list_items> list_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        recyclerView = findViewById(R.id.recycleView2);
        list_items = new ArrayList<list_items>();


        depatureText = findViewById(R.id.StartLocation1);
        arrivalText = findViewById(R.id.end_location1);

        final String getDeparture = getIntent().getStringExtra("Departure");
        final String getArrival = getIntent().getStringExtra("Arrival");
        final String getDate = getIntent().getStringExtra("Date");
        final String getTime = getIntent().getStringExtra("Time");



        // set the title to the header
        depatureText.setText(getDeparture);
        arrivalText.setText(getArrival);
        // todo have't set the date, to the head screen

        // Assume you have a Firebase database reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Routes");

        // Create a query to search for nodes with the specified arrival and departure locations
        Query query = databaseReference.orderByChild("Arrival").equalTo(getArrival);




        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Retrieve the data from the child node
                    String busNo = snapshot.child("Bus_No").getValue(String.class);
                    String routeNo = snapshot.child("Route_No").getValue(String.class);
                    String departureLocation = snapshot.child("Departure").getValue(String.class);
                    String arrivalLocation = snapshot.child("Arrival").getValue(String.class);
                    String price = snapshot.child("Price").getValue(String.class);
                    String Route_Id = snapshot.child("Route_ID").getValue(String.class);
                    String time = snapshot.child("Time").getValue(String.class);


                    // Do something with the retrieved data
                    Log.d("FirebaseData", "Bus No: " + busNo);
                    Log.d("FirebaseData", "Route No: " + routeNo);
                    Log.d("FirebaseData", "Departure Location: " + departureLocation);
                    Log.d("FirebaseData", "Arrival Location: " + arrivalLocation);

                    list_items.add(new list_items(departureLocation, arrivalLocation, time, busNo, routeNo, price, routeNo));
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(ResultScreen.this));
                recyclerView.setAdapter(new ListViewAdapter(ResultScreen.this, list_items));

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur during the data retrieval
                Log.d("FirebaseData","I am here ooo");
            }
        });


    }
}