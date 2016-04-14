package com.example.agi51.cloudmessaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by agi51 on 3/2/16.
 */
public class CloudBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        try{
            String action = intent.getAction();
            if(action.equals("com.google.android.c2dm.intent.REGISTRATION")){
                String registrationID = intent.getStringExtra("reg_id");
                Log.i("uo",registrationID);
                String error = intent.getStringExtra("error");
                String unregistered = intent.getStringExtra("unregistered");
            }else if(action.equals("com.google.android.c2dm.intent.RECEIVE")){
                String data1 = intent.getStringExtra("data1");
                String data2 = intent.getStringExtra("data2");
            }

        }finally {

        }
    }
}
