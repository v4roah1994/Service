package com.example.buscadorpersonas.services.models;

public class InfoApi {

    private String nombre;
    private String apellido;
    private String edad;
    private String tidoDocumentos;
    private String numeroDoc;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTidoDocumentos() {
        return tidoDocumentos;
    }

    public void setTidoDocumentos(String tidoDocumentos) {
        this.tidoDocumentos = tidoDocumentos;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }
}
