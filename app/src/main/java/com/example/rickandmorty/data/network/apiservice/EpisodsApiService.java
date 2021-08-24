package com.example.rickandmorty.data.network.apiservice;

import com.example.rickandmorty.models.RickAndMoryResponse;
import com.example.rickandmorty.models.episods.Episods;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpisodsApiService {
    @GET("api/episode")
    Call<RickAndMoryResponse<Episods>> fetchEpisods(@Query("page") int page);

    @GET("api/episode/{id}")
    Call<Episods>fetchId(@Path("id") int id);
}
