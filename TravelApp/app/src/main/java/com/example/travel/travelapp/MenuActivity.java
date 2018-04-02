package com.example.travel.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    private static String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent i = getIntent();
        username = i.getStringExtra("name");
    }

    public void clickMapButton(View view){
        Intent mapIntent = new Intent(this, MapActivity.class );
        mapIntent.putExtra("name", username);
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

    public void clickBroadcastButton(View view){
        Intent finishedIntent = new Intent("edu.pitt.cs1699.stocks.BALANCE");
        sendBroadcast(finishedIntent);
    }
}
