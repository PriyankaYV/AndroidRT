package com.example.agi51.yummyfood.Activities.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.agi51.yummyfood.Activities.utilities.Utility;
import com.example.agi51.yummyfood.R;

/**
 * Created by agi51 on 1/3/16.
 */
public class ConnectionLost extends Activity {

    Button retry;
    public boolean isFirstRun;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_lost);
        animation = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        retry = (Button)findViewById(R.id.retry);

        isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retry.startAnimation(animation);

                if (Utility.checkInternet(ConnectionLost.this)) {

                    if (!isFirstRun) {

                        Intent intent = new Intent(ConnectionLost.this, SignInActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Intent intent = new Intent(ConnectionLost.this, AppIntroGitActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }
}
