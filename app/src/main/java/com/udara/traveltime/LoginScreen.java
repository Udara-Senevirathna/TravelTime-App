package com.udara.traveltime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity implements Shaker.OnShakeListener {

    EditText username;
    EditText password;
    Button loginButton;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Shaker mShaker;

    DatabaseHelper MyDataDB;
    Boolean checkuserpass, admincheckuserpass;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // Get the SensorManager
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get the accelerometer sensor
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // DB connection
        MyDataDB = new DatabaseHelper(this);

        // Create a new ShakeDetector instance
        mShaker = new Shaker();
        mShaker.setOnShakeListener(this);

        loginButton = findViewById(R.id.loginButton);
        TextView sing_up = (TextView) findViewById(R.id.signupText);


        // initialize the firebase
        firebaseAuth = FirebaseAuth.getInstance();

        final EditText username = findViewById(R.id.username);
        final EditText passwd = findViewById(R.id.password1);

        sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, SignupScreen.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            // todo uncomment this.
                String user = username.getText().toString().trim();
                String pass = passwd.getText().toString().trim();
                Boolean result = MyDataDB.checkEmailAllReadyReg(user);
                if (user.equals("") || pass.equals("")){
                    username.setError("Fill this Field");
                    passwd.setError("Fill this Field");
                }
                else{
//                    checkuserpass = MyDataDB.checkusernamepassword(user, pass);
//                    admincheckuserpass = MyDataDB.checkadminusernamepassword(user, pass);
//                    if (checkuserpass){
//                        Toast.makeText(LoginScreen.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
//                        Intent intent= new Intent(getApplicationContext(), RouteSearchScreen.class);
//                        startActivity(intent);
//                    }else if(admincheckuserpass) {
//                        Toast.makeText(LoginScreen.this, "LogIn As admin", Toast.LENGTH_SHORT).show();
//                        Intent intent= new Intent(getApplicationContext(), AdminDashBordNavipanel.class);
//                        startActivity(intent);
//                    }
//                    else{
//                        Toast.makeText(LoginScreen.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
//                    }

                    // firebase login
                    userLogin(user, pass);
                }
            }
        });
    }

    private void userLogin(String user, String pass) {
        FirebaseAuth  auth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    if(firebaseUser.isEmailVerified()){
                        Toast.makeText(LoginScreen.this, "You've log in", Toast.LENGTH_SHORT).show();

                    }else {

                        // sent verified link to the email address.
                        firebaseUser.sendEmailVerification();
                        firebaseAuth.signOut();

                        AlertDialog();
                    }

                    Toast.makeText(LoginScreen.this, firebaseUser.toString(), Toast.LENGTH_SHORT).show();

                    // go to the main screen
                    Intent intent = new Intent(LoginScreen.this, RouteSearchScreen.class);
                    startActivity(intent);


                }else{
                    // catch exception that trow from the firebase
                    try{
                        throw task.getException();

                    }catch (FirebaseAuthInvalidUserException e){
                        username.setError("User does not exits or not valid, please try again");
                        username.requestFocus();

                    }catch (FirebaseAuthInvalidCredentialsException e){
                        username.setError("Invalid Credentials");
                        username.requestFocus();
                    }catch (Exception e) {
                        Toast.makeText(LoginScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }

    private void AlertDialog() {
        // setup the alert message
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
        builder.setTitle("Email Not Verified");
        builder.setMessage("Please verify your email now. You can't login without email verification.");

        // open Email apps if user clicked on continue button
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // sent email to the email address that account has been registered with
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // create the Alert Dialog
        AlertDialog alertDialog = builder.create();

        // show dialog box

        alertDialog.show();

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null){

            Toast.makeText(LoginScreen.this, "Already login", Toast.LENGTH_SHORT).show();

            // go to the user profile

            Intent intent = new Intent(LoginScreen.this, RouteSearchScreen.class);
            startActivity(intent);
            finish(); // prevent from the back to the login page.
        }else{
            Toast.makeText(LoginScreen.this, "you can log in now", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Register the ShakeDetector
        mSensorManager.registerListener(mShaker, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        // Unregister the ShakeDetector
        mSensorManager.unregisterListener(mShaker);
        super.onPause();
    }

    @Override
    public void onShake(int count) {
        // Do whatever you want when the phone is shaken
        // For example, close the activity
        finish();
    }
}