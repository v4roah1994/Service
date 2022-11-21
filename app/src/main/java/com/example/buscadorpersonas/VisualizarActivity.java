package com.example.buscadorpersonas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buscadorpersonas.services.InfoServices;
import com.example.buscadorpersonas.services.dataResponse.InfoResponse;
import com.example.buscadorpersonas.services.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarActivity extends AppCompatActivity {

    TextView autoName, autoUser, autoRol;
    Button Update, Delete, Back;

    VisualizarActivity context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        autoName = findViewById(R.id.eTxtNombre);
        autoUser = findViewById(R.id.eTxtUsuario);
        autoRol = findViewById(R.id.eTxtRol);
        Update = findViewById(R.id.btnActualizar);
        Back = findViewById(R.id.btnAtrasA);
        Delete = findViewById(R.id.btnEliminar);

        context = this;

        String id = getIntent().getStringExtra("Id");
        autoName.setText(getIntent().getStringExtra("Name"));
        autoUser.setText(getIntent().getStringExtra("User"));
        autoRol.setText(getIntent().getStringExtra("Rol"));

        
        Back.setOnClickListener(view -> {

            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });


        Delete.setOnClickListener(view -> {

            deleteUser(id);

            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });

        //Botón de actualizar
        Update.setOnClickListener(view -> {
                //validacion de campos vacios
            if (autoUser.getText().toString().isEmpty() || autoName.getText().toString().isEmpty() ||  autoRol.getText().toString().isEmpty()) {
                Toast.makeText(context, "Campos vacios", Toast.LENGTH_SHORT).show();
            }else {

                updateUser(id, new User(
                        autoName.getText().toString(),
                        autoUser.getText().toString(),
                        "0",
                        autoRol.getText().toString())
                );
                //Al terminar la actualizacion, se envia nuevamente a la interfaz principal
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void deleteUser(String i) {
        //A traves de enqueue y el id, se hace la peticion por deleteInfoService y se elimina el usuario
        Call<InfoResponse> respInfo = (new InfoServices().deleteInfoService(i));
        respInfo.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {

                Toast.makeText(context, "Conexion establecida y usuario eliminado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {

                Log.i("Info", "Conexión denegada");
            }
        });
    }


    private void updateUser(String i, User u) {
        //A traves de enqueue y el id, se hace la peticion put a updateInfoService del servicio
        Call<InfoResponse> respInfo = (new InfoServices().updateInfoService(i, u));
        respInfo.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {

                Toast.makeText(context, "Conexion establecida y usuario actualizado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {

                Log.i("Info", "Conexión denegada");
            }
        });
    }
}