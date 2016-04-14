package com.example.agi51.tryemcomerce.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.agi51.tryemcomerce.R;
import com.example.agi51.tryemcomerce.Utilities.Utility;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import xhome.uestcfei.com.loadingpoppoint.LoadingPopPoint;

public class SplashScreenActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    private static final String TAG = "SplashScreen";
    private static final int REQUEST_SET_CHECKING = 123;
    ImageView imageViewSplash;
    LoadingPopPoint loadingPopPoint;
    GoogleApiClient mGoogleApiClient;
    Location mLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
        setContentView(R.layout.activity_splashscreen);
        imageViewSplash = (ImageView) findViewById(R.id.splimv);
        loadingPopPoint = (LoadingPopPoint) findViewById(R.id.idpoppoint);
        imageViewSplash.setScaleType(ImageView.ScaleType.FIT_XY);
        initGoogleApi();
    }

    private void initGoogleApi() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
        finish();
    }

    @Override
    public void onConnected(Bundle bundle) {
        settingRequest();
    }

    private void settingRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                final LocationSettingsStates states = locationSettingsResult.getLocationSettingsStates();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (ActivityCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                                Utility.write(SplashScreenActivity.this, "lat", mLocation.getLatitude() + "");
                                Utility.write(SplashScreenActivity.this, "long", mLocation.getLongitude() + "");
                                Utility.logThis(TAG, mLocation.toString());
                            }
                        }, 3000);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (Utility.checkInternet(SplashScreenActivity.this)) {
                                    startActivity(new Intent(SplashScreenActivity.this, AppIntroActivity.class));
                                } else {
                                    Utility.getAlertDialogue(SplashScreenActivity.this, null, "Please connect to network", null, "ok").show();
                                }
                            }
                        }, 3000);
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(SplashScreenActivity.this, REQUEST_SET_CHECKING);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_SET_CHECKING:
                switch (resultCode) {
                    case RESULT_OK:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (ActivityCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                                Utility.write(SplashScreenActivity.this,"lat",mLocation.getLatitude()+"");
                                Utility.write(SplashScreenActivity.this,"lon",mLocation.getLongitude()+"");
                                Utility.logThis(TAG,mLocation.toString());
                            }
                        },3000);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (Utility.checkInternet(SplashScreenActivity.this)) {
                                    startActivity(new Intent(SplashScreenActivity.this, AppIntroActivity.class));
                                } else {
                                    Utility.getAlertDialogue(SplashScreenActivity.this, null, "Please turn On the internet and retry ", null, "OK").show();
                                }

                            }
                        }, 3000);
                        break;
                    case RESULT_CANCELED:
                        settingRequest();
                        break;
                }
                break;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }


}
