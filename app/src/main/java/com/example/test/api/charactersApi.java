package com.example.test.api;

import com.example.test.models.characters.DataCharacters;
import com.example.test.models.characters.ResponseCharacters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Victor
 */
public interface charactersApi {

    @GET("/api/character")
    Call<ResponseCharacters> getCharacters();

    @GET("/api/character/{id}")
    Call<DataCharacters> getCharatersById(
            @Path("id") Integer id
    );

    @GET("/api/character/")
    Call<DataCharacters> getCharatersByFilter(
            @Query("name") int name,
            @Query("status") int status,
            @Query("species") int species,
            @Query("type") int type,
            @Query("gender") int gender
    );
}