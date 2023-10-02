package com.example.test.models.characters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Victor on 01/03/2016.
 */
public class VoLocationCharacters {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;


    public VoLocationCharacters() {
    }

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