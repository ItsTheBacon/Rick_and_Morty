package com.example.rickandmorty.ui.fragments.episods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.rickandmorty.R;
import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentEpisodsBinding;
import com.example.rickandmorty.ui.adapters.episodsadapter.EpisodsAdapter;
import com.example.rickandmorty.ui.fragments.character.CharterFragmentDirections;
import com.example.rickandmorty.utils.OnItemClickListener;

public class EpisodsFragment extends BaseFragment<FragmentEpisodsBinding, EpisodsViewModel> {
    EpisodsAdapter adapter = new EpisodsAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodsViewModel.class);
        binding = FragmentEpisodsBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupRequest() {
        super.setupRequest();
        viewModel.fetchEpisods().observe(getViewLifecycleOwner(), episodsRickAndMoryResponse ->
                adapter.addlist(episodsRickAndMoryResponse.getResults()));
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
                        .navigate(EpisodsFragmentDirections.actionEpisodsFragmentToEpisodsDetailFragment().setId(id));
            }

            @Override
            public void OnLongListener(int id) {

            }
        });
    }
    private void setupRecycler() {
        binding.rvEpisods.setAdapter(adapter);
    }
}