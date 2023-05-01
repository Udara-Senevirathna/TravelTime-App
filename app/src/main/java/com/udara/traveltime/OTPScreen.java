package com.udara.traveltime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OTPScreen extends AppCompatActivity {
    EditText OTPBox1, OTPBox2, OTPBox3, OTPBox4;
    TextView resentBTN;
    AppCompatButton verifyBTN;

    // true after every second
    private boolean resendEnable = false;

    // resent time in second
    private int resentTime = 60;
    private int selectedEtPostion  = 0;
    private  Boolean register_user;

    DatabaseHelper MyDataDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpscreen);

        verifyBTN = findViewById(R.id.verifyBTN);

        MyDataDB = new DatabaseHelper(this);


        OTPBox1 = (EditText) findViewById(R.id.OTPbox1);
        OTPBox2 = (EditText) findViewById(R.id.OTPbox2);
        OTPBox3 = (EditText) findViewById(R.id.OTPbox3);
        OTPBox4 = (EditText) findViewById(R.id.OTPbox4);

        resentBTN = (TextView) findViewById(R.id.resentBtn);

        final Button verifyBTN = findViewById(R.id.verifyBTN);
        final TextView otpEmail = (TextView) findViewById(R.id.otpEmail);
        final TextView otpMobile = (TextView) findViewById(R.id.otpMobile);

        // getting email and phone number
//        final String getEmail = getIntent().getStringExtra("Email");
//        final String getPhone = getIntent().getStringExtra("mobile");



// getting email and phone number
        final String getFname = getIntent().getStringExtra("f_name");
        final String getLname = getIntent().getStringExtra("l_name");
        final String getNIC = getIntent().getStringExtra("nic");
        final String get_Email = getIntent().getStringExtra("email");
        final String get_pass = getIntent().getStringExtra("pass");


        // setting email and mobile to textView
        otpEmail.setText(get_Email);
//        otpMobile.setText(getPhone);

        OTPBox1.addTextChangedListener(textWatcher);
        OTPBox2.addTextChangedListener(textWatcher);
        OTPBox3.addTextChangedListener(textWatcher);
        OTPBox4.addTextChangedListener(textWatcher);

        // default open keyboard at OPTcode
        showKeyboard(OTPBox1);

        // start resent time count downer
        startCountDownTimer();
        resentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resendEnable){
                    // handle your resend code here

                    // start new resent count down timer
                    startCountDownTimer();
                }
            }
        });

        verifyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String generateOTP = OTPBox1.getText().toString() + OTPBox2.getText().toString() + OTPBox3.getText().toString() + OTPBox4.getText().toString();

                if (generateOTP.length()  == 4){

                    register_user = MyDataDB.checkusername(get_Email);

                    if (!register_user){
                        Boolean insert = MyDataDB.insertData(getFname,getLname,get_Email,getNIC,get_pass);
                        if (insert){
                            Toast.makeText(OTPScreen.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), RouteSearchScreen.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(OTPScreen.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(OTPScreen.this, "User Already exists please signing", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SignupScreen.class);
                        startActivity(intent);
                    }


                }
//
            }
        });
    }

    private void startCountDownTimer(){
        resendEnable = false;
        resentBTN.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resentTime * 1000 ,1000){

            // Activate Resent code again
            @Override
            public void onTick(long millisUntilFinished){
                resentBTN.setText("Resend Code (" + (millisUntilFinished / 1000) + ")");
            }

            //Showing the Resent code
            @Override
            public void onFinish(){
                resendEnable = true;
                resentBTN.setText("Resend Code");
                resentBTN.setTextColor(getResources().getColor(R.color.purple_500));
            }
        }.start();
    }

    //Get OTP code
    private void showKeyboard(EditText OTPCODE){
        OTPCODE.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(OTPCODE, InputMethodManager.SHOW_IMPLICIT);
    }
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(editable.length() > 0 ){
                if(selectedEtPostion == 0){
                    selectedEtPostion  = 1;
                    showKeyboard(OTPBox2);

                }else if(selectedEtPostion == 1){

                    selectedEtPostion  = 2;
                    showKeyboard(OTPBox3);

                }else if(selectedEtPostion == 2){
                    selectedEtPostion  = 3;
                    showKeyboard(OTPBox4);
                }
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_DEL){

            if(selectedEtPostion == 3){
                selectedEtPostion = 2;
                showKeyboard(OTPBox3);
            }else if(selectedEtPostion == 2){

                selectedEtPostion = 1;
                showKeyboard(OTPBox2);
            }else if(selectedEtPostion  == 1){
                selectedEtPostion = 0;
                showKeyboard(OTPBox1);
            }

            return true;

        }else{
            return super.onKeyUp(keyCode, event);
        }
    }
}