package com.example.rickandmorty.ui.fragments.location;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.rickandmorty.R;
import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentLocationBinding;
import com.example.rickandmorty.models.location.Location;
import com.example.rickandmorty.ui.adapters.LocationAdapter;
import com.example.rickandmorty.utils.OnItemClickListener;

import java.util.ArrayList;

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
    protected void setupViews() {
        super.setupViews();
        setupRecycler();
    }

    @Override
    protected void setupListener() {
        super.setupListener();
        setOnClikcListener();
    }

    @Override
    protected void setupRequest() {
        super.setupRequest();
        if (isInternetConnection()) {
            viewModel.fetchLocation().observe(getViewLifecycleOwner(), characters -> {
                if (characters != null) {
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    adapter.addlist(characters.getResults());
                }
            });
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            adapter.addlist((ArrayList<Location>) viewModel.getLocations());
        }
    }


    private void setOnClikcListener() {
        adapter.setItemClickList(new OnItemClickListener() {
            @Override
            public void OnClickListener(int id) {
                Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                        .navigate(LocationFragmentDirections
                                .actionLocationFragmentToLocationDetailFragment()
                                .setId(id));
            }

            @Override
            public void OnLongListener(int id) {

            }
        });
    }

    public boolean isInternetConnection() {
        ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    private void setupRecycler() {
        binding.rvLocation.setAdapter(adapter);
    }
}