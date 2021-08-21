package com.example.rickandmorty.ui.fragments.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.data.repositories.RickAndMortyRepository;
import com.example.rickandmorty.models.RickAndMoryResponse;
import com.example.rickandmorty.models.location.Location;

import java.util.List;

public class LocationViewModel extends ViewModel {
    private final RickAndMortyRepository repository = new RickAndMortyRepository();

    MutableLiveData<RickAndMoryResponse<Location>> fetchLocation() {
        return repository.fetchLocation();
    }

    public MutableLiveData<Location> fetchId(int id) {
        return repository.fetchId(id);
    }

    public List<Location> getLocations() {
        return repository.getLocation();
    }

}