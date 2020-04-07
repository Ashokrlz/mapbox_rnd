package com.example.mbtilesdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.sources.VectorSource;

import java.io.IOException;
import java.io.InputStream;

public class MapsActivity extends AppCompatActivity {
    private MapView mapView;
    private MapboxMap mapboxMap;
    private MBTilesSource mbSource;
    private String jsonLocation ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));

        setContentView(R.layout.activity_maps);

        mapView = findViewById(R.id.mb_map_view);
        mapView.onCreate(savedInstanceState);
        String path = getApplicationContext().getApplicationInfo().dataDir + "/qatar.mbtiles";
        final String stylePath = getApplicationContext().getApplicationInfo().dataDir + "/style.json";

        String sourceId = null;
        try {
            mbSource = new MBTilesSource(path, sourceId);
            mbSource.activate();
        } catch (Exception ex) {
            Log.d("Error", ex.getMessage());
        }


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                //mapboxMap.setStyle(new Style.Builder().withSource(new VectorSource(mbSource.getId(), mbSource.getUrl())));

                mapboxMap.setStyle(Style.LIGHT, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        style.addSource(
                                new VectorSource(mbSource.getId(), mbSource.getUrl())
                        );
                    }

                });
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}

