package com.example.rickandmorty.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.ActivityMainBinding;
import com.example.rickandmorty.ui.fragments.settings.SettingsFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(getDrawable(R.drawable.dark_mode));
        setUpNavigation();
    }

    public void setUpNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.charterFragment,
                R.id.episodsFragment,
                R.id.settingsFragment,
                R.id.locationFragment)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.getNavController(), appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.getNavController());

    }

}