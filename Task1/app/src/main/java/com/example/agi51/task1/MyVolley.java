package com.example.agi51.task1;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by agi51 on 27/1/16.
 */
public class MyVolley {
    private static RequestQueue mRequestQueue;
    public static void init(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }
    public static RequestQueue getRequestQueue(){
        if(mRequestQueue != null){
            return mRequestQueue;
        }else{
            throw new IllegalStateException("Request queue not initialized");
        }

    }
}
