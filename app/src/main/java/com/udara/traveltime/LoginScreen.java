package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity implements Shaker.OnShakeListener {

    EditText username;
    EditText password;
    Button loginButton;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Shaker mShaker;

    DatabaseHelper MyDataDB;
    Boolean checkuserpass, admincheckuserpass;

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
                    checkuserpass = MyDataDB.checkusernamepassword(user, pass);
                    admincheckuserpass = MyDataDB.checkadminusernamepassword(user, pass);
                    if (checkuserpass){
                        Toast.makeText(LoginScreen.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(), RouteSearchScreen.class);
                        startActivity(intent);
                    }else if(admincheckuserpass) {
                        Toast.makeText(LoginScreen.this, "LogIn As admin", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(), AdminDashBordNavipanel.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginScreen.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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