package com.example.travel.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void clickMapButton(View view){
        Intent mapIntent = new Intent(this, MapActivity.class );
        startActivity(mapIntent);
    }

    public void clickBucketButton(View view){
        Intent bucketIntent = new Intent(this, BucketListActivity.class);
        startActivity(bucketIntent);
    }

    public void clickProfileButton(View view){
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileIntent);
    }
}
