package com.example.app_combined.info;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_combined.MainActivity2;

import com.example.app_combined.R;
import com.example.app_combined.databinding.ActivityMainBinding;
import com.example.app_combined.frags.Profile;
import com.example.app_combined.frags.pose;
import com.example.app_combined.frags.stats;
import com.example.app_combined.frags.work;
import com.example.app_combined.model.AuthResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        Intent deliveredIntent = getIntent();
        String accessToken = deliveredIntent.getStringExtra("accessToken");
        String tokenType = deliveredIntent.getStringExtra("tokenType");
        AuthResponse authResponse = new AuthResponse(accessToken,tokenType);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        replaceFragment(new pose());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_stat) {
                replaceFragment(new stats());
            } else if (itemId == R.id.nav_work) {
                // MainActivity로 이동하는 Intent 생성
                Intent intent = new Intent(this, MainActivity2.class);
                // MainActivity로 전환
                startActivity(intent);
            } else if (itemId == R.id.nav_pose) {
                // MainActivity로 이동하는 Intent 생성
                Intent intent = new Intent(this, MainActivity2.class);
                // MainActivity로 전환
                startActivity(intent);
            }else if(itemId == R.id.nav_profile){
                replaceFragment(new Profile());
            }

            return true;
        });



    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}