package com.example.rickandmorty.ui.fragments.episods;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentEpisodsBinding;
import com.example.rickandmorty.models.episods.Episods;
import com.example.rickandmorty.ui.adapters.EpisodsAdapter;
import com.example.rickandmorty.utils.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EpisodsFragment extends BaseFragment<FragmentEpisodsBinding, EpisodsViewModel> {
    private int visibleItemCount;
    private int postVisibleItems;
    private int totalItemCount;
    EpisodsAdapter adapter = new EpisodsAdapter();
    private LinearLayoutManager linearLayoutManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodsViewModel.class);
        binding = FragmentEpisodsBinding.inflate(getLayoutInflater(), container, false);
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
        getEpisods();
    }

    private void getEpisods() {
        if (isInternetConnection()) {
            viewModel.fetchEpisods().observe(getViewLifecycleOwner(), episods -> {
                if (episods != null) {
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    adapter.addlist(episods.getResults());
                }
            });
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            adapter.addlist((ArrayList<Episods>) viewModel.getEpisods());
        }
        binding.rvEpisods.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    postVisibleItems = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    if ((visibleItemCount + postVisibleItems) >= totalItemCount) {
                        viewModel.page++;
                        viewModel.fetchEpisods().observe(getViewLifecycleOwner(), episods -> adapter.addlist(episods.getResults()));
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
                Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                        .navigate(EpisodsFragmentDirections.actionEpisodsFragmentToEpisodsDetailFragment().setId(id));
            }

            @Override
            public void OnLongListener(int id) {

            }
        });
    }

    private void setupRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvEpisods.setLayoutManager(linearLayoutManager);
        binding.rvEpisods.setAdapter(adapter);
    }

    public boolean isInternetConnection() {
        ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}