package com.example.rickandmorty.ui.fragments.location;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentLocationBinding;
import com.example.rickandmorty.models.RickAndMoryResponse;
import com.example.rickandmorty.models.location.Location;
import com.example.rickandmorty.ui.adapters.LocationAdapter;
import com.example.rickandmorty.utils.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationFragment extends BaseFragment<FragmentLocationBinding, LocationViewModel> {

    LocationAdapter adapter = new LocationAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int postVisibleItems;

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
    protected void setupListeners() {
        super.setupListeners();
        setOnClikcListener();
    }

    @Override
    protected void setupRequests() {
        super.setupRequests();
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
        binding.rvLocation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    postVisibleItems = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    if ((visibleItemCount + postVisibleItems) >= totalItemCount) {
                        viewModel.page++;
                        viewModel.fetchLocation().observe(getViewLifecycleOwner(), new Observer<RickAndMoryResponse<Location>>() {
                            @Override
                            public void onChanged(RickAndMoryResponse<Location> location) {
                                adapter.addlist(location.getResults());
                            }
                        });
                        binding.progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }


    private void setOnClikcListener() {
        adapter.setItemClickList(new OnItemClickListener() {
            @Override
            public void OnClickListener(int id) {
                if (isInternetConnection()) {
                    Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                            .navigate((NavDirections) LocationFragmentDirections
                                    .actionLocationFragmentToLocationDetailFragment()
                                    .setId(id));
                }else{
                    Toast.makeText(requireContext(), "Not Internet Connection", Toast.LENGTH_SHORT).show();
                }
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
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvLocation.setLayoutManager(linearLayoutManager);
        binding.rvLocation.setAdapter(adapter);
    }
}