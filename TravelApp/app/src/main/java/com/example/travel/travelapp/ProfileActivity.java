package com.example.travel.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        username = intent.getStringExtra("name");
        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usertable = fb.child("users").child(username);
        usertable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView name = (TextView) findViewById(R.id.name);
                TextView bio = (TextView) findViewById(R.id.bio);
                TextView score = (TextView) findViewById(R.id.score);
                name.setText(username);
                if(dataSnapshot.child("bio").exists()){
                    bio.setText(dataSnapshot.child("bio").getValue(String.class));
                } else {
                    bio.setText("Fill in your bio!");
                }
                if(dataSnapshot.child("score").exists()){
                    score.setText(dataSnapshot.child("score").getValue(String.class));
                } else {
                    bio.setText("0 pts");
                    dataSnapshot.child("score").getRef().setValue(0);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void clickEditProfileButton(View view){
        Intent profileIntent = new Intent(this, EditProfileActivity.class);
        profileIntent.putExtra("name", username);
        startActivity(profileIntent);
    }
}
