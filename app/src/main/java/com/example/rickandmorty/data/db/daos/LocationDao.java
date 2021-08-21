package com.example.rickandmorty.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rickandmorty.models.location.Location;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Location> locations);

    @Query("SELECT * FROM location")
    List<Location> getAll();

}
