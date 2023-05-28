package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class PaymentMethod extends AppCompatActivity {

    EditText cardNumber, holderName, cvv, exdate;
    Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        cardNumber = findViewById(R.id.cardName2);
        holderName = findViewById(R.id.cardNumber);
        cvv = findViewById(R.id.Code);
        exdate = findViewById(R.id.ExpireDate);


        payBtn = findViewById(R.id.PayButton);

    }
}