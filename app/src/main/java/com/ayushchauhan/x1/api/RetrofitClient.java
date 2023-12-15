package com.ayushchauhan.x1.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //1.Retrofit class
    //2.static instance
    //3.static final base_url/root-url/common_url/server_url


    private final String BASE_URL="https://myhisab.seeksolution.in/api/";
    private static RetrofitClient instance;
    private Retrofit retrofit;

    //private constructor
    private RetrofitClient() {
        //1. baseurl
        //2. converter add

        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }


    public static synchronized RetrofitClient getInstance(){
        if(instance==null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }


}

