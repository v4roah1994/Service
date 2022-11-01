package com.example.buscadorpersonas.services.endpoints;

import com.example.buscadorpersonas.services.dataResponse.InfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InfoEndPoints {
    @GET("test/personas.json")
    Call<InfoResponse> getInfo();
}
