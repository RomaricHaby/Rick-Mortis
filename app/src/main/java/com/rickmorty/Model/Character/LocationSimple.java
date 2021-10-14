package com.rickmorty.Model.Character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationSimple {
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
}
