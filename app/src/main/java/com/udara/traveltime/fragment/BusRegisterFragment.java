package com.udara.traveltime.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udara.traveltime.BusRegisterWithFireBase;
import com.udara.traveltime.R;

public class BusRegisterFragment extends Fragment {
    EditText Bus_No;
    EditText DriverName;
    EditText NIC_NO;
    EditText Sheet;
    Button regButton;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bus_register, container, false);
        // Find the TextView with ID text_view and assign it to a variable

        Bus_No = view.findViewById(R.id.Bus_No);
        DriverName = view.findViewById(R.id.DriverName);
        NIC_NO = view.findViewById(R.id.NIC_NO);
        Sheet = view.findViewById(R.id.Sheet);
        regButton = view.findViewById(R.id.regButton);


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getBusNo = Bus_No.getText().toString();
                final String getDriverName = DriverName.getText().toString();
                final String getNIC_NO = NIC_NO.getText().toString();
                final String getSheet = Sheet.getText().toString();

                if(TextUtils.isEmpty(getBusNo) || TextUtils.isEmpty(getDriverName) || TextUtils.isEmpty(getNIC_NO) || TextUtils.isEmpty(getSheet)){
                    Toast.makeText(getContext(), "Empty fields", Toast.LENGTH_SHORT).show();
                }else {
                    if ((getNIC_NO.length() <= 9)){
                        Toast.makeText(getContext(), "INC is Wrong", Toast.LENGTH_SHORT).show();
                    }else {

                        // add data to the database
                        RegisterBus(getBusNo, getDriverName, getNIC_NO, getSheet);

                    }
                }
            }
        });
        return view;
    }

    private void RegisterBus(String getBusNo, String getDriverName, String getNIC_no, String getSheet) {
        // firebase initialization
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        BusRegisterWithFireBase busRegisterWithFireBase = new BusRegisterWithFireBase(firebaseUser.getUid(),getBusNo, getDriverName, getNIC_no, getSheet);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Register Bus");
        databaseReference.child(getBusNo).setValue(busRegisterWithFireBase).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(), "User Registered Successful", Toast.LENGTH_SHORT).show();
                    // add code to here . after registration of the bus
                }
            }
        }).addOnFailureListener(new OnFailureListener() { // indicate database errors.
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}