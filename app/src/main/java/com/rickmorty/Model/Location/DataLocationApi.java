package com.rickmorty.Model.Location;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rickmorty.Model.Info;

import java.util.ArrayList;
import java.util.List;


public class DataLocationApi {

    @SerializedName("info")
    @Expose
    private Info infoLocation;
    @SerializedName("results")
    @Expose
    private List<Location> locations = new ArrayList<Location>();

    public Info getInfoLocation() {
        return infoLocation;
    }

    public void setInfoLocation(Info infoLocation) {
        this.infoLocation = infoLocation;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

}