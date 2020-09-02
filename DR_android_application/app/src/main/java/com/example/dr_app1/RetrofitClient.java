package com.example.dr_app1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.1.3/DR/";
    private static RetrofitClient myclient;
    private Retrofit retrofit;

    public RetrofitClient(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (GsonConverterFactory.create()).build();
    }
    public static synchronized  RetrofitClient getInstance(){
        if(myclient == null){
            myclient = new RetrofitClient();
        }
        return myclient;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
