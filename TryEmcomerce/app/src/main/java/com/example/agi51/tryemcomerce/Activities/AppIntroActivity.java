package com.example.agi51.tryemcomerce.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.agi51.tryemcomerce.R;
import com.example.agi51.tryemcomerce.SupportViews.CirclePageIndicator;

/**
 * Created by agi51 on 9/2/16.
 */
public class AppIntroActivity extends AppCompatActivity {

    private static final String SAVING_STATE_SLIDER_ANIMATION ="sliderAnimationSavingstate" ;
    private boolean isSliderAnimation=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Window window = getWindow();
        //window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_app_intro);

        ViewPager viewPager = (ViewPager)findViewById(R.id.idPager);
        viewPager.setAdapter(new ViewPagerAdapter(R.array.icons, R.array.titles, R.array.hints));

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(viewPager);

        viewPager.setPageTransformer(true, new CustomPageTransformer());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {

                View landingBGView = findViewById(R.id.landing_backgrond);
                int colorBg[] = getResources().getIntArray(R.array.landing_bg);


                ColorShades shades = new ColorShades();
                shades.setFromColor(colorBg[position % colorBg.length])
                        .setToColor(colorBg[(position + 1) % colorBg.length])
                        .setShade(positionOffset);

                landingBGView.setBackgroundColor(shades.generate());

            }

            public void onPageSelected(int position) {

            }

