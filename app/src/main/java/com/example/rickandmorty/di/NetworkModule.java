package com.example.rickandmorty.di;

import com.example.rickandmorty.data.network.RetrofitClient;
import com.example.rickandmorty.data.network.apiservice.CharterApiService;
import com.example.rickandmorty.data.network.apiservice.EpisodsApiService;
import com.example.rickandmorty.data.network.apiservice.LocationApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    RetrofitClient retrofitClient = new RetrofitClient();

    @Singleton
    @Provides
    CharterApiService provideCharacterApiService() {
        return retrofitClient.provideChacterApiService();
    }

    @Singleton
    @Provides
    EpisodsApiService provideEpisodsApiService() {
        return retrofitClient.provideEpisodsApiService();
    }
    @Singleton
    @Provides
    LocationApiService provideLocationApiService(){
        return retrofitClient.provideLocationApiService();
    }

}
