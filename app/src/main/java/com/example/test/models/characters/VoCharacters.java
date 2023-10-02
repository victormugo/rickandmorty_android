package com.example.test.models.characters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Victor on 01/03/2016.
 */
public class VoCharacters {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("status")
    private String status;

    @SerializedName("species")
    private String species;

    @SerializedName("type")
    private String type;

    @SerializedName("gender")
    private String gender;

    @SerializedName("origin")
    private VoOriginCharacters origin;

    @SerializedName("location")
    private VoLocationCharacters location;

    @SerializedName("image")
    private String image;

    @SerializedName("episode")
    private List<String> episode;

    @SerializedName("url")
    private String url;

    @SerializedName("created")
    private String created;

    public VoCharacters() {
    }

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

    public VoOriginCharacters getOrigin() {
        return origin;
    }

    public void setOrigin(VoOriginCharacters origin) {
        this.origin = origin;
    }

    public VoLocationCharacters getLocation() {
        return location;
    }

    public void setLocation(VoLocationCharacters location) {
        this.location = location;
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
}