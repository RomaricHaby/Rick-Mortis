package com.rickmorty.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rickmorty.Model.Character.Character;
import com.rickmorty.Model.Episode.Episode;
import com.rickmorty.Model.Location.Location;


@Database(entities = {Character.class, Location.class, Episode.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RickMortyDao rickMortyDao();
}
