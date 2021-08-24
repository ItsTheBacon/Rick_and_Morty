package com.example.rickandmorty.data.network.apiservice;

import com.example.rickandmorty.models.RickAndMoryResponse;
import com.example.rickandmorty.models.charter.Character;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharterApiService {
    @GET("api/character")
    Call<RickAndMoryResponse<Character>> fetchCharacters(
            @Query("page") int page
    );

    @GET("api/character/{id}")
    Call<Character>fetchId( @Path("id") int id);
}
