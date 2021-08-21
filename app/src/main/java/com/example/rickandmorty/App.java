package com.example.rickandmorty;

import android.app.Application;

import com.example.rickandmorty.data.apiservice.CharterApiService;
import com.example.rickandmorty.data.apiservice.EpisodsApiService;
import com.example.rickandmorty.data.apiservice.LocationApiService;
import com.example.rickandmorty.data.db.RoomClient;
import com.example.rickandmorty.data.db.daos.CharacterDao;
import com.example.rickandmorty.data.db.daos.EpisodsDao;
import com.example.rickandmorty.data.db.daos.LocationDao;
import com.example.rickandmorty.data.network.RetrofitClient;

public class App extends Application {
    public static CharterApiService charterApiService;
    public static LocationApiService locationApiService;
    public static EpisodsApiService episodsApiService;
    public static CharacterDao characterDao;
    public static LocationDao locationDao;
    public static EpisodsDao episodsDao;

    @Override
    public void onCreate() {
        super.onCreate();
        charterApiService = new RetrofitClient().provideChacterApiService();
        locationApiService = new RetrofitClient().provideLocationApiService();
        episodsApiService = new RetrofitClient().provideEpisodsApiService();
        RoomClient roomClient = new RoomClient();
        characterDao = roomClient.provideCharacterDao(roomClient.provideDatabase(this));
        locationDao = roomClient.provideLocationDao(roomClient.provideDatabase(this));
        episodsDao = roomClient.provideEpisodsDao(roomClient.provideDatabase(this));

    }
}
