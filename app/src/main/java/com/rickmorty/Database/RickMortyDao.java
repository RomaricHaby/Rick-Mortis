package com.rickmorty.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rickmorty.Model.Character.Character;
import com.rickmorty.Model.Episode.Episode;
import com.rickmorty.Model.Location.Location;

import java.util.List;

@Dao
public interface RickMortyDao {

    //region Characters

    @Query("SELECT * FROM  character ")
    List<Character> getCharacters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllCharacters(List<Character> characters);

    //endregion

    //region Episodes

    @Query("SELECT * FROM episode")
    List<Episode> getEpisodes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllEpisodes(List<Episode> episodes);

    //endregion

    //region Locations

    @Query("SELECT * FROM location")
    List<Location> getLocations();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllLocations(List<Location> locations);

    //endregion
}