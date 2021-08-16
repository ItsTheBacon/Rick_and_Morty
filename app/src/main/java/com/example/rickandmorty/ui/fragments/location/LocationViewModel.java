package com.example.rickandmorty.ui.fragments.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.models.RickAndMoryResponse;
import com.example.rickandmorty.models.location.Location;
import com.example.rickandmorty.utils.App;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {
    MutableLiveData<RickAndMoryResponse<Location>> fetchLocation() {
        MutableLiveData<RickAndMoryResponse<Location>> data = new MutableLiveData<>();
        App.locationApiService.fetchLocation().enqueue(new Callback<RickAndMoryResponse<Location>>() {
            @Override
            public void onResponse(Call<RickAndMoryResponse<Location>> call, Response<RickAndMoryResponse<Location>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMoryResponse<Location>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<Location> fetchId(int id) {
        MutableLiveData<Location> data = new MutableLiveData<>();
        App.locationApiService.fetchId(id).enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}