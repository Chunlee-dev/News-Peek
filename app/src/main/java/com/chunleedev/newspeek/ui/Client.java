package com.chunleedev.newspeek.ui;

import com.chunleedev.newspeek.RetroInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class Client {

    private static final String URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit;
    private static Client client;

    private Client() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Client getInstance () {
        if (client == null){
            client = new Client();
        }
        return client;
    }

    public RetroInterface getApi () {
        return retrofit.create(RetroInterface.class);
    }
}
