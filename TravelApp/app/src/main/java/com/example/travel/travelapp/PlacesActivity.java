package com.example.travel.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlacesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        // Get Neighborhood Information from Intent
        Intent intent = getIntent();
        String neighborhood = intent.getStringExtra("neighborhood");

        // Add the Neighborhood to the TextView
        TextView txtNeighborhood = (TextView) findViewById(R.id.txtNeighborhood);
        txtNeighborhood.setText(neighborhood);

    }

    // Back button click
    public void back(View view) {
        finish();
    }
}
