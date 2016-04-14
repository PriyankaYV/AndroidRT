package com.example.agi51.yummyfood.Activities.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.example.agi51.yummyfood.R;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by agi51 on 18/2/16.
 */
public class AppIntroGitActivity extends AppIntro2 {

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();

        addSlide(AppIntroFragment.newInstance(
                "Welcome To FOODYUMMMY",
                "\n" +" Foodyummy, together with its affiliated brands hellofood and Delivery Club, is one of the world’s largest online food ordering marketplaces.",
                R.mipmap.logofinal,
                ContextCompat.getColor(this, R.color.grey)));

        addSlide(AppIntroFragment.newInstance(
                "Our Working Place",
                "We are a highly motivated and talented team, driven by the ambition to become the global synonym for online food ordering.We launched our first market, Madhapur, in 2015 and are headquartered in Madhapur.Currently we are active in more than 40 areas in Gachchibowli, Begumpet, Ameerpet, Chandanagar and S R Nagar.",
                R.mipmap.f,
                getResources().getColor(R.color.green)));

        addSlide(AppIntroFragment.newInstance("Try Our Delicious Food", "\n" +
                        "Popular Dishes\n" +
                        "\n" +
                        "Some of Hyderabad's fussiest diners are also the littlest ones and it's a tricky task pleasing every member of a hungry family at once.\n",
                R.mipmap.dum_biryani, getResources().getColor(R.color.base)));


        setVibrate(true);
        setVibrateIntensity(30);

        addSlide(AppIntroFragment.newInstance("Starters", "\n" +
                        "" +
                        "\n" +
                        "Every celebrity chef worth their salt has their own restaurant - and the really famous ones have a whole chain of them.\n",
                R.mipmap.nn, getResources().getColor(R.color.purple)));

    }

    @Override
    public void onDonePressed() {

        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onSlideChanged() {

    }
}
