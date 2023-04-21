package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupScreen extends AppCompatActivity {

    EditText FirstName;
    EditText LastName;
    EditText NIC;
    EditText Email;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the layout xml file
        setContentView(R.layout.activity_signup_screen);

        signupButton = findViewById(R.id.signupButton);//Getting the button data
        //Getting the text data
        TextView login = (TextView) findViewById(R.id.loginsText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupScreen.this, OTPScreen.class);
                startActivity(intent);
            }
        });
    }
}