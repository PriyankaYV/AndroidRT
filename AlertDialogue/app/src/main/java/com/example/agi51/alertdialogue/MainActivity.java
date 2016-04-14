package com.example.agi51.alertdialogue;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    Button button;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.btnAlerts);
        tv1 = (TextView)findViewById(R.id.tv1);
        button.setOnClickListener(this);
    }

    public void onClick(View view){
       AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setMessage("Do you accept all the terms and condition..??");
        ad.setIcon(R.drawable.mark);
        ad.setTitle("Terms Of Service");
        ad.setPositiveButton("Yes", this);
        ad.setNegativeButton("No", this);
        ad.setNeutralButton("Cancel", this);
        ad.setCancelable(false);
        ad.create();
        ad.show();
    }

    public void onClick(DialogInterface dialog, int which){
        switch (which){

            case DialogInterface.BUTTON_POSITIVE:
                tv1.setText("You have accepted TOS.. Welcome to the site..!!");
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                tv1.setText("You denied TOS. You cannot access..");
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                tv1.setText("Please select Yes or No");
        }
    }
}
