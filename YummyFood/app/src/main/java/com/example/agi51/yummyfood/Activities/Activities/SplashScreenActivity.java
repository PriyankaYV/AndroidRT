package com.example.agi51.yummyfood.Activities.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agi51.yummyfood.Activities.utilities.Utility;
import com.example.agi51.yummyfood.R;

public class SplashScreenActivity extends AppCompatActivity {

    TextView tvCenter,tvPopup,tvTitle;
    ImageView tvImage;
    Animation myAnimation,popAnimation,myanimslow;
    private int SPLASH_DISPLAY_LENGTH = 3000;
    public boolean isFirstRun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tvImage = (ImageView)findViewById(R.id.idtitleimg);
        tvTitle=(TextView)findViewById(R.id.idTitleText);
        tvCenter=(TextView)findViewById(R.id.tvcenter);
        tvPopup=(TextView)findViewById(R.id.idHeading);



        isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);



       myAnimation = AnimationUtils.loadAnimation(this, R.anim.myanimation);

        myanimslow = AnimationUtils.loadAnimation(this, R.anim.myanimslow);
        tvImage.startAnimation(myAnimation);
        tvTitle.startAnimation(myAnimation);
       tvPopup.startAnimation(myanimslow);

        popAnimation = AnimationUtils.loadAnimation(this, R.anim.pushup);
        tvCenter.startAnimation(popAnimation);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {


                if (!Utility.checkInternet(SplashScreenActivity.this)) {

                    Intent intent = new Intent(SplashScreenActivity.this,ConnectionLost.class);
                    startActivity(intent);
                    finish();

                }

                else if(isFirstRun){

                    Intent mainIntent = new Intent(SplashScreenActivity.this, AppIntroGitActivity.class);
                    startActivity(mainIntent);
                    finish();
                }else{
                    Intent min = new Intent(SplashScreenActivity.this,SignInActivity.class);
                    startActivity(min);
                    finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);


    }


}
