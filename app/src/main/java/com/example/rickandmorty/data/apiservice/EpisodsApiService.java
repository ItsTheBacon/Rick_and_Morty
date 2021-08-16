package com.example.rickandmorty.data.apiservice;

import com.example.rickandmorty.models.charter.Character;
import com.example.rickandmorty.models.episods.Episods;
import com.example.rickandmorty.models.RickAndMoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EpisodsApiService {
    @GET("api/episode")
    Call<RickAndMoryResponse<Episods>> fetchEpisods();

    @GET("api/episode/{id}")
    Call<Episods>fetchId(@Path("id") int id);
}
