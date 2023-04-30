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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.udara.traveltime.databinding.ActivityAdminDashBordNavipanelBinding;
import com.udara.traveltime.databinding.ActivityMainBinding;
import com.udara.traveltime.databinding.ActivityRouteSearchScreenBinding;
import com.udara.traveltime.fragment.BusRegister;
import com.udara.traveltime.fragment.HomeFragment;
import com.udara.traveltime.fragment.MapsFragment;
import com.udara.traveltime.fragment.RoutesRegister;
import com.udara.traveltime.fragment.SettingFragment;

public class AdminDashBordNavipanel extends AppCompatActivity {

    ActivityAdminDashBordNavipanelBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDashBordNavipanelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ReplaceFragment(new BusRegister());

        binding.navBottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.add_bus:
                        ReplaceFragment(new BusRegister());
                        break;

                    case R.id.add_routes:
                        ReplaceFragment(new RoutesRegister());
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
