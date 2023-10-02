package com.example.test.models.locations;

import com.example.test.models.characters.VoInfoCharacters;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Victor
 */
public class ResponseLocations {

    @SerializedName("info")
    private VoInfoLocations infoLocations;

    @SerializedName("results")
    private List<VoLocations> resultsLocations;


    public ResponseLocations() {
    }

    public VoInfoLocations getInfoLocations() {
        return infoLocations;
    }

    public void setInfoLocations(VoInfoLocations infoLocations) {
        this.infoLocations = infoLocations;
    }

    public List<VoLocations> getResultsLocations() {
        return resultsLocations;
    }

    public void setResultsLocations(List<VoLocations> resultsLocations) {
        this.resultsLocations = resultsLocations;
    }
}
