package com.rickmorty.Model.Character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rickmorty.Model.Info;

import java.util.List;

public class DataCharacterApi {


    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("results")
    @Expose
    private List<Character> characters = null;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

}


