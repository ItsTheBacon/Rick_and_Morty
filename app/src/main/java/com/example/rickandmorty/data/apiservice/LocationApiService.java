package com.example.rickandmorty.data.apiservice;

import com.example.rickandmorty.models.charter.Character;
import com.example.rickandmorty.models.location.Location;
import com.example.rickandmorty.models.RickAndMoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocationApiService {
    @GET("api/location")
    Call<RickAndMoryResponse<Location>> fetchLocation();
    @GET("api/location/{id}")
    Call<Location>fetchId(@Path("id") int id);
}
