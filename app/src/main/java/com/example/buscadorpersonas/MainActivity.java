package com.example.buscadorpersonas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buscadorpersonas.services.InfoServices;
import com.example.buscadorpersonas.services.dataResponse.InfoResponse;
import com.example.buscadorpersonas.services.models.InfoApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView listUsers;
    EditText txtDocument;
    Button btnAdd, btnSearch;

    ArrayList<String> listUser;
    InfoApi user;

    MainActivity context;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listUsers = findViewById(R.id.txtMostrar);
        txtDocument = findViewById(R.id.TxtDocumento);
        btnAdd = findViewById(R.id.btnCrear);
        btnSearch = findViewById(R.id.btnBuscar);

        context = this;

        loadUser(-1);

        //Botón buscar
        btnSearch.setOnClickListener(v -> {
            if (txtDocument.getText().toString().isEmpty()) {
                loadUser(-1);
                return;
            }
            loadUser(Integer.parseInt(txtDocument.getText().toString()));
        });

        //Botón crear
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(context, CreacionActivity.class);
            startActivity(intent);
        });


        listUsers.setOnItemClickListener((adapterView, view, i, l) -> {

            String[] cadenas = listUsers.getItemAtPosition(i).toString().split(" - ");
            Intent intent = new Intent(context, VisualizarActivity.class);
            intent.putExtra("Id", cadenas[0]);
            intent.putExtra("Name", cadenas[1]);
            intent.putExtra("User", cadenas[2]);
            intent.putExtra("paramsRol", cadenas[3]);
            startActivity(intent);
        });




    }

    private void loadUser(int id) {
        //GET
        Call<InfoResponse> respInfo = (new InfoServices().getInfoService());
        respInfo.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {

                listUser = new ArrayList<>();
                InfoResponse res = response.body();

                for (int i = 0; i < res.data.size(); i++) {
                    if (id == -1) {

                        user = new InfoApi(
                                res.data.get(i).getId(),
                                res.data.get(i).getNames(),
                                res.data.get(i).getUsername(),
                                res.data.get(i).getRol()
                        );
                        listUser.add(user.toString());
                    } else if (res.data.get(i).getId() == id) {

                        user = new InfoApi(
                                res.data.get(i).getId(),
                                res.data.get(i).getNames(),
                                res.data.get(i).getUsername(),
                                res.data.get(i).getRol()
                        );
                        listUser.add(user.toString());
                    }
                }

                if (listUser.isEmpty()){
                    Toast.makeText(context, "no existe el usuario", Toast.LENGTH_SHORT).show();
                }

                listUsers.setAdapter(new ArrayAdapter(context, android.R.layout.simple_list_item_1, listUser));
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                Log.i("Info", "Conexión denegada");
            }
        });
    }
}