package com.example.agi51.hmkcloud;

import java.io.IOException;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.agi51.hmkcloud.GcmMessageHandler;

public class MainActivity extends Activity implements OnClickListener {

    Button btnRegId;
    EditText etRegId;
    TextView tv1s;
    GoogleCloudMessaging gcm;
    String regid;
    String PROJECT_NUMBER ="299192778171";
    GcmMessageHandler ghm = new GcmMessageHandler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegId = (Button) findViewById(R.id.btnGetRegId);
        tv1s = (TextView)findViewById(R.id.tv1);
        etRegId = (EditText) findViewById(R.id.etRegId);
        btnRegId.setOnClickListener(this);
    }
    public void getRegId(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(PROJECT_NUMBER);
                    msg = "Device registered, registration ID=" + regid;
                    Log.i("GCM",  msg);


                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();

                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                etRegId.setText(msg);
            }
        }.execute(null, null, null);
    }

    @Override
    public void onClick(View v) {
        getRegId();

    }


    public void openRecycler(View v){

        Intent intent = new Intent(this, MainRecycler.class);
        startActivity(intent);
    }



}