package com.example.agi51.yummyfood.Activities.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.util.Patterns;

import com.android.volley.DefaultRetryPolicy;
import com.example.agi51.yummyfood.BuildConfig;
import com.example.agi51.yummyfood.R;

/**
 * Created by agi51 on 1/3/16.
 */
public class Utility {
    private static SharedPreferences preferences;

    public static void logThis(String tag, String message) {

        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

    public static boolean checkInternet(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = new Network[0];
            networks = cm.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = cm.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        }

        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        NetworkInfo[] var8 = netInfo;
        int var7 = netInfo.length;

        for (int var6 = 0; var6 < var7; ++var6) {
            NetworkInfo ni = var8[var6];
            if (ni.getTypeName().equalsIgnoreCase("WIFI") && ni.isConnected()) {
                haveConnectedWifi = true;
            }

            if (ni.getTypeName().equalsIgnoreCase("MOBILE") && ni.isConnected()) {
                haveConnectedMobile = true;
            }
        }


        return !(!haveConnectedWifi && !haveConnectedMobile);
    }

    public static AlertDialog getAlertDialog(final Context context, String title, String message, final Class clazz, String postivetext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom));
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(postivetext, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (clazz != null) {
                    context.startActivity(new Intent(context, clazz).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                } else {
                    Activity activity = (Activity) context;
                    activity.finish();
                    dialog.dismiss();
                }

            }
        });
        return builder.create();
    }

    public static DefaultRetryPolicy getRetryPolicy() {
        DefaultRetryPolicy policy = new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return policy;
    }

}