package com.example.rickandmorty.ui.fragments.episods;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.models.charter.Character;
import com.example.rickandmorty.models.location.Location;
import com.example.rickandmorty.utils.App;
import com.example.rickandmorty.models.episods.Episods;
import com.example.rickandmorty.models.RickAndMoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodsViewModel extends ViewModel {
    MutableLiveData<RickAndMoryResponse<Episods>> fetchEpisods() {
        MutableLiveData<RickAndMoryResponse<Episods>> data = new MutableLiveData<>();
        App.episodsApiService.fetchEpisods().enqueue(new Callback<RickAndMoryResponse<Episods>>() {
            @Override
            public void onResponse(Call<RickAndMoryResponse<Episods>> call, Response<RickAndMoryResponse<Episods>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMoryResponse<Episods>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
    public MutableLiveData<Episods> fetchId(int id){
        MutableLiveData<Episods> data = new MutableLiveData<>();
       App.episodsApiService.fetchId(id).enqueue(new Callback<Episods>() {
           @Override
           public void onResponse(Call<Episods> call, Response<Episods> response) {
               data.setValue(response.body());
           }

           @Override
           public void onFailure(Call<Episods> call, Throwable t) {
               data.setValue(null);

           }
       });
       return data;
    }
}
