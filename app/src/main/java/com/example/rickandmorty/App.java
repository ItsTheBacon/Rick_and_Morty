package com.example.rickandmorty;

import android.app.Application;

import com.example.rickandmorty.data.db.RoomClient;
import com.example.rickandmorty.data.db.daos.CharacterDao;
import com.example.rickandmorty.data.db.daos.EpisodsDao;
import com.example.rickandmorty.data.db.daos.LocationDao;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }
}
