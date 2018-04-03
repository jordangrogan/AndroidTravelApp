package com.example.travel.travelapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("name");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView bio = (TextView) findViewById(R.id.bio);
                TextView score = (TextView) findViewById(R.id.score);
                DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
                fb.child("users").child(username).child("bio").setValue(bio.getText().toString());
                fb.child("users").child(username).child("score").setValue(score.getText().toString());
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
