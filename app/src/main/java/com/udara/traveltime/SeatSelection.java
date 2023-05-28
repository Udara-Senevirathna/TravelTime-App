package com.udara.traveltime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class SeatSelection extends AppCompatActivity {

    Button ConfirmBtn;
    TextView depatureTitle, arrivalTitle,tmptext;
    FirebaseAuth firebaseAuth;
    String SeatSlected = "1";
    String[] array;
    ArrayList<String> list;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        ConfirmBtn = findViewById(R.id.ConfirmBtn);

         array = new String[]{};

        // Convert the array to an ArrayList
        list = new ArrayList<>(Arrays.asList(array));


//
//        Button[] buttons = new Button[10]; // Update the array size based on the number of buttons you have
//
////        buttons[0] = findViewById(R.id.my_button1);
//        buttons[1] = findViewById(R.id.my_button2);
//
//        final String buttonClickedIdSeatColor = getIntent().getStringExtra("ButtonClickedId");
//
//        for (Button button : buttons) {
//            if (button != null && button.getTag().equals(buttonClickedIdSeatColor)) {
//                button.setBackgroundColor(getResources().getColor(R.color.red));
//                break; // Exit the loop once the clicked button is found and colored
//            }
//        }

//        Button seatButton = findViewById(R.id.my_button1);
//        seatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Change the button's background color when clicked
//                seatButton.setBackgroundColor(getResources().getColor(R.color.red));
//            }
//        });


        depatureTitle = findViewById(R.id.depature_location_id);
        arrivalTitle = findViewById(R.id.arrival_location_id);

        tmptext = findViewById(R.id.textId);

        final String getDeparture = getIntent().getStringExtra("Departure");
        final String getArrival = getIntent().getStringExtra("Arrival");
        final String ButtonId = getIntent().getStringExtra("ButtonId");
        String buttonClickedId = getIntent().getStringExtra("ButtonClickedId");


        // set the title to the header
        depatureTitle.setText(getDeparture);
        arrivalTitle.setText(getArrival);

        tmptext.setText(ButtonId);
        Toast.makeText(this,String.valueOf(buttonClickedId), Toast.LENGTH_SHORT).show();

        // selected seat no

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BookingSeat");
        Query query = databaseReference.orderByChild("route_id").equalTo(buttonClickedId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String routeID = snapshot.child("route_id").getValue(String.class);
                    String seatNo = snapshot.child("selectedSeat").getValue(String.class);
//                    String status = snapshot.child("bookingStatus").getValue(String.class);


                    Log.d("FirebaseData", "route_id: " + routeID);
                    Log.d("FirebaseData", "seatNo: " + seatNo);
//                    Log.d("FirebaseData", status);

                    // Get the button associated with the seat
                    Button button = getButtonForSeat(seatNo);

                    if (button != null) {
                        // Set button color based on seat status
                        boolean isBooked = Boolean.TRUE.equals(snapshot.child("bookingStatus").getValue(Boolean.class));
                        if (isBooked) {
                            // Seat is booked, set button color to red
                            button.setBackgroundColor(getResources().getColor(R.color.red));
                            // Lock the button
                            button.setEnabled(false);
                        } else {
                            // Seat is available, set button color to green
                            button.setBackgroundColor(getResources().getColor(R.color.red));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error if needed
            }
        });


        // go to the payment page
        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(SeatSlected)){
                    Toast.makeText(SeatSelection.this, "Seat not selected", Toast.LENGTH_SHORT).show();
                }else{
                    if(!TextUtils.isEmpty(SeatSlected)){
                        boolean bookingStatus = true;
                        MakeBookingSeat(SeatSlected, buttonClickedId, bookingStatus);
                    }

                }


                Intent intent = new Intent(SeatSelection.this, PaymentMethod.class);
                startActivity(intent);
            }
        });
    }

    private void MakeBookingSeat(String seatSelected, String routeId, boolean bookingStatus) {
        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        // Get current date and time
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(calendar.getTime());
        String formattedTime = timeFormat.format(calendar.getTime());

        if(list.isEmpty()){
            Toast.makeText(this, "You've not selected any seat", Toast.LENGTH_SHORT).show();
        }else {
            for (int i = 0; i <= (list.size() -1 ); i++) {


                // Get reference to the "BookingSeat" node in the database
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BookingSeat");

                // Generate a unique key for the booking
                String bookingKey = databaseReference.push().getKey();

                // Create an instance of WriteMakeBooking with the selected seat, user ID, and route ID
                WriteMakeBooking makeBooking = new WriteMakeBooking(list.get(i), firebaseUser.getUid(), routeId, bookingStatus, bookingKey, formattedDate, formattedTime);

                // Store the booking under the generated key
                databaseReference.child(bookingKey).setValue(makeBooking)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SeatSelection.this, "Write data successfully", Toast.LENGTH_SHORT).show();
                                // Booking stored successfully
                                // You can perform any further actions here
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Failed to store the booking
                                // You can handle the error here
                            }
                        });

            }
        }

    }
    public void handleButtonClick(View view) {
        // Perform action based on the clicked button
        Button my_button1, my_button2, my_button3, my_button4, my_button5;
        my_button1 = findViewById(R.id.my_button1);
        my_button2 = findViewById(R.id.my_button2);
        my_button3 = findViewById(R.id.my_button3);
        my_button4 = findViewById(R.id.my_button4);
        my_button5 = findViewById(R.id.my_button5);
        int buttonId = view.getId();
        switch (buttonId) {
            case R.id.my_button1:
                if (my_button1.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button1.setSelected(false);
                    // Reset button color to default
                    my_button1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(1));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button1.setSelected(true);
                    // Change button color when selected
                    my_button1.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(1));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button2:
                if (my_button2.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button2.setSelected(false);
                    // Reset button color to default
                    my_button2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(2));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button2.setSelected(true);
                    // Change button color when selected
                    my_button2.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(2));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button3:
                if (my_button3.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button3.setSelected(false);
                    // Reset button color to default
                    my_button3.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(3));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button3.setSelected(true);
                    // Change button color when selected
                    my_button3.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(3));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button4:
                if (my_button4.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button4.setSelected(false);
                    // Reset button color to default
                    my_button4.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(4));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button4.setSelected(true);
                    // Change button color when selected
                    my_button4.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(4));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button5:
                if (my_button5.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button5.setSelected(false);
                    // Reset button color to default
                    my_button5.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(5));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button5.setSelected(true);
                    // Change button color when selected
                    my_button5.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(5));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            // Add cases for other buttons
        }
    }

    // Helper method to get the button associated with the seat
    private Button getButtonForSeat(String seat) {
        // Determine the button ID based on the seat value
        int buttonId = getResources().getIdentifier("my_button" + seat, "id", getPackageName());

        // Find and return the button
        return findViewById(buttonId);
    }
}