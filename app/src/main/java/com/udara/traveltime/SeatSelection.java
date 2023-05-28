package com.udara.traveltime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.util.Calendar;

public class SeatSelection extends AppCompatActivity {

    Button ConfirmBtn;
    TextView depatureTitle, arrivalTitle,tmptext;
    FirebaseAuth firebaseAuth;
    String SeatSlected;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        ConfirmBtn = findViewById(R.id.ConfirmBtn);



        depatureTitle = findViewById(R.id.depature_location_id);
        arrivalTitle = findViewById(R.id.arrival_location_id);

        tmptext = findViewById(R.id.textId);

        final String getDeparture = getIntent().getStringExtra("Departure");
        final String getArrival = getIntent().getStringExtra("Arrival");
        final String ButtonId = getIntent().getStringExtra("ButtonId");
        String buttonClickedId = getIntent().getStringExtra("ButtonClickedId");


        // set the title to the header
        depatureTitle.setText(getDeparture);
        arrivalTitle.setText(String.valueOf(ButtonId));

        tmptext.setText(ButtonId);
        Toast.makeText(this,String.valueOf(buttonClickedId), Toast.LENGTH_SHORT).show();

        // selected seat no


        // go to the payment page
        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(SeatSlected)){
                    Toast.makeText(SeatSelection.this, "Seat not selected", Toast.LENGTH_SHORT).show();
                }else{

                    MakeBookingSeat(SeatSlected, buttonClickedId);
                }


                Intent intent = new Intent(SeatSelection.this, PaymentDetailsScreen.class);
                startActivity(intent);
            }
        });
    }

    private void MakeBookingSeat(String seatSelected, String routeId) {
        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        // Get current date and time
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(calendar.getTime());
        String formattedTime = timeFormat.format(calendar.getTime());


        // Create an instance of WriteMakeBooking with the selected seat, user ID, and route ID
        WriteMakeBooking makeBooking = new WriteMakeBooking(seatSelected, firebaseUser.getUid(), routeId, formattedDate , formattedTime);

        // Get reference to the "BookingSeat" node in the database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BookingSeat");

        // Generate a unique key for the booking
        String bookingKey = databaseReference.push().getKey();

        // Store the booking under the generated key
        databaseReference.child(bookingKey).setValue(makeBooking)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
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