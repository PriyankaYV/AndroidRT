package com.rohit.myapplication;

import android.app.Application;

/**
 * Created by rohit on 25/1/16.
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
