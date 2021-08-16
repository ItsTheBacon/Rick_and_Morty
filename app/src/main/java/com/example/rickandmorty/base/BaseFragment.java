package com.example.rickandmorty.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public  abstract class BaseFragment<Binding ,ViewModel> extends Fragment {
    protected Binding binding;
    protected ViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initalize();
        setupViews();
        setupRequest();
        setUpObserves();
        setupListener();
    }

    protected  void setupListener(){}

    protected  void setUpObserves(){}

    protected  void setupRequest(){}

    protected  void setupViews(){}

    protected  void initalize(){}
}
