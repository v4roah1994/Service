package com.example.buscadorpersonas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buscadorpersonas.services.InfoServices;
import com.example.buscadorpersonas.services.models.*;
import com.example.buscadorpersonas.services.dataResponse.InfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreacionActivity extends AppCompatActivity {

    EditText Name, UserName, Rol, Password;
    Button Add, Back;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion);

        Name = findViewById(R.id.eTxtNombreC);
        UserName = findViewById(R.id.eTxtUsuarioC);
        Rol = findViewById(R.id.eTxtRolC);
        Password = findViewById(R.id.eTxtPasswordC);
        Add = findViewById(R.id.btnCrear);
        Back = findViewById(R.id.btnAtrasC);

        //boton agregar ususario
        Add.setOnClickListener(v -> {
            if (Name.getText().toString().isEmpty() || UserName.getText().toString().isEmpty()|| Password.getText().toString().isEmpty() || Rol.getText().toString().isEmpty()){
                Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
            }else {
                //obtencion de datos
                createUser(new User(Name.getText().toString(), UserName.getText().toString(),Password.getText().toString(), Rol.getText().toString()));
            }
        });

        //boton atras
        Back.setOnClickListener(v -> {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void createUser(User u) {
        //peticion al POST
        Call<InfoResponse> respInfo = (new InfoServices().postInfoService(u));
        respInfo.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                Log.i("Info", "Conexión satisfactoria");
                Log.i("Info", "Usuario creado satisfactoriamente");
            }
            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                Log.i("Info", "Conexión denegada");
            }
        });
    }
}