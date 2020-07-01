package com.chunleedev.newspeek;

import com.chunleedev.newspeek.parceables.Config;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroInterface {
    @GET("top-headlines")
    Call<Config> getConfig(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );
}
