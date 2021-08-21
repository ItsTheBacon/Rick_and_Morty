package com.example.rickandmorty.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rickandmorty.models.charter.Character;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Character> character);

    @Query("SELECT * FROM character")
    List<Character> getAll();
}
