package com.example.rickandmorty.ui.fragments.location.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentLocationDetailBinding;
import com.example.rickandmorty.ui.fragments.character.detail.CharterDetailFragmentArgs;
import com.example.rickandmorty.ui.fragments.location.LocationViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationDetailFragment extends BaseFragment<FragmentLocationDetailBinding, LocationViewModel> {

    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        binding = FragmentLocationDetailBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        setupId();
    }

    @Override
    protected void setUpObserves() {
        super.setUpObserves();
        viewModel.fetchId(id).observe(getViewLifecycleOwner(), location -> {
            binding.itemName.setText(location.getName());
            binding.itemAirDate.setText(location.getType());
            binding.itemEpisode.setText(location.getDimension());
        });
    }

    private void setupId() {
        id = CharterDetailFragmentArgs.fromBundle(getArguments()).getId();
    }
}