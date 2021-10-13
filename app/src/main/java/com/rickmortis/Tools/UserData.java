package com.rickmortis.Tools;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.rickmortis.Model.Character.Character;

import java.util.ArrayList;
import java.util.Map;

public class UserData {
    //Singleton
    private static UserData instance = null;
    private ArrayList<Character> arrayFavCharac;

    private UserData() {
        arrayFavCharac = new ArrayList<>();
    }

    //Gestion des fav-
    public void addFavCharac(Character character){
        boolean save = false;

        for (Character ch : arrayFavCharac) {
            if (ch.getId() == character.getId()) {
                save = true;
                break;
            }
        }

        if (!save){
            arrayFavCharac.add(character);
        }
    }

    public void removeFavCharacter(Character character){
        for (Character ch : arrayFavCharac) {
            if (ch.getId() == character.getId()) {
                arrayFavCharac.remove(ch);
                return;
            }
        }
    }

    public boolean isFav(Character character){
        for (Character ch : arrayFavCharac) {
            if (ch.getId() == character.getId()) {
                return true;
            }
        }
        return false;
    }

    //Get && Set
    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    public ArrayList<Character> getArrayFavCharac() {
        return arrayFavCharac;
    }

}
