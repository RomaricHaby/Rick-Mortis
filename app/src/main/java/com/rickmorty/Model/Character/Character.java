package com.rickmorty.Model.Character;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

@Entity
public class Character implements Serializable {

    @SerializedName("id")
    @PrimaryKey
    @Expose
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    @Expose
    private String name;

    @SerializedName("status")
    @ColumnInfo(name = "status")
    @Expose
    private String status;

    @SerializedName("species")
    @ColumnInfo(name = "species")
    @Expose
    private String species;

    @SerializedName("type")
    @ColumnInfo(name = "type")
    @Expose
    private String type;

    @SerializedName("gender")
    @ColumnInfo(name = "gender")
    @Expose
    private String gender;

    @SerializedName("origin")
    @ColumnInfo(name = "origin")
    @Expose
    private OriginCharacter originCharacter;

    @SerializedName("location")
    @ColumnInfo(name = "locationsimple")
    @Expose
    private LocationSimple locationSimple;

    @SerializedName("image")
    @ColumnInfo(name = "image")
    @Expose
    private String image;

    @SerializedName("episode")
    @ColumnInfo(name = "episode")
    @Expose
    private List<String> episode = null;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    @Expose
    private String url;

    @SerializedName("created")
    @ColumnInfo(name = "created")
    @Expose
    private String created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public OriginCharacter getOriginCharacter() {
        return originCharacter;
    }

    public void setOriginCharacter(OriginCharacter originCharacter) {
        this.originCharacter = originCharacter;
    }

    public LocationSimple getLocationSimple() {
        return locationSimple;
    }

    public void setLocationSimple(LocationSimple locationSimple) {
        this.locationSimple = locationSimple;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public void setEpisode(List<String> episode) {
        this.episode = episode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}