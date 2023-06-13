package com.udara.traveltime;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupScreen extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the layout xml file
        setContentView(R.layout.activity_signup_screen);
        auth = FirebaseAuth.getInstance();

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

                    registerUser(getFirstName, getLastName, getNIC, getEmail, getpass);

                }
            }
        });
    }
    private void registerUser(String getFirstName, String getLastName, String getNIC, String getEmail, String getpass) {
        auth = FirebaseAuth.getInstance();
        // create new user
        auth.createUserWithEmailAndPassword(getEmail, getpass).addOnCompleteListener(SignupScreen.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupScreen.this, "Enter to the register section", Toast.LENGTH_SHORT).show();
                    // get current user id.
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    if (firebaseUser != null) {
                        // get reference from the database "register users"
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RegUsers");

                        boolean isAdmin = false; // Set isAdmin based on user role, e.g., through user input or condition check
//                        boolean isAdmin = true;
                        // get the data and use these data store in the real-time database in Firebase.
                        RWDataToFirebas writeReadFirebase = new RWDataToFirebas(getFirstName, getLastName, getNIC, firebaseUser.getUid(), getEmail, isAdmin);

                        databaseReference.child(firebaseUser.getUid()).setValue(writeReadFirebase).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignupScreen.this, "User Registered Successful", Toast.LENGTH_SHORT).show();
                                    // send email verification
                                    firebaseUser.sendEmailVerification();
                                    // open user profile after registration
                                    Intent intent = new Intent(SignupScreen.this, LoginScreen.class);
                                    // prevent the user from going back to the registration screen after registration.
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);
                                    finish(); // close the registration activity.
                                } else {
                                    Toast.makeText(SignupScreen.this, "User Registered not Successful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(SignupScreen.this, "User Registration failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupScreen.this, "User Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() { // indicate database errors.
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}