package com.example.marce.mapas;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.marce.mapas.datosAPI.PlantasService;
import com.example.marce.mapas.models.Plantas;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Retrofit retrofit;
    private float lat, lng;
    public static final String TAG = "datosPeajeColombia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        PlantasService service = retrofit.create(PlantasService.class);
        Call<List<Plantas>> sitioRespuestaCall = service.obtenerListadeSitios();
        sitioRespuestaCall.enqueue(new Callback<List<Plantas>>() {
            @Override

            public void onResponse(Call<List<Plantas>> call, Response<List<Plantas>> response) {
                if(response.isSuccessful()){  // Si llegaron los datos
                    List lista = response.body();
                    for(int i=0; i<lista.size(); i++){
                        Plantas m = (Plantas) lista.get(i);
                        Log.i(TAG,"Nombre: "+m.getNombre() +" Latitud: "+m.getLatitud() + " Longitud: " + m.getLongitud());
                        lat = Float.parseFloat(m.getLatitud());
                        lng = Float.parseFloat(m.getLongitud());

                        LatLng place =  new LatLng(lat, lng);

                        mMap.addMarker(new MarkerOptions()
                                .position(place)
                                .title(m.getNombre())
                                .snippet("Tipo: "+ m.getTipo() + "\nUbicación: " + m.getUbicaciN())

                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                        );
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place,5));
                    }
                }

                else{
                    Log.e(TAG,"OnResponse: "+ response.errorBody());
                }

            }
            @Override
            public void onFailure(Call<List<Plantas>> call, Throwable t) {
                Log.e(TAG," OnFailure: "+ t.getMessage());
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){

            @Override
            public boolean onMarkerClick(Marker marker){
                Toast.makeText(getApplicationContext(),marker.getSnippet(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }


}
