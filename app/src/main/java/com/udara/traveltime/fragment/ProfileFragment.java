package com.udara.traveltime.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.udara.traveltime.LoginScreen;
import com.udara.traveltime.R;

public class ProfileFragment extends Fragment {
    TextView username, email;
    Button signOut;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        username = view.findViewById(R.id.textView);
        email = view.findViewById(R.id.textView2);
        signOut = view.findViewById(R.id.singout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            String userEmail = firebaseUser.getEmail();
            username.setText(userEmail);
            email.setText(userEmail);
        }

        String userId = firebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RegUsers");
        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String mail = snapshot.child("email").getValue(String.class);
                    String firstName = snapshot.child("firstName").getValue(String.class);
                    String lastName = snapshot.child("lastName").getValue(String.class);

                    String fullName = firstName + " " + lastName;
                    username.setText(fullName);
                    email.setText(mail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if the read operation is canceled
                Toast.makeText(getContext(), "Failed to read user data", Toast.LENGTH_SHORT).show();
            }
        });


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseUser != null) {
                    firebaseAuth.signOut();
                    Intent intent = new Intent(requireActivity(), LoginScreen.class);
                    startActivity(intent);
                    requireActivity().finish(); // Close the current activity
                }
            }
        });

        return view;
    }
}
