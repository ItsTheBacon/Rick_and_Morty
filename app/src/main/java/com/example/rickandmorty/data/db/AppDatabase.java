package com.example.rickandmorty.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rickandmorty.data.db.daos.CharacterDao;
import com.example.rickandmorty.data.db.daos.EpisodsDao;
import com.example.rickandmorty.data.db.daos.LocationDao;
import com.example.rickandmorty.models.charter.Character;
import com.example.rickandmorty.models.episods.Episods;
import com.example.rickandmorty.models.location.Location;

@Database(entities = {Character.class, Location.class, Episods.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();

    public abstract LocationDao locationDao();

    public abstract EpisodsDao episodsDao();
}
