package com.example.agi51.tryemcomerce.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.Patterns;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.example.agi51.tryemcomerce.BuildConfig;
import com.example.agi51.tryemcomerce.R;

/**
 * Created by agi51 on 8/2/16.
 */
public class Utility {

    private static SharedPreferences sharedPreferences;
    public static void logThis(String tag, String message){
        if(BuildConfig.DEBUG){
            Log.d(tag, message);
        }
    }
    public static boolean checkInternet(Context context){
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Network[] network = new Network[0];
            network = cm.getAllNetworks();
            NetworkInfo networkInfo;
            for(Network network1:network){
                networkInfo = cm.getNetworkInfo(network1);
                if(networkInfo.getState().equals(NetworkInfo.State.CONNECTED)){
                    return true;
                }
            }
        }

        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        NetworkInfo[] var8 = networkInfos;
        int var7 = networkInfos.length;

        for(int var6=0;var6<var7;++var6){
            NetworkInfo ni = var8[var6];
            if(ni.getTypeName().equalsIgnoreCase("WIFI") && ni.isConnected()){
                haveConnectedWifi = true;
            }
            if(ni.getTypeName().equalsIgnoreCase("MOBILE") && ni.isConnected()){
                haveConnectedMobile = true;
            }


        }
        return !(!haveConnectedWifi && !haveConnectedMobile);
    }

    public static AlertDialog getAlertDialogue(final Context context,String title, String message, final Class claz,String positivetext){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogueCustom));
        if(title!=null){
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(positivetext, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(claz!=null) {
                    context.startActivity(new Intent(context, claz).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }else{
                    Activity activity = (Activity)context;
                    activity.finish();
                    dialog.dismiss();
                }
            }
        });
        return builder.create();
    }

    public static DefaultRetryPolicy getRetryPolicy(){
        DefaultRetryPolicy policy = new DefaultRetryPolicy(0,-1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return policy;
    }

    public static boolean validateEmail(String email) {
        if(email != null && !email.trim().equals("")) {
            String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Boolean b = Boolean.valueOf(email.matches(emailreg));
            return b.booleanValue();
        } else {
            return false;
        }
    }

    public static boolean validatePhone(String phnNum){
        if(phnNum.length() <10 || phnNum.length() > 12){
            return false;
        }else{
            if(!Patterns.PHONE.matcher(phnNum).matches()){
                return false;
            }
        }
        return true;
    }

    public static void write(Context context,String key,String value){
        sharedPreferences= context.getSharedPreferences(Constants.MY_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void write(Context context,String key,int value){
        sharedPreferences = context.getSharedPreferences(Constants.MY_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void write(Context context,String key ,boolean value){
        sharedPreferences = context.getSharedPreferences(Constants.MY_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public static void write(Context context,String key ,long value){
        sharedPreferences = context.getSharedPreferences(Constants.MY_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static String readString(Context context,String key){
        sharedPreferences = context.getSharedPreferences(Constants.MY_PREFS,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }

    public static boolean readBoolean(Context context, String key){
        sharedPreferences = context.getSharedPreferences(Constants.MY_PREFS,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static int readInt(Context context, String key){
        sharedPreferences = context.getSharedPreferences(Constants.MY_PREFS,Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(key, 0);
    }

    public static float readFloat(Context context, String key){
        sharedPreferences = context.getSharedPreferences(Constants.MY_PREFS,Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, 0);
    }
    public static void clearSharedPreferences(Context context, String key){
        sharedPreferences = context.getSharedPreferences(Constants.MY_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void toastLong(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void toastShort(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
