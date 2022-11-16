package com.example.buscadorpersonas;

import androidx.appcompat.app.AppCompatActivity;

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

        //Botón de volver
        Back.setOnClickListener(v -> {

            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });

        //Botón de borrar
        Delete.setOnClickListener(v -> {

            deleteUser(id);

            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });

        //Botón de actualizar
        Update.setOnClickListener(v -> {

            if (autoUser.getText().toString().isEmpty() ||
                    autoName.getText().toString().isEmpty() ||
                    autoRol.getText().toString().isEmpty()) {
                Toast.makeText(context, "Campos vacios", Toast.LENGTH_SHORT).show();
            }else {

                updateUser(id, new User(
                        autoName.getText().toString(),
                        autoUser.getText().toString(),
                        "0", //addPassword.getText().toString(),
                        autoRol.getText().toString())
                );

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Método para borrar un usuario que toma un ID
    private void deleteUser(String i) {
        //Hace la petición DELETE
        Call<InfoResponse> respInfo = (new InfoServices().deleteInfoService(i));
        respInfo.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {

                Log.i("Info", "Conexión establecida");
                Log.i("Info", "Usuario eliminado");
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {

                Log.i("Info", "Conexión denegada");
                Log.i("Info", t.getCause().getMessage());
            }
        });
    }


    private void updateUser(String i, User u) {
        //petición PUT
        Call<InfoResponse> respInfo = (new InfoServices().updateInfoService(i, u));
        respInfo.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {

                Log.i("Info", "Conexión establecida");
                Log.i("Info", "Usuario actualizado");
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {

                Log.i("Info", "Conexión denegada");
                Log.i("Info", t.getCause().getMessage());
            }
        });
    }
}