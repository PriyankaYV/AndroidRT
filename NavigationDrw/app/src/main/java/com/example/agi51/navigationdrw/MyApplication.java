package com.example.agi51.navigationdrw;

import android.app.Application;

/**
 * Created by agi51 on 6/2/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Initialize Volley library at the start of the application .
         * add android:name="classname" to application Tag in android manifest
         * this executes the class at the start of app
         */
        MyVolley.init(this);
    }
}
