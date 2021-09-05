package com.example.rickandmorty.data.db;

import android.content.Context;

import androidx.room.Room;

import com.example.rickandmorty.data.db.daos.CharacterDao;
import com.example.rickandmorty.data.db.daos.EpisodsDao;
import com.example.rickandmorty.data.db.daos.LocationDao;

import javax.inject.Singleton;

import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;

public class RoomClient {

    public AppDatabase provideDatabase( Context context) {
        return Room
                .databaseBuilder(context, AppDatabase.class, "rick-and_morty-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }


    public CharacterDao provideCharacterDao(AppDatabase database) {
        return database.characterDao();
    }

    public LocationDao provideLocationDao(AppDatabase database) {
        return database.locationDao();
    }

    public EpisodsDao provideEpisodesDao(AppDatabase database) {
        return database.episodsDao();
    }

}
