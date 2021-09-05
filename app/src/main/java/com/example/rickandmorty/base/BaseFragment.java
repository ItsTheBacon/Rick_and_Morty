package com.example.rickandmorty.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

public  abstract class BaseFragment<Binding  extends ViewBinding,ViewModel extends androidx.lifecycle.ViewModel> extends Fragment {
    protected Binding binding;
    protected ViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setupViews();
        setupListeners();
        setupRequests();
        setUpObserves();
    }


    protected void initialize() {
    }

    protected void setupViews() {
    }

    protected void setupListeners() {
    }

    protected void setupRequests() {
    }

    protected void setUpObserves() {
    }
}
