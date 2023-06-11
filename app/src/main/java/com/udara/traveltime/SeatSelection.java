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

                if(list.isEmpty()){
                    Toast.makeText(SeatSelection.this, "Seat not selected", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(SeatSelection.this, PaymentMethod.class);
                    intent.putStringArrayListExtra("SeatSelectedList", new ArrayList<>(list));
                    intent.putExtra("BookingID", buttonClickedId);
                    startActivity(intent);

                }
            }
        });
    }

    public void handleButtonClick(View view) {
        // Perform action based on the clicked button
        Button my_button1, my_button2, my_button3, my_button4, my_button5, my_button6, my_button7, my_button8, my_button9,
                my_button10, my_button11, my_button12, my_button13, my_button14, my_button15, my_button16, my_button17, my_button18,
                my_button19, my_button20, my_button21, my_button22, my_button23, my_button24, my_button25, my_button26, my_button27,
                my_button28, my_button29, my_button30, my_button31, my_button32, my_button33, my_button34, my_button35, my_button36,
                my_button37, my_button38, my_button39, my_button40, my_button41, my_button42, my_button43, my_button44, my_button45,
                my_button46, my_button47, my_button48, my_button49, my_button50, my_button51, my_button52;
        my_button1 = findViewById(R.id.my_button1);
        my_button2 = findViewById(R.id.my_button2);
        my_button3 = findViewById(R.id.my_button3);
        my_button4 = findViewById(R.id.my_button4);
        my_button5 = findViewById(R.id.my_button5);
        my_button6 = findViewById(R.id.my_button6);
        my_button7 = findViewById(R.id.my_button7);
        my_button8 = findViewById(R.id.my_button8);
        my_button9 = findViewById(R.id.my_button9);
        my_button10 = findViewById(R.id.my_button10);
        my_button11 = findViewById(R.id.my_button11);
        my_button12 = findViewById(R.id.my_button12);
        my_button13 = findViewById(R.id.my_button13);
        my_button14 = findViewById(R.id.my_button14);
        my_button15 = findViewById(R.id.my_button15);
        my_button16 = findViewById(R.id.my_button16);
        my_button17 = findViewById(R.id.my_button17);
        my_button18 = findViewById(R.id.my_button18);
        my_button19 = findViewById(R.id.my_button19);
        my_button20 = findViewById(R.id.my_button20);
        my_button21 = findViewById(R.id.my_button21);
        my_button22 = findViewById(R.id.my_button22);
        my_button23 = findViewById(R.id.my_button23);
        my_button24 = findViewById(R.id.my_button24);
        my_button25 = findViewById(R.id.my_button25);
        my_button26 = findViewById(R.id.my_button26);
        my_button27 = findViewById(R.id.my_button27);
        my_button28 = findViewById(R.id.my_button28);
        my_button29 = findViewById(R.id.my_button29);
        my_button30 = findViewById(R.id.my_button30);
        my_button31 = findViewById(R.id.my_button31);
        my_button32 = findViewById(R.id.my_button32);
        my_button33 = findViewById(R.id.my_button33);
        my_button34 = findViewById(R.id.my_button34);
        my_button35 = findViewById(R.id.my_button35);
        my_button36 = findViewById(R.id.my_button36);
        my_button37 = findViewById(R.id.my_button37);
        my_button38 = findViewById(R.id.my_button38);
        my_button39 = findViewById(R.id.my_button39);
        my_button40 = findViewById(R.id.my_button40);
        my_button41 = findViewById(R.id.my_button41);
        my_button42 = findViewById(R.id.my_button42);
        my_button43 = findViewById(R.id.my_button43);
        my_button44 = findViewById(R.id.my_button44);
        my_button45 = findViewById(R.id.my_button45);
        my_button46 = findViewById(R.id.my_button46);
        my_button47 = findViewById(R.id.my_button47);
        my_button48 = findViewById(R.id.my_button48);
        my_button49 = findViewById(R.id.my_button49);
        my_button50 = findViewById(R.id.my_button50);
        my_button51 = findViewById(R.id.my_button51);
        my_button52 = findViewById(R.id.my_button52);

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
            case R.id.my_button6:
                if (my_button6.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button6.setSelected(false);
                    // Reset button color to default
                    my_button6.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(6));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button6.setSelected(true);
                    // Change button color when selected
                    my_button6.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(6));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button7:
                if (my_button7.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button7.setSelected(false);
                    // Reset button color to default
                    my_button7.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(7));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button7.setSelected(true);
                    // Change button color when selected
                    my_button7.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(7));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button8:
                if (my_button8.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button8.setSelected(false);
                    // Reset button color to default
                    my_button8.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(8));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button8.setSelected(true);
                    // Change button color when selected
                    my_button8.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(8));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button9:
                if (my_button9.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button9.setSelected(false);
                    // Reset button color to default
                    my_button9.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(9));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button9.setSelected(true);
                    // Change button color when selected
                    my_button9.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(9));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button10:
                if (my_button10.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button10.setSelected(false);
                    // Reset button color to default
                    my_button10.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(10));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button10.setSelected(true);
                    // Change button color when selected
                    my_button10.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(10));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button11:
                if (my_button11.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button11.setSelected(false);
                    // Reset button color to default
                    my_button11.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(11));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button11.setSelected(true);
                    // Change button color when selected
                    my_button11.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(11));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button12:
                if (my_button12.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button12.setSelected(false);
                    // Reset button color to default
                    my_button12.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(12));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button12.setSelected(true);
                    // Change button color when selected
                    my_button12.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(12));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button13:
                if (my_button13.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button13.setSelected(false);
                    // Reset button color to default
                    my_button13.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(13));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button13.setSelected(true);
                    // Change button color when selected
                    my_button13.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(13));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button14:
                if (my_button14.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button14.setSelected(false);
                    // Reset button color to default
                    my_button14.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(14));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button14.setSelected(true);
                    // Change button color when selected
                    my_button14.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(14));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button15:
                if (my_button15.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button15.setSelected(false);
                    // Reset button color to default
                    my_button15.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(15));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button15.setSelected(true);
                    // Change button color when selected
                    my_button15.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(15));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button16:
                if (my_button16.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button16.setSelected(false);
                    // Reset button color to default
                    my_button16.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(16));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button16.setSelected(true);
                    // Change button color when selected
                    my_button16.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(16));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button17:
                if (my_button17.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button17.setSelected(false);
                    // Reset button color to default
                    my_button17.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(17));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button17.setSelected(true);
                    // Change button color when selected
                    my_button17.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(17));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button18:
                if (my_button18.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button18.setSelected(false);
                    // Reset button color to default
                    my_button18.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(18));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button18.setSelected(true);
                    // Change button color when selected
                    my_button18.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(18));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button19:
                if (my_button19.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button19.setSelected(false);
                    // Reset button color to default
                    my_button19.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(19));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button19.setSelected(true);
                    // Change button color when selected
                    my_button19.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(19));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button20:
                if (my_button20.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button20.setSelected(false);
                    // Reset button color to default
                    my_button20.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(20));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button20.setSelected(true);
                    // Change button color when selected
                    my_button20.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(20));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button21:
                if (my_button21.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button21.setSelected(false);
                    // Reset button color to default
                    my_button21.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(21));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button21.setSelected(true);
                    // Change button color when selected
                    my_button21.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(21));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button22:
                if (my_button22.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button22.setSelected(false);
                    // Reset button color to default
                    my_button22.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(22));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button22.setSelected(true);
                    // Change button color when selected
                    my_button22.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(22));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button23:
                if (my_button23.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button23.setSelected(false);
                    // Reset button color to default
                    my_button23.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(23));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button23.setSelected(true);
                    // Change button color when selected
                    my_button23.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(23));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button24:
                if (my_button24.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button24.setSelected(false);
                    // Reset button color to default
                    my_button24.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(24));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button24.setSelected(true);
                    // Change button color when selected
                    my_button24.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(24));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button25:
                if (my_button25.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button25.setSelected(false);
                    // Reset button color to default
                    my_button25.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(25));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button25.setSelected(true);
                    // Change button color when selected
                    my_button25.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(25));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button26:
                if (my_button26.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button26.setSelected(false);
                    // Reset button color to default
                    my_button26.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(26));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button26.setSelected(true);
                    // Change button color when selected
                    my_button26.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(26));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button27:
                if (my_button27.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button27.setSelected(false);
                    // Reset button color to default
                    my_button27.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(27));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button27.setSelected(true);
                    // Change button color when selected
                    my_button27.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(27));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button28:
                if (my_button28.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button28.setSelected(false);
                    // Reset button color to default
                    my_button28.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(28));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button28.setSelected(true);
                    // Change button color when selected
                    my_button28.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(28));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button29:
                if (my_button29.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button29.setSelected(false);
                    // Reset button color to default
                    my_button29.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(29));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button29.setSelected(true);
                    // Change button color when selected
                    my_button29.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(29));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button30:
                if (my_button30.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button30.setSelected(false);
                    // Reset button color to default
                    my_button30.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(30));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button30.setSelected(true);
                    // Change button color when selected
                    my_button30.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(30));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button31:
                if (my_button31.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button31.setSelected(false);
                    // Reset button color to default
                    my_button31.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(31));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button31.setSelected(true);
                    // Change button color when selected
                    my_button31.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(31));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button32:
                if (my_button32.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button32.setSelected(false);
                    // Reset button color to default
                    my_button32.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(32));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button32.setSelected(true);
                    // Change button color when selected
                    my_button32.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(32));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button33:
                if (my_button33.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button33.setSelected(false);
                    // Reset button color to default
                    my_button33.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(33));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button33.setSelected(true);
                    // Change button color when selected
                    my_button33.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(33));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button34:
                if (my_button34.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button34.setSelected(false);
                    // Reset button color to default
                    my_button34.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(34));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button34.setSelected(true);
                    // Change button color when selected
                    my_button34.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(34));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button35:
                if (my_button35.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button35.setSelected(false);
                    // Reset button color to default
                    my_button35.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(30));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button35.setSelected(true);
                    // Change button color when selected
                    my_button35.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(35));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button36:
                if (my_button36.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button36.setSelected(false);
                    // Reset button color to default
                    my_button36.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(36));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button36.setSelected(true);
                    // Change button color when selected
                    my_button36.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(36));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button37:
                if (my_button37.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button37.setSelected(false);
                    // Reset button color to default
                    my_button37.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(37));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button37.setSelected(true);
                    // Change button color when selected
                    my_button37.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(37));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button38:
                if (my_button38.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button38.setSelected(false);
                    // Reset button color to default
                    my_button38.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(38));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button38.setSelected(true);
                    // Change button color when selected
                    my_button38.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(38));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button39:
                if (my_button39.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button39.setSelected(false);
                    // Reset button color to default
                    my_button39.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(39));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button39.setSelected(true);
                    // Change button color when selected
                    my_button39.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(39));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button40:
                if (my_button40.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button40.setSelected(false);
                    // Reset button color to default
                    my_button40.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(40));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button40.setSelected(true);
                    // Change button color when selected
                    my_button40.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(40));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button41:
                if (my_button41.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button41.setSelected(false);
                    // Reset button color to default
                    my_button41.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(41));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button41.setSelected(true);
                    // Change button color when selected
                    my_button41.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(41));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button42:
                if (my_button42.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button42.setSelected(false);
                    // Reset button color to default
                    my_button42.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(42));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button42.setSelected(true);
                    // Change button color when selected
                    my_button42.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(42));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button43:
                if (my_button43.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button43.setSelected(false);
                    // Reset button color to default
                    my_button43.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(43));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button43.setSelected(true);
                    // Change button color when selected
                    my_button43.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(43));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button44:
                if (my_button44.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button44.setSelected(false);
                    // Reset button color to default
                    my_button44.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(44));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button44.setSelected(true);
                    // Change button color when selected
                    my_button44.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(44));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button45:
                if (my_button45.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button45.setSelected(false);
                    // Reset button color to default
                    my_button45.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(45));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button45.setSelected(true);
                    // Change button color when selected
                    my_button45.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(45));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button46:
                if (my_button46.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button46.setSelected(false);
                    // Reset button color to default
                    my_button46.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(46));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button46.setSelected(true);
                    // Change button color when selected
                    my_button46.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(46));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button47:
                if (my_button47.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button47.setSelected(false);
                    // Reset button color to default
                    my_button47.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(47));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button47.setSelected(true);
                    // Change button color when selected
                    my_button47.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(47));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button48:
                if (my_button48.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button48.setSelected(false);
                    // Reset button color to default
                    my_button48.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(48));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button48.setSelected(true);
                    // Change button color when selected
                    my_button48.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(48));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button49:
                if (my_button49.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button49.setSelected(false);
                    // Reset button color to default
                    my_button49.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(49));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button49.setSelected(true);
                    // Change button color when selected
                    my_button49.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(49));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button50:
                if (my_button50.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button50.setSelected(false);
                    // Reset button color to default
                    my_button50.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(50));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button50.setSelected(true);
                    // Change button color when selected
                    my_button50.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(50));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button51:
                if (my_button51.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button51.setSelected(false);
                    // Reset button color to default
                    my_button51.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(51));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button51.setSelected(true);
                    // Change button color when selected
                    my_button51.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(51));
                    Log.d("TN", String.valueOf(list));
                }
                break;
            case R.id.my_button52:
                if (my_button52.isSelected()) {
                    // Button is already selected, so deselect it
                    my_button52.setSelected(false);
                    // Reset button color to default
                    my_button52.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    list.remove(String.valueOf(52));
                    Log.d("TN", String.valueOf(list));
                } else {
                    // Button is not selected, so select it
                    my_button52.setSelected(true);
                    // Change button color when selected
                    my_button52.setBackgroundColor(getResources().getColor(R.color.red));
                    list.add(String.valueOf(52));
                    Log.d("TN", String.valueOf(list));
                }
                break;
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