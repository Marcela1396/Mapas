package com.example.marce.mapas.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Plantas {
    @SerializedName("coordenadas")
    @Expose
    private Coordenadas coordenadas;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("ubicaci_n")
    @Expose
    private String ubicaciN;

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicaciN() {
        return ubicaciN;
    }

    public void setUbicaciN(String ubicaciN) {
        this.ubicaciN = ubicaciN;
    }

}






