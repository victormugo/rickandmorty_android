package com.example.test.models.episodes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Victor on 01/03/2016.
 */
public class VoEpisodes {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("air_date")
    private String air_date;

    @SerializedName("episode")
    private String episode;

    @SerializedName("characters")
    private List<String> characters;

    @SerializedName("url")
    private String url;

    @SerializedName("created")
    private String created;

    public VoEpisodes() {
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

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
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