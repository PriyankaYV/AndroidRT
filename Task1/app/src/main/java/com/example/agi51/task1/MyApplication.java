package com.example.agi51.task1;

import android.app.Application;

/**
 * Created by agi51 on 28/1/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyVolley.init(this);
    }
}
