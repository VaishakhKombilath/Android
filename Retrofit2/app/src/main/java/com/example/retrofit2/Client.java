package com.example.retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static final String BSE_URL = "http://192.168.1.104:2020/";
    private static  Client  mInstance;
    private Retrofit retrofit;

    private Client(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BSE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Client getInstance(){
        if(mInstance == null){
            mInstance = new Client();
        }
        return mInstance;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
