package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStarted extends AppCompatActivity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the layout xml file
        setContentView(R.layout.activity_get_started);

        startButton = findViewById(R.id.startButton); //Getting the button data
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetStarted.this, LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}