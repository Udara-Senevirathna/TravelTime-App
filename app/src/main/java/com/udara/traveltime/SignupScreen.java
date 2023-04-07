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
        setContentView(R.layout.activity_signup_screen);

        signupButton = findViewById(R.id.signupButton);
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
                Intent intent = new Intent(SignupScreen.this, RouteSearchScreen.class);
                startActivity(intent);
            }
        });
//        WindowCompat.setDecorFitsSystemWindows(getWindow(),false);
//        setContentView(R.layout.activity_main);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SignupScreen.this,RouteSearchScreen.class));
//                finish();
//            }
//        }, 3800);
    }
}