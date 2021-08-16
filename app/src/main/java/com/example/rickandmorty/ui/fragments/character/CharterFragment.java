package com.example.rickandmorty.ui.fragments.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.rickandmorty.R;
import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentCharterBinding;
import com.example.rickandmorty.ui.adapters.charteradapter.CharacterAdapter;
import com.example.rickandmorty.utils.OnItemClickListener;

public class CharterFragment extends BaseFragment<FragmentCharterBinding, CharterViewModel> {

    private CharacterAdapter adapter= new CharacterAdapter();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel =
                new  ViewModelProvider(requireActivity()).get(CharterViewModel.class);
        binding = FragmentCharterBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();

    }


    @Override
    protected void setupRequest() {
        super.setupRequest();
        viewModel.fetchCharacter().observe(getViewLifecycleOwner(), character ->
                adapter.addlist(character.getResults()));

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
                        .navigate(CharterFragmentDirections.actionCharterFragmentToCharterDetailFragment().setId(id));
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