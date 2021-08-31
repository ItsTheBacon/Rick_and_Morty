package com.example.rickandmorty.ui.fragments.episods.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentEpisodsDetailBinding;
import com.example.rickandmorty.ui.fragments.character.detail.CharterDetailFragmentArgs;
import com.example.rickandmorty.ui.fragments.episods.EpisodsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EpisodsDetailFragment extends BaseFragment<FragmentEpisodsDetailBinding, EpisodsViewModel> {
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(requireActivity()).get(EpisodsViewModel.class);
        binding = FragmentEpisodsDetailBinding.inflate(getLayoutInflater(), container, false);
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
        viewModel.fetchId(id).observe(getViewLifecycleOwner(), episods -> {
            binding.itemName.setText(episods.getName());
            binding.itemAirDate.setText(episods.getAir_date());
            binding.itemEpisode.setText(episods.getEpisode());
        });
    }

    private void setupId() {
        id = CharterDetailFragmentArgs.fromBundle(getArguments()).getId();
    }
}