package com.example.rickandmorty.data.network;

import com.example.rickandmorty.data.network.apiservice.CharterApiService;
import com.example.rickandmorty.data.network.apiservice.EpisodsApiService;
import com.example.rickandmorty.data.network.apiservice.LocationApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private final OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(provideLoginInterceptor())
            .connectTimeout(30 , TimeUnit.SECONDS)
            .writeTimeout(30 , TimeUnit.SECONDS)
            .readTimeout(30 , TimeUnit.SECONDS)
            .build();

    private HttpLoggingInterceptor provideLoginInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
    private final Retrofit provideRetrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    
    public CharterApiService provideChacterApiService(){
        return provideRetrofit.create(CharterApiService.class);
    }
    public LocationApiService provideLocationApiService(){
        return provideRetrofit.create(LocationApiService.class);
    }
    public EpisodsApiService provideEpisodsApiService(){
        return provideRetrofit.create(EpisodsApiService.class);
    }
}
