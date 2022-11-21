package com.example.buscadorpersonas.services;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.buscadorpersonas.services.dataResponse.InfoResponse;
import com.example.buscadorpersonas.services.endpoints.InfoEndPoints;
import com.example.buscadorpersonas.services.models.User;

public class InfoServices {

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://172.16.36.32:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<InfoResponse> getInfoService() {
        return this.getRetrofit().create(InfoEndPoints.class).getInfo();
    }

    public Call<InfoResponse> postInfoService(User user) {
        return this.getRetrofit().create(InfoEndPoints.class).postInfo(user);
    }

    public Call<InfoResponse> deleteInfoService(String id) {
        return this.getRetrofit().create(InfoEndPoints.class).deleteInfo(id);
    }

    public Call<InfoResponse> updateInfoService(String id, User user) {
        return this.getRetrofit().create(InfoEndPoints.class).updateInfo(id, user);
    }


}
