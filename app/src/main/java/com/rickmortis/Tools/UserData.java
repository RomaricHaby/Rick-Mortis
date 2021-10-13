package com.rickmortis.Tools;

import com.rickmortis.Model.Character.Character;

import java.util.ArrayList;

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
