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
        final EditText pass = findViewById(R.id.Password);
        final EditText passC = findViewById(R.id.Password2);

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
                final String getpass = pass.getText().toString();
                final String getpassC = passC.getText().toString();

                if (getFirstName.equals("") ||getLastName.equals("")||getNIC.equals("")||getEmail.equals("") || getpass.equals("")) {
                    FirstName.setError("Fill this field");
                    LastName.setError("Fill this field");
                    NIC.setError("Fill this field");
                    Email.setError("Fill this field");
                    pass.setError("Fill this field");
                }else if(!getpass.equals(getpassC)){
                    pass.setError("Password is didn't match");
                    passC.setError("Password is didn't match");
                } else if (getpass.length() <= 7){
                    pass.setError("Password is short ");
                } else if (!(getNIC.trim().length() == 10) && (!getNIC.trim().endsWith("V") || !getNIC.trim().endsWith("v"))) {
                    NIC.setError("NIC invalid");
                }

                else {
                    Intent intent = new Intent(SignupScreen.this, OTPScreen.class);
                    intent.putExtra("f_name", getFirstName);
                    intent.putExtra("l_name", getLastName);
                    intent.putExtra("nic", getNIC);
                    intent.putExtra("email", getEmail);
                    intent.putExtra("pass", getpass);
                    startActivity(intent);
                }
            }
        });
    }
}