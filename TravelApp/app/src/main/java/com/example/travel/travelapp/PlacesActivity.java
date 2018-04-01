package com.example.travel.travelapp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.hardware.camera2.params.BlackLevelPattern;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PlacesActivity extends AppCompatActivity {
    private static HashMap<String, Boolean> visited = new HashMap<String, Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        // Get Neighborhood Information from Intent
        Intent intent = getIntent();

        final String neighborhood = intent.getStringExtra("neighborhood");
        String place = intent.getStringExtra("place");

        Log.v("neighborhood", ""+neighborhood);
        Log.v("place", ""+place);

//        IntentFilter intentFilter=new MyReceiver =new MyBroadcastReceiver();
//        IntentFilter("com.pkg.perform.Ruby");
//        if(intentFilter!=null)
//        {
//            registerReceiver(MyReceiver,intentFilter);
//
//        }

        // Add the Neighborhood to the TextView
        TextView txtNeighborhood = (TextView) findViewById(R.id.txtNeighborhood);
        txtNeighborhood.setText(neighborhood);



        final LinearLayout ll = findViewById(R.id.checkBoxLayout);

        //Get username
        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccountsByType("com.google");
        final String username = accounts[0].name.split("@")[0];

        //TODO: this will need to go inside a database call looping through each option
        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        //event listeners
        fb.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot data){
                ll.removeAllViews();
                DataSnapshot user_child = data.child("users").child(username).child("neighborhoods")
                                                .child(neighborhood);
                for(DataSnapshot place: user_child.getChildren()){
                    visited.put(place.getKey(), true);
                }
                DataSnapshot places_child = data.child("neighborhoods").child(neighborhood).child("places");
                for(final DataSnapshot place: places_child.getChildren()){
                    final String place_holder = place.getKey();
                    CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setText(place.getKey());
                    cb.setTextColor(Color.BLACK);
                    if(visited.get(place.getKey()) != null){
                        cb.setChecked(true);
                    }else{
                        cb.setChecked(false);
                    }

                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked){
                                addToDB(neighborhood, place_holder);
                            } else{
                                removeFromDB(neighborhood, place_holder);
                                PlacesActivity.visited.remove(place_holder);
                            }
                        }
                    });
                    ll.addView(cb);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });

    }
    private void addToDB(String neighborhood, String place){
        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccountsByType("com.google");
        final String username = accounts[0].name.split("@")[0];
        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        fb.child("users").child(username).child("neighborhoods")
                                        .child(neighborhood).child(place).setValue(true);
        return;
    }

    private void removeFromDB(String neighborhood, final String place){

        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccountsByType("com.google");
        final String username = accounts[0].name.split("@")[0];
        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        fb.child("users").child(username).child("neighborhoods")
                .child(neighborhood).child(place).removeValue();
    }
    // Back button click
    public void back(View view) {
        finish();
    }


}