            public void onPageScrollStateChanged(int state) {
            }
        });
    }



    public class ViewPagerAdapter extends PagerAdapter {

        private int iconResId, titleArrayResId, hintArrayResId;

        public ViewPagerAdapter(int iconResId, int titleArrayResId, int hintArrayResId) {

            this.iconResId = iconResId;
            this.titleArrayResId = titleArrayResId;
            this.hintArrayResId = hintArrayResId;
        }

        @Override
        public int getCount() {
            return getResources().getIntArray(iconResId).length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            Drawable icon = getResources().obtainTypedArray(iconResId).getDrawable(position);
            String title = getResources().getStringArray(titleArrayResId)[position];
            String hint = getResources().getStringArray(hintArrayResId)[position];


            View itemView = getLayoutInflater().inflate(R.layout.viewpager_item, container, false);


            ImageView iconView = (ImageView) itemView.findViewById(R.id.landing_img_slide);
            TextView titleView = (TextView)itemView.findViewById(R.id.landing_txt_title);
            TextView hintView = (TextView)itemView.findViewById(R.id.landing_txt_hint);
            TextView getSartedView = (TextView)itemView.findViewById(R.id.getStartedTV);

            int count = getCount()-1;
            //ECommerceApp.Utility.logThis(TAG,"Count = "+count+" pos = "+position);


            iconView.setImageDrawable(icon);
            titleView.setText(title);
            hintView.setText(hint);
            if(position==count){
                getSartedView.setVisibility(View.VISIBLE);
            }else {
                getSartedView.setVisibility(View.GONE);
            }
            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);

        }
    }


    public class CustomPageTransformer implements ViewPager.PageTransformer {


        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            View imageView = view.findViewById(R.id.landing_img_slide);
            View contentView = view.findViewById(R.id.landing_txt_hint);
            View txt_title = view.findViewById(R.id.landing_txt_title);
            View getStarted = view.findViewById(R.id.getStartedTV);

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left
            } else if (position <= 0) { // [-1,0]
                // This page is moving out to the left

                // Counteract the default swipe
                setTranslationX(view,pageWidth * -position);
                if (contentView != null) {
                    // But swipe the contentView
                    setTranslationX(contentView,pageWidth * position);
                    setTranslationX(txt_title,pageWidth * position);
                    setTranslationX(getStarted,pageWidth * position);
                    setAlpha(contentView,1 + position);
                    setAlpha(txt_title,1 + position);
                    setAlpha(getStarted,1+position);
                }

                if (imageView != null) {
                    // Fade the image in
                    setAlpha(imageView,1 + position);
                }

            } else if (position <= 1) { // (0,1]
                // This page is moving in from the right

                // Counteract the default swipe
                setTranslationX(view, pageWidth * -position);
                if (contentView != null) {
                    // But swipe the contentView
                    setTranslationX(contentView,pageWidth * position);
                    setTranslationX(txt_title,pageWidth * position);
                    setTranslationX(getStarted,pageWidth * position);

                    setAlpha(contentView, 1 - position);
                    setAlpha(txt_title, 1 - position);
                    setAlpha(getStarted,1 - position);

                }
                if (imageView != null) {
                    // Fade the image out
                    setAlpha(imageView,1 - position);
                }

            }
        }
    }

    private void setAlpha(View view, float alpha) {


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && ! isSliderAnimation) {
            view.setAlpha(alpha);
        }
    }

    /**
     * Sets the translationX for the view. The translation value will be applied only if the running android device OS is greater than honeycomb.
     * @param view - view to which alpha to be applied.
     * @param translationX - translationX value.
     */
    private void setTranslationX(View view, float translationX) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && ! isSliderAnimation) {
            view.setTranslationX(translationX);
        }
    }

    public void onSaveInstanceState(Bundle outstate) {

        if(outstate != null) {
            outstate.putBoolean(SAVING_STATE_SLIDER_ANIMATION, isSliderAnimation);
        }

        super.onSaveInstanceState(outstate);
    }

    public void onRestoreInstanceState(Bundle inState) {

        if(inState != null) {
            isSliderAnimation = inState.getBoolean(SAVING_STATE_SLIDER_ANIMATION,false);
        }
        super.onRestoreInstanceState(inState);

    }
    public void getStarted(View v){
        finish();
        /*Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("lat", Double.parseDouble(Utility.readString(this,"lat")));
        intent.putExtra("lon", Double.parseDouble(Utility.readString(this,"lon")));
        startActivity(intent);*/
        startActivity(new Intent(this,Login.class));
    }


    public class ColorShades {

        private int mFromColor;
        private int mToColor;
        private float mShade;

        public ColorShades setFromColor(int fromColor) {
            this.mFromColor = fromColor;
            return this;
        }

        public ColorShades setToColor(int toColor) {
            this.mToColor = toColor;
            return this;
        }



        public ColorShades setFromColor(String fromColor) {

            this.mFromColor = Color.parseColor(fromColor);
            return this;
        }

        public ColorShades setToColor(String toColor) {
            this.mToColor = Color.parseColor(toColor);
            return this;
        }

        public ColorShades forLightShade(int color) {
            setFromColor(Color.WHITE);
            setToColor(color);
            return this;
        }

        public ColorShades forDarkShare(int color) {
            setFromColor(color);
            setToColor(Color.BLACK);
            return this;
        }

        public ColorShades setShade(float mShade) {
            this.mShade = mShade;
            return this;
        }


        /**
         * Generates the shade for the given color.
         * @return the int value of the shade.
         */
        public int generate() {

            int fromR = (Color.red(mFromColor));
            int fromG = (Color.green(mFromColor));
            int fromB = (Color.blue(mFromColor));

            int toR = (Color.red(mToColor));
            int toG = (Color.green(mToColor));
            int toB = (Color.blue(mToColor));

            int diffR = toR - fromR;
            int diffG = toG - fromG;
            int diffB = toB - fromB;



            int R = fromR + (int) (( diffR * mShade));
            int G = fromG + (int) (( diffG * mShade));
            int B = fromB + (int) (( diffB * mShade));

            return  Color.rgb(R, G, B);

        }


        /**
         * Assumes the from and to color are inverted before generating the shade.
         * @return the int value of the inverted shade.
         */
        public int generateInverted() {

            int fromR = (Color.red(mFromColor));
            int fromG = (Color.green(mFromColor));
            int fromB = (Color.blue(mFromColor));

            int toR = (Color.red(mToColor));
            int toG = (Color.green(mToColor));
            int toB = (Color.blue(mToColor));


            int diffR = toR - fromR;
            int diffG = toG - fromG;
            int diffB = toB - fromB;

            int R = toR - (int) (( diffR * mShade));
            int G = toG - (int) (( diffG * mShade));
            int B = toB - (int) (( diffB * mShade));

            return  Color.rgb(R, G, B);

        }

        /**
         * Gets the String equivalent of the generated shade
         * @return String value of the shade
         */
        public String generateInvertedString() {
            return String.format("#%06X", 0xFFFFFF & generateInverted());
        }

        /**
         * Gets the inverted String equivalent of the generated shade
         * @return String value of the shade
         */
        public String generateString() {
            return String.format("#%06X", 0xFFFFFF & generate());
        }

    }


}
