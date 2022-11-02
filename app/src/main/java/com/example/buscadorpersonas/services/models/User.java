package com.example.buscadorpersonas.services.models;

public class User {

    String nombre;
    String email;

    public User(String names, String username, String password) {
        this.nombre = names;
        this.email = username;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;
}
