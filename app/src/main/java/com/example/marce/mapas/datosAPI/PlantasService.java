package com.example.marce.mapas.datosAPI;

import retrofit2.Call;

import com.example.marce.mapas.models.Plantas;

import java.util.List;

import retrofit2.http.GET;

/**
 * Created by marce on 06/11/2017.
 */

public interface PlantasService {
    @GET("e9kg-ftpd.json")
    Call<List<Plantas>> obtenerListadeSitios();
}
