package com.example.test.models.episodes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Victor on 18/03/2016.
 */
public class ResponseEpisodes {

    @SerializedName("info")
    private VoInfoEpisodes infoEpisodes;

    @SerializedName("results")
    private List<VoEpisodes> resultsEpisodes;


    public ResponseEpisodes() {
    }

    public VoInfoEpisodes getInfoEpisodes() {
        return infoEpisodes;
    }

    public void setInfoEpisodes(VoInfoEpisodes infoEpisodes) {
        this.infoEpisodes = infoEpisodes;
    }

    public List<VoEpisodes> getResultsEpisodes() {
        return resultsEpisodes;
    }

    public void setResultsEpisodes(List<VoEpisodes> resultsEpisodes) {
        this.resultsEpisodes = resultsEpisodes;
    }
}
