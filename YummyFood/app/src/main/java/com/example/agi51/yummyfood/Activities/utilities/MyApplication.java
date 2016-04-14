package com.example.agi51.yummyfood.Activities.utilities;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class MyApplication extends Application implements LocationListener{

    private Context context=this;
    private LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Initialize Volley library at the start of the application .
         * add android:name="classname" to application Tag in android manifest
         * this executes the class at the start of app
         */
        MyVolley.init(this);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
