package com.example.sam.broadcastreceiverstest;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String storeName = "CVS";
    String latitude = "40.442279";
    String longitude = "-80.002267";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view){
//        Intent newIntent = new Intent("com.example.travel.travelapp");
//        sendBroadcast(newIntent, android.Manifest.permission.VIBRATE);

        String p = "com.example.travel.travelapp";
        Toast.makeText(this,p, Toast.LENGTH_LONG);
        Log.v("broadcast", p);
        Intent intent = new Intent();
        intent.setAction("com.example.travel.travelapp.DISCONNECT");
        intent.setClassName(p, p+".MyReceiver");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra("storeInfo", storeName + "/" + latitude + "/" + longitude);
        sendBroadcast(intent);
    }
}
