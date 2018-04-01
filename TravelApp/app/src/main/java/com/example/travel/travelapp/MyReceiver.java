package com.example.travel.travelapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by burri on 3/28/2018.
 */

public class MyReceiver extends BroadcastReceiver {

    private final static String TAG = MyReceiver.class.getSimpleName();

    public MyReceiver() {
        Log.d(TAG, "==========================================");
        Log.d(TAG, "MyReceiver: "+MyReceiver.class.getCanonicalName());
        Log.d(TAG, "==========================================");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "==========================================");
        Log.d(TAG, "onReceive: ");
        Log.d(TAG, "==========================================");

        Toast toast = Toast.makeText(context, "received", Toast.LENGTH_SHORT);
        toast.show();

        String storeInfo = intent.getStringExtra("storeInfo");
        String[] info = storeInfo.split("/"); //[0] is name, [1] is longitude, [2] is latitude
        //TODO: pick a neighborhood based off of latatude and longitude
        Intent placesIntent = new Intent(context, PlacesActivity.class);

        placesIntent.putExtra("neighborhood", "Oakland");
        placesIntent.putExtra("place", info[0]);
        context.startActivity(placesIntent);

    }
}
