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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udara.traveltime.fragment.BusRegisterFragment;

public class LoginScreen extends AppCompatActivity implements Shaker.OnShakeListener {

    EditText username;
    EditText password;
    Button loginButton;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Shaker mShaker;

    DatabaseHelper MyDataDB;
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
        TextView signUp = findViewById(R.id.signupText);

        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password1);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, SignupScreen.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginScreen.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    userLogin(user, pass);
                }
            }
        });
    }

    private void userLogin(String user, String pass) {
        firebaseAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                    if (firebaseUser != null) {
                        if (firebaseUser.isEmailVerified()) {
                            checkAdminStatus(firebaseUser.getUid());
                        } else {
                            firebaseAuth.signOut();
                            showEmailNotVerifiedDialog();
                        }
                    }
                } else {
                    // Catch exception that is thrown from Firebase
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        username.setError("User does not exist or is not valid, please try again");
                        username.requestFocus();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        username.setError("Invalid Credentials");
                        username.requestFocus();
                    } catch (Exception e) {
                        Toast.makeText(LoginScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void checkAdminStatus(String userId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RegUsers").child(userId);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    boolean isAdmin = snapshot.child("isAdmin").getValue(Boolean.class);
                    if (isAdmin) {
                        Toast.makeText(LoginScreen.this, "Admin user logged in", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginScreen.this, AdminDashBordNavipanel.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginScreen.this, "Regular user logged in", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginScreen.this, RouteSearchScreen.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginScreen.this, "Failed to read user data", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showEmailNotVerifiedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
        builder.setTitle("Email Not Verified");
        builder.setMessage("Please verify your email. You cannot login without email verification.");

        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.isEmailVerified()) {
                Toast.makeText(LoginScreen.this, "Already logged in", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginScreen.this, RouteSearchScreen.class);
                startActivity(intent);
                finish();
            } else {
                firebaseAuth.signOut();
                Toast.makeText(LoginScreen.this, "Please verify your email address", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginScreen.this, "You can log in now", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShaker, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShaker);
        super.onPause();
    }

    @Override
    public void onShake(int count) {
        finish();
    }

}
