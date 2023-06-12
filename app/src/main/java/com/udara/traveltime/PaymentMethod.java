package com.udara.traveltime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentMethod extends AppCompatActivity {

    EditText cardNumber, holderName, cvv, exdate;
    Button payBtn;

    FirebaseAuth firebaseAuth;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);


        cardNumber = findViewById(R.id.cardName2);
        holderName = findViewById(R.id.cardNumber);
        cvv = findViewById(R.id.Code);
        exdate = findViewById(R.id.ExpireDate);


        payBtn = findViewById(R.id.PayButton);

        list = getIntent().getStringArrayListExtra("SeatSelectedList");
        String buttonClickedId = getIntent().getStringExtra("BookingID");

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cardNo = cardNumber.getText().toString();
                String holdName = holderName.getText().toString();
                String cvvNo = cvv.getText().toString();
                String exDate = exdate.getText().toString();

                if(TextUtils.isEmpty(cardNo)){
                    cardNumber.setError("This filed is required.");
                } else if (TextUtils.isEmpty(holdName)) {
                    holderName.setError("This filed is required.");
                } else if (TextUtils.isEmpty(cvvNo)) {
                    cvv.setError("This filed is required.");
                } else if (TextUtils.isEmpty(exDate)) {
                    exdate.setError("This filed is required.");
                } else{

                    boolean bookingStatus = true;
                    MakeBookingSeat(buttonClickedId, bookingStatus, holdName);

                }

            }
        });

    }

    private boolean issueTicket(String holderName, String bookingID) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PaymentData");
        // Generate a unique key for the booking
        String paymentKey = databaseReference.push().getKey();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("TicketId", paymentKey);
        dataMap.put("uId", paymentKey);
        dataMap.put("BookingId", holderName);
        dataMap.put("PaymentID", firebaseUser.getUid());
        dataMap.put("Price", bookingID);
        dataMap.put("exDate", bookingID);
        dataMap.put("IssueDate", bookingID);

        databaseReference.child(paymentKey).setValue(dataMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PaymentMethod.this, "Payment is successful", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure case
                    }
                });

        return true;
    }



    private boolean makePayment(String holderName, String bookingID) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PaymentData");
        // Generate a unique key for the booking
        String paymentKey = databaseReference.push().getKey();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("paymentKey", paymentKey);
        dataMap.put("name", holderName);
        dataMap.put("uid", firebaseUser.getUid());
        dataMap.put("bookingId", bookingID);

        databaseReference.child(paymentKey).setValue(dataMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PaymentMethod.this, "Payment is successful", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure case
                    }
                });

        return true;
    }



    private void MakeBookingSeat(String routeId, boolean bookingStatus, String holderName) {
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
                                Toast.makeText(PaymentMethod.this, "Write data successfully", Toast.LENGTH_SHORT).show();
                                // Booking stored successfully
                                // You can perform any further actions here
                                makePayment(holderName, bookingKey);
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
}