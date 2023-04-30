package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity {

    Button seatBookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

//        seatBookBtn = findViewById(R.id.seatBookBtn);

//        go to the seat booking page
//        seatBookBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ResultScreen.this, SeatSelection.class);
//                startActivity(intent);
//            }
//        });

    }
}