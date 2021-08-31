package com.example.rickandmorty;

import android.app.Application;

import com.example.rickandmorty.data.db.RoomClient;
import com.example.rickandmorty.data.db.daos.CharacterDao;
import com.example.rickandmorty.data.db.daos.EpisodsDao;
import com.example.rickandmorty.data.db.daos.LocationDao;
import com.example.rickandmorty.data.network.RetrofitClient;
import com.example.rickandmorty.data.network.apiservice.CharterApiService;
import com.example.rickandmorty.data.network.apiservice.EpisodsApiService;
import com.example.rickandmorty.data.network.apiservice.LocationApiService;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {
    public static CharacterDao characterDao;
    public static LocationDao locationDao;
    public static EpisodsDao episodsDao;


    @Override
    public void onCreate() {
        super.onCreate();
        RoomClient roomClient = new RoomClient();
        characterDao = roomClient.provideCharacterDao(roomClient.provideDatabase(this));
        locationDao = roomClient.provideLocationDao(roomClient.provideDatabase(this));
        episodsDao = roomClient.provideEpisodesDao(roomClient.provideDatabase(this));

    }
}
