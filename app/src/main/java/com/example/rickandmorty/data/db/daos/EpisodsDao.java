package com.example.rickandmorty.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rickandmorty.models.episods.Episods;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EpisodsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Episods> episods);

    @Query("SELECT * FROM episods")
    List<Episods> getAll();

}
