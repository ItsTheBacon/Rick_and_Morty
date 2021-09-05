package com.example.rickandmorty.ui.fragments.character;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.rickandmorty.R;
import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentCharterBinding;
import com.example.rickandmorty.models.charter.Character;
import com.example.rickandmorty.ui.activity.MainActivity;
import com.example.rickandmorty.ui.adapters.CharacterAdapter;
import com.example.rickandmorty.utils.OnItemClickListener;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CharterFragment extends BaseFragment<FragmentCharterBinding, CharterViewModel> {
    private CharacterAdapter adapter = new CharacterAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount, totalItemCount, postVisibleItems;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharterBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();
    }
    @Override
    protected void setupListeners() {
        super.setupListeners();
        setOnClikcListener();
    }

    @Override
    protected void initialize() {
        super.initialize();
        viewModel =
                new ViewModelProvider(requireActivity()).get(CharterViewModel.class);
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        setupRecycler();
    }

    @Override
    protected void setupRequests() {
        super.setupRequests();
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
        binding.rvCharter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    postVisibleItems = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    if ((visibleItemCount + postVisibleItems) >= totalItemCount) {
                        viewModel.page++;
                        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), characterRickAndMoryResponse ->
                                adapter.addlist(characterRickAndMoryResponse.getResults()));
                        binding.progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    public boolean isInternetConnection() {
            ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network nw = connectivityManager.getActiveNetwork();
                if (nw == null) return false;
                NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
                return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
            } else {
                NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
                return nwInfo != null && nwInfo.isConnected();
            }
    }

    private void setOnClikcListener() {

        adapter.setItemClickList(new OnItemClickListener() {
            @Override
            public void OnClickListener(int id) {
                if (isInternetConnection()) {
                    Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                            .navigate(CharterFragmentDirections
                                    .actionCharterFragmentToCharterDetailFragment()
                                    .setId(id));
                }else
                {
                    Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnLongListener(int id) {
            }
        });
    }

    private void setupRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvCharter.setLayoutManager(linearLayoutManager);
        binding.rvCharter.setAdapter(adapter);
    }
}