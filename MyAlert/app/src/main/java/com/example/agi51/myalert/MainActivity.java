package com.example.agi51.myalert;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showDialogue(View view){
        MyAlert myAlert = new MyAlert();
        myAlert.show(getFragmentManager(),"My alert");
    }
}
