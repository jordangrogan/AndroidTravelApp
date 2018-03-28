package com.example.travel.travelapp;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.params.BlackLevelPattern;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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



        LinearLayout ll = findViewById(R.id.checkBoxLayout);


        //TODO: this will need to go inside a database call looping through each option
        CheckBox cb = new CheckBox(getApplicationContext());
        cb.setText("Add places here");
        cb.setTextColor(Color.BLACK);
        cb.setChecked(true); //TODO: depending on what the user has checked this will need to be set to true or false
        ll.addView(cb);



    }

    // Back button click
    public void back(View view) {
        finish();
    }
}
