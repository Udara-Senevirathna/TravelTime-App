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
import android.widget.Toast;

public class SignupScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the layout xml file
        setContentView(R.layout.activity_signup_screen);

        final EditText FirstName = findViewById(R.id.FirstName);
        final EditText LastName = findViewById(R.id.LastName);
        final EditText NIC = findViewById(R.id.NIC);
        final EditText Email = findViewById(R.id.Email);

        Button signupButton = findViewById(R.id.signupButton);//Getting the button data
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



                // keep data tmp until the verification has been completed
                final String getFirstName = FirstName.getText().toString();
                final String getLastName = LastName.getText().toString();
                final String getNIC = NIC.getText().toString();
                final String getEmail = Email.getText().toString();

                if (getFirstName.equals("") ||getLastName.equals("")||getNIC.equals("")||getEmail.equals(""))
                    Toast.makeText(SignupScreen.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else {

                    Intent intent = new Intent(SignupScreen.this, OTPScreen.class);

                    intent.putExtra("f_name", getFirstName);
                    intent.putExtra("l_name", getLastName);
                    intent.putExtra("nic", getNIC);
                    intent.putExtra("email", getEmail);
                    startActivity(intent);
                }
            }
        });
    }
}