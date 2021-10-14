package com.rickmorty.Tools;

import com.rickmorty.Model.Character.Character;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class UserData {
    //Singleton
    private static UserData instance = null;
    private final ArrayList<Character> arrayFavCharac;

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

    //Internet
    public boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            // Log error
        }
        return false;
    }

}
