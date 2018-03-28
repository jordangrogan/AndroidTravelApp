package com.example.travel.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private ArrayList<MarkerOptions> neighborhoodMarkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Create ArrayList of neighborhood markers
        neighborhoodMarkers = new ArrayList<MarkerOptions>();
        // TODO: Connect to Firebase - populate these dynamically (neighborhood name, long, lat)
        neighborhoodMarkers.add(new MarkerOptions().position(new LatLng(40.446765, -80.015760)).title("North Side"));
        neighborhoodMarkers.add(new MarkerOptions().position(new LatLng(40.444253, -79.953239)).title("Oakland"));
        neighborhoodMarkers.add(new MarkerOptions().position(new LatLng(40.440413, -80.002602)).title("Downtown"));

        // Create the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMinZoomPreference(12);
        LatLng pgh = new LatLng(40.440625, -79.995886);
        googleMap.setOnMarkerClickListener(this);
        for(MarkerOptions marker : neighborhoodMarkers) { // Add the markers
            googleMap.addMarker(marker);
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pgh));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        String neighborhood = marker.getTitle(); // Get the title from the marker to denote the neighborhood

        // Start Places Activity with the neighborhood information
        Intent intent = new Intent(this, PlacesActivity.class);
        intent.putExtra("neighborhood", neighborhood);
        startActivity(intent);

        return false;

    }

}