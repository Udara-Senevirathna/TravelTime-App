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

        depatureText.setText(getDeparture);
        arrivalText.setText(getArrival);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Routes");
        Query query = databaseReference.orderByChild("Arrival").equalTo(getArrival);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_items.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
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
                        Toast.makeText(ResultScreen.this, "Button is clicked", Toast.LENGTH_SHORT).show();
                        Button seatBookBtn = findViewById(R.id.op_btn);
                        int buttonId = seatBookBtn.getId();
                        list_items selectedItem = list_items.get(position);
                        Intent intent = new Intent(ResultScreen.this, SeatSelection.class);
                        intent.putExtra("BusNo", selectedItem.getBus_no());
                        intent.putExtra("RouteNo", selectedItem.getRoute_no());
                        intent.putExtra("Departure", getDeparture);
                        intent.putExtra("Arrival", getArrival);
                        intent.putExtra("ButtonId", buttonId);  // Corrected key name
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
}
