package com.rickmorty.API;

import com.rickmorty.Model.Character.DataCharacterApi;
import com.rickmorty.Model.Episode.DataEpisodeApi;
import com.rickmorty.Model.Location.DataLocationApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers("Content-Type: application/json")

    @GET("location/")
    Call<DataLocationApi> getLocation(@Query("page") int page);

    @GET("episode/")
    Call<DataEpisodeApi> getEpisode(@Query("page") int page);

    @GET("character/")
    Call<DataCharacterApi> getCharacter(@Query("page") int page);

    @GET("character/")
    Call<DataCharacterApi> getCharacterFilter(@Query("name") String name);
}
