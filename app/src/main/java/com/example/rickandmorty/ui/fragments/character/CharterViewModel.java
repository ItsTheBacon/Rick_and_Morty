package com.example.rickandmorty.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.utils.App;
import com.example.rickandmorty.models.charter.Character;
import com.example.rickandmorty.models.RickAndMoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharterViewModel extends ViewModel {

    MutableLiveData<RickAndMoryResponse<Character>> fetchCharacter(){
        MutableLiveData<RickAndMoryResponse<Character>> data = new MutableLiveData<>();
        App.charterApiService.fetchCharacters().enqueue(new Callback<RickAndMoryResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMoryResponse<Character>> call, Response<RickAndMoryResponse<Character>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMoryResponse<Character>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

   public MutableLiveData<Character> fetchId(int id){
        MutableLiveData<Character> data = new MutableLiveData<>();
        App.charterApiService.fetchId(id).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
