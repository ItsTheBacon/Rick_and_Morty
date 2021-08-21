package com.example.rickandmorty.ui.fragments.character.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentCharterDetail2Binding;
import com.example.rickandmorty.ui.fragments.character.CharterViewModel;

public class CharterDetailFragment extends BaseFragment<FragmentCharterDetail2Binding, CharterViewModel> {
    int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(requireActivity()).get(CharterViewModel.class);
        binding = FragmentCharterDetail2Binding.inflate(getLayoutInflater(), container, false);
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
        viewModel.fetchCharacter(id).observe(getViewLifecycleOwner(), character -> {
            Glide.with(binding.imageDetailfrag)
                    .load(character.getImage())
                    .into(binding.imageDetailfrag);
            binding.txtTitle.setText(character.getName());
            binding.txtDescription.setText(character.getGender());

        });
    }

    private void setupId() {
        id = CharterDetailFragmentArgs.fromBundle(getArguments()).getId();
    }
}