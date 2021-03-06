package com.rickmorty.Database;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rickmorty.Model.Character.LocationCharacter;
import com.rickmorty.Model.Character.OriginCharacter;

import java.util.List;

public class Converters {

    @TypeConverter
    public static List<String> restoreList(String listOfString) {
        return new Gson().fromJson(listOfString, new TypeToken<List<String>>() {}.getType());
    }

    @TypeConverter
    public static String saveList(List<String> listOfString) {
        return new Gson().toJson(listOfString);
    }

    @TypeConverter
    public static LocationCharacter restoreLocation(String location) {
        return new Gson().fromJson(location, new TypeToken<LocationCharacter>() {}.getType());
    }

    @TypeConverter
    public static String saveLocation(LocationCharacter location) {
        return new Gson().toJson(location);
    }



    @TypeConverter
    public static OriginCharacter restoreOrigin(String origin) {
        return new Gson().fromJson(origin, new TypeToken<OriginCharacter>() {}.getType());
    }

    @TypeConverter
    public static String saveOrigin(OriginCharacter origin) {
        return new Gson().toJson(origin);
    }
}