package com.example.buscadorpersonas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.buscadorpersonas.services.InfoServices;
import com.example.buscadorpersonas.services.dataResponse.InfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText numeroDocumento;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numeroDocumento = findViewById(R.id.txtNumeroDoc);
        buscar = findViewById(R.id.btnBuscar);

        buscar.setOnClickListener(view -> {

            Consulta();

        });





    }

    private void Consulta() {

        Call<InfoResponse> respInfo = (new InfoServices()).getInfoService();
        respInfo.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                InfoResponse r = response.body();
                System.out.println(r.data.get(0).getNombre());


            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {

            }
        });


    }


}