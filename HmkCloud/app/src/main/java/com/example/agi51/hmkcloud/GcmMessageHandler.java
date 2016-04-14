package com.example.agi51.hmkcloud;

/**
 * Created by agi51 on 3/2/16.
 */

import com.google.android.gms.gcm.GcmListenerService;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.IntentService;
import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class GcmMessageHandler extends GcmListenerService {

    private static final String TAG = "GCM handler";
    String mes;
    private Handler handler;


    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        mes = data.getString("message");
        Log.d(TAG, "from" + from + "message " + data.getString("message"));
        showToast();
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        handler = new Handler();
    }


    public void showToast(){
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(),mes, Toast.LENGTH_LONG).show();
            }
        });

    }

}

