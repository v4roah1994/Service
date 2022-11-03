package com.example.buscadorpersonas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buscadorpersonas.services.InfoServices;
import com.example.buscadorpersonas.services.dataResponse.InfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText  txtDocumento;
    Button btnBuscar,btnActualizar, btnCrear;
    TextView txtMostar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDocumento = findViewById(R.id.eTxtDocumentoB);
        btnBuscar = findViewById(R.id.btnBuscar);
        txtMostar = findViewById(R.id.txtVMostrar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnCrear = findViewById(R.id.btnCrear);


        btnBuscar.setOnClickListener(view -> {
            if (txtDocumento.getText().toString().isEmpty()){
                Toast.makeText(this, "Ingrese el documento para la consulta", Toast.LENGTH_LONG).show();
            }else{
                consultar();
            }


        });

        btnActualizar.setOnClickListener(view -> {
            actualizar();
        });

        btnCrear.setOnClickListener(view -> {
            crear();
        });





    }

    private void consultar() {

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


    private void actualizar(){
        Intent intent = new Intent(this, ActualizarActivity.class);



      //  intent.putExtra("NOMBRE", eTxTNom.getText().toString());
      //  intent.putExtra("CODIGO", eTxTCod.getText().toString());

        startActivity(intent);

    }

    private void crear(){

    }


}