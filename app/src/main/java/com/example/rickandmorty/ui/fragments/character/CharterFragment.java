package com.example.rickandmorty.ui.fragments.character;

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
import com.example.rickandmorty.databinding.FragmentCharterBinding;
import com.example.rickandmorty.models.charter.Character;
import com.example.rickandmorty.ui.adapters.CharacterAdapter;
import com.example.rickandmorty.utils.OnItemClickListener;

import java.util.ArrayList;

public class CharterFragment extends BaseFragment<FragmentCharterBinding, CharterViewModel> {
    private CharacterAdapter adapter = new CharacterAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharterBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupListener() {
        super.setupListener();
        setOnClikcListener();
    }

    @Override
    protected void initalize() {
        super.initalize();
        viewModel =
                new ViewModelProvider(requireActivity()).get(CharterViewModel.class);
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        setupRecycler();
        setupProgressBar();
    }

    @Override
    protected void setupRequest() {
        super.setupRequest();
        getCharacter();
    }

    private void getCharacter() {
        if (isInternetConnection()) {
            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), characters -> {
                if (characters != null) {
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    adapter.addlist(characters.getResults());
                }
            });
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            adapter.addlist((ArrayList<Character>) viewModel.getCharacters());
        }
    }


    private void setupProgressBar() {
    }

    public boolean isInternetConnection() {
        ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    private void setOnClikcListener() {
        adapter.setItemClickList(new OnItemClickListener() {
            @Override
            public void OnClickListener(int id) {
                Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                        .navigate(CharterFragmentDirections
                                .actionCharterFragmentToCharterDetailFragment()
                                .setId(id));
            }

            @Override
            public void OnLongListener(int id) {

            }
        });
    }

    private void setupRecycler() {
        binding.rvCharter.setAdapter(adapter);
    }
}