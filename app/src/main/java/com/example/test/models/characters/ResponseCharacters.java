package com.example.test.models.characters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Victor on 18/03/2016.
 */
public class ResponseCharacters {

    @SerializedName("info")
    private VoInfoCharacters infoCharacters;

    @SerializedName("results")
    private List<VoCharacters> resultsCharacters;


    public ResponseCharacters() {
    }

    public VoInfoCharacters getInfoCharacters() {
        return infoCharacters;
    }

    public void setInfoCharacters(VoInfoCharacters infoCharacters) {
        this.infoCharacters = infoCharacters;
    }

    public List<VoCharacters> getResultsCharacters() {
        return resultsCharacters;
    }

    public void setResultsCharacters(List<VoCharacters> resultsCharacters) {
        this.resultsCharacters = resultsCharacters;
    }
}
