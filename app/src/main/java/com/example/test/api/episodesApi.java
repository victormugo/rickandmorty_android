package com.example.test.api;

import com.example.test.models.episodes.ResponseEpisodes;
import com.example.test.models.episodes.VoEpisodes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Victor
 */
public interface episodesApi {

    @GET("/api/episode")
    Call<ResponseEpisodes> episodes(
    );

    @GET("/api/episode/{id}")
    Call<VoEpisodes> episodesById(
            @Path("id") Integer id
    );

    @GET("/api/episode/")
    Call<ResponseEpisodes> episodesByFilter(
            @Query("name") int name,
            @Query("episode") int episode
    );
}