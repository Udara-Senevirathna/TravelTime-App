package com.udara.traveltime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.udara.traveltime.databinding.ActivityMainBinding;
import com.udara.traveltime.databinding.ActivityRouteSearchScreenBinding;
import com.udara.traveltime.fragment.HomeFragment;
import com.udara.traveltime.fragment.MapsFragment;
import com.udara.traveltime.fragment.SettingFragment;

public class RouteSearchScreen extends AppCompatActivity {

    ActivityRouteSearchScreenBinding binding;

    EditText DepartureLocation;
    EditText ArrivalLocation;
    EditText TravelDate;
    EditText Time;
    Button Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRouteSearchScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ReplaceFragment(new HomeFragment());

        binding.navBottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        ReplaceFragment(new HomeFragment());
                        break;

                    case R.id.bottom_location:
                        ReplaceFragment(new MapsFragment());
                        break;


                    case R.id.bottom_setting:
                        ReplaceFragment(new SettingFragment());
                        break;

                }
                return true;
            }
        });
    }
    private void ReplaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();



    }
}