package com.rickmorty.Model.Episode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rickmorty.Model.Info;

import java.util.List;

public class DataEpisodeApi {

    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("results")
    @Expose
    private List<Episode> results = null;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Episode> getResults() {
        return results;
    }

    public void setResults(List<Episode> results) {
        this.results = results;
    }
}
