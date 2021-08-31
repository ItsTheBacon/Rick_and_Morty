package com.example.rickandmorty.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorty.App;
import com.example.rickandmorty.data.network.apiservice.CharterApiService;
import com.example.rickandmorty.data.network.apiservice.EpisodsApiService;
import com.example.rickandmorty.data.network.apiservice.LocationApiService;
import com.example.rickandmorty.models.RickAndMoryResponse;
import com.example.rickandmorty.models.charter.Character;
import com.example.rickandmorty.models.episods.Episods;
import com.example.rickandmorty.models.location.Location;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RickAndMortyRepository {
    CharterApiService charterApiService;

    EpisodsApiService episodsApiService;
    LocationApiService locationApiService;


    @Inject
    public RickAndMortyRepository(CharterApiService charterApiService , EpisodsApiService episodsApiService, LocationApiService locationApiService) {
        this.charterApiService = charterApiService;
        this.episodsApiService = episodsApiService;
        this.locationApiService = locationApiService;
    }

    public MutableLiveData<RickAndMoryResponse<Character>> fetchCharacters(int page) {
        MutableLiveData<RickAndMoryResponse<Character>> data = new MutableLiveData<>();
        charterApiService.fetchCharacters(page).enqueue(new Callback<RickAndMoryResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMoryResponse<Character>> call, Response<RickAndMoryResponse<Character>> response) {
                if (response.body() != null) {
                    App.characterDao.insertAll(response.body().getResults());
                    data.postValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<RickAndMoryResponse<Character>> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<Character> fetchCharacter(int id) {
        MutableLiveData<Character> data = new MutableLiveData<>();
        charterApiService.fetchId(id).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<RickAndMoryResponse<Location>> fetchLocation(int page) {
        MutableLiveData<RickAndMoryResponse<Location>> location = new MutableLiveData<>();
        locationApiService.fetchLocation(page).enqueue(new Callback<RickAndMoryResponse<Location>>() {
            @Override
            public void onResponse(Call<RickAndMoryResponse<Location>> call, Response<RickAndMoryResponse<Location>> response) {
                if (response.body() != null) {
                    App.locationDao.insertAll(response.body().getResults());
                    location.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMoryResponse<Location>> call, Throwable t) {
                location.postValue(null);
            }
        });
        return location;
    }

    public MutableLiveData<Location> fetchId(int id) {
        MutableLiveData<Location> data = new MutableLiveData<>();
        locationApiService.fetchId(id).enqueue(new Callback<Location>() {
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

    public MutableLiveData<RickAndMoryResponse<Episods>> fetchEpisods(int page) {
        MutableLiveData<RickAndMoryResponse<Episods>> data = new MutableLiveData<>();
        episodsApiService.fetchEpisods(page).enqueue(new Callback<RickAndMoryResponse<Episods>>() {
            @Override
            public void onResponse(Call<RickAndMoryResponse<Episods>> call, Response<RickAndMoryResponse<Episods>> response) {
                if (response.body() != null) {
                    App.episodsDao.insertAll(response.body().getResults());
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMoryResponse<Episods>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<Episods> fetchEpisodsdetail(int id) {
        MutableLiveData<Episods> data = new MutableLiveData<>();
        episodsApiService.fetchId(id).enqueue(new Callback<Episods>() {
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

    public List<Character> getCharacters() {
        return App.characterDao.getAll();

    }

    public List<Episods> getEpisods() {
        return App.episodsDao.getAll();
    }

    public ArrayList<Location> getLocation() {
        return (ArrayList<Location>) App.locationDao.getAll();
    }
}
