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

        String neighborhood = intent.getStringExtra("neighborhood");
        String place = intent.getStringExtra("place");

        Intent placesIntent = new Intent(context, PlacesActivity.class);

        placesIntent.putExtra("neighborhood", neighborhood);
        placesIntent.putExtra("place", place);
        context.startActivity(placesIntent);

    }
}
