package com.example.rickandmorty.utils;

import android.app.Application;

import com.example.rickandmorty.data.apiservice.CharterApiService;
import com.example.rickandmorty.data.apiservice.EpisodsApiService;
import com.example.rickandmorty.data.apiservice.LocationApiService;
import com.example.rickandmorty.data.network.RetrofitClient;

public class App extends Application {
    public static CharterApiService charterApiService;
    public static LocationApiService locationApiService;
    public static EpisodsApiService episodsApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        charterApiService = new RetrofitClient().provideChacterApiService();
        locationApiService = new RetrofitClient().provideLocationApiService();
        episodsApiService = new RetrofitClient().provideEpisodsApiService();
    }
}
