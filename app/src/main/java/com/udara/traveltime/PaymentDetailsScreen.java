package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class PaymentDetailsScreen extends AppCompatActivity {

    EditText Name;
    EditText passportNum;
    EditText EmailAddress;
    EditText mobileNum;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details_screen);
    }
}