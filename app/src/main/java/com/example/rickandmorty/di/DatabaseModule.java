package com.example.rickandmorty.di;

import android.content.Context;

import com.example.rickandmorty.data.db.AppDatabase;
import com.example.rickandmorty.data.db.AppDatabase_Impl;
import com.example.rickandmorty.data.db.RoomClient;
import com.example.rickandmorty.data.db.daos.CharacterDao;
import com.example.rickandmorty.data.db.daos.EpisodsDao;
import com.example.rickandmorty.data.db.daos.LocationDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Singleton
    RoomClient roomClient = new RoomClient();
    @Singleton
    @Provides
    AppDatabase provideDatabase(@ApplicationContext Context context){
        return roomClient.provideDatabase(context);
    }

    @Singleton
    @Provides
    CharacterDao provideCharacterDao(AppDatabase appDatabase){
        return roomClient.provideCharacterDao(appDatabase);
    }
    @Singleton
    @Provides
    LocationDao provideLocationDao(AppDatabase appDatabase){
        return roomClient.provideLocationDao(appDatabase);
    }
    @Singleton
    @Provides
    EpisodsDao provideEpisodesDao( AppDatabase appDatabase){
        return roomClient.provideEpisodesDao(appDatabase);
    }
}
