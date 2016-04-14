package com.example.agi51.cloudmessaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registrationIntent = new Intent();
                registrationIntent.setClassName("com.google.android.c2dm.intent", "REGISTER");
                registrationIntent.putExtra("app", PendingIntent.getBroadcast(v.getContext(),0,new Intent(),0));
                registrationIntent.putExtra("sender","299192778171");
                startService(registrationIntent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unregIntent = new Intent();
                unregIntent.setClassName("com.google.android.c2dm.intent", "UNREGISTER");
                unregIntent.putExtra("app",PendingIntent.getBroadcast(v.getContext(),0,new Intent(),0));
                startService(unregIntent);
            }
        });
    }
}
