package com.rickmorty.Model.Character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OriginCharacter {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String url;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
