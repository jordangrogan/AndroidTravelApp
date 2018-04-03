package com.example.travel.travelapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BucketListActivity extends ListActivity {

    ArrayList<String> bucketItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    String username = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_list);
        Intent intent = getIntent();
        username = intent.getStringExtra("name");
        makeList();



    }

    public void addBucket(View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Add New Activity");
        alert.setMessage("Bucket List Activity");
        final DatabaseReference fb = FirebaseDatabase.getInstance().getReference();

        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                fb.child("users").child(username).child("activities").child(input.getText().toString());
                fb.child("users").child(username).child("activities").child(input.getText().toString()).setValue(false);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        AlertDialog builder = alert.create();
        builder.show();
        makeList();



    }

    private void makeList(){

        adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item, bucketItems);
        setListAdapter(adapter);
        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {

                if (data.child("users").child(username).child("activities").exists()) {
                    DataSnapshot activites_child = data.child("users").child(username).child("activities");
                    for (DataSnapshot activities_child : data.getChildren()) {
                        String activity = activities_child.getKey();
                        bucketItems.add(activity);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError){
                //error
            }

        });


    }
}