package com.example.test.api;

import com.example.test.models.locations.ResponseLocations;
import com.example.test.models.locations.VoLocations;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Victor
 */
public interface locationsApi {

    @GET("/api/location")
    Call<ResponseLocations> locations(
    );

    @GET("/api/location/{id}")
    Call<VoLocations> locationsById(
            @Path("id") Integer id
    );

    @GET("/api/location/")
    Call<VoLocations> locationsByFilter(
            @Query("name") int name,
            @Query("type") int type,
            @Query("dimension") int dimension
    );
}