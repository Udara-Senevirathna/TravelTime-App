package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SeatSelection extends AppCompatActivity {

    Button ConfirmBtn;
    TextView depatureTitle, arrivalTitle,tmptext;

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
        final String ButtonId = getIntent().getStringExtra("buttonId");


        // set the title to the header
        depatureTitle.setText(getDeparture);
        arrivalTitle.setText(String.valueOf(ButtonId));

        tmptext.setText(ButtonId);
        Toast.makeText(this,String.valueOf(ButtonId), Toast.LENGTH_SHORT).show();
        // go to the payment page
        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeatSelection.this, PaymentDetailsScreen.class);
                startActivity(intent);
            }
        });
    }
}