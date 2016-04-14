package com.example.agi51.tryemcomerce.Utilities;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by agi35 on 21/1/16.
 */
public class ECommerceApp extends Application implements LocationListener {
    private Context context = this;
    private LocationManager locationManager;
    @Override
    public void onCreate() {
        super.onCreate();
        MyVolley.init(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

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
