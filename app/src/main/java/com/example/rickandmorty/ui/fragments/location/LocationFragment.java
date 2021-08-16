package com.example.rickandmorty.ui.fragments.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.rickandmorty.R;
import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentLocationBinding;
import com.example.rickandmorty.ui.adapters.locationadapter.LocationAdapter;
import com.example.rickandmorty.ui.fragments.episods.EpisodsFragmentDirections;
import com.example.rickandmorty.utils.OnItemClickListener;

public class LocationFragment extends BaseFragment<FragmentLocationBinding, LocationViewModel> {

    LocationAdapter adapter = new LocationAdapter();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(requireActivity()).get(LocationViewModel.class);

        binding = FragmentLocationBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupRequest() {
        super.setupRequest();
        viewModel.fetchLocation().observe(getViewLifecycleOwner(), locationRickAndMoryResponse ->
                adapter.addlist(locationRickAndMoryResponse.getResults()));
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        setupRecycler();
    }

    @Override
    protected void setupListener() {
        super.setupListener();
        setOnClikcListener();
    }
    private void setOnClikcListener() {
        adapter.setItemClickList(new OnItemClickListener() {
            @Override
            public void OnClickListener(int id) {
                Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                        .navigate(LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment().setId(id));
            }

            @Override
            public void OnLongListener(int id) {

            }
        });
    }

    private void setupRecycler() {
        binding.rvLocation.setAdapter(adapter);
    }
}