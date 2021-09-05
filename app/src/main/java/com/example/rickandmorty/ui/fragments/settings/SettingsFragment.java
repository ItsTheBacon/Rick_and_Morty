package com.example.rickandmorty.ui.fragments.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.FragmentSettingsBinding;
import com.example.rickandmorty.ui.activity.MainActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(LayoutInflater.from(getContext()), container, false);
        setupDarkMode();
        return binding.getRoot();
    }

    private void setupDarkMode() {
        binding.switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(requireContext(), "Theme dark", Toast.LENGTH_SHORT).show();
                    binding.switchMaterial.isChecked();
//                    actionBar.setBackgroundDrawable(R.drawable.ic_icons8_rick_sanchez);

                } else {
                    Toast.makeText(requireContext(), "Theme light", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}