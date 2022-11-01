package com.example.buscadorpersonas.services;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.buscadorpersonas.services.dataResponse.InfoResponse;
import com.example.buscadorpersonas.services.endpoints.InfoEndPoints;

public class InfoServices {

    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://esalboy.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public Call<InfoResponse> getInfoService(){
       return this.getRetrofit().create(InfoEndPoints.class).getInfo();

    }


}
