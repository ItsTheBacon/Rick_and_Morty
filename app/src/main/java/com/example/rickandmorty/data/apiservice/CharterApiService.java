package com.example.rickandmorty.data.apiservice;

import com.example.rickandmorty.models.charter.Character;
import com.example.rickandmorty.models.RickAndMoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharterApiService {
    @GET("api/character")
    Call<RickAndMoryResponse<Character>> fetchCharacters();

    @GET("api/character/{id}")
    Call<Character>fetchId( @Path("id") int id);
}
