package com.example.rickandmorty.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpNavigation();
    }
    public void setUpNavigation(){
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.charterFragment,
                R.id.episodsFragment,
                R.id.locationFragment)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.getNavController(), appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.getNavController());

    }
}