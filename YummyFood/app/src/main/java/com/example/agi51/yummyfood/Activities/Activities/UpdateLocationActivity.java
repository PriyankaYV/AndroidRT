package com.example.agi51.yummyfood.Activities.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agi51.yummyfood.R;


/**
 * Created by agi51 on 24/2/16.
 */
public class UpdateLocationActivity extends AppCompatActivity {


    private String city,area;
    private TextView textView,textV;
    private Button updateLocationInMain;
    private ImageView imageView;
   // private Animation locationindicator;
    private SharedPreferences sharedPreferences;
    private String blankdata;
    private ProgressDialog dialog;
    private RecyclerView recyclerViewcity;
    private CoordinatorLayout coordinatorLayout;
    private String updatedCity,updatedArea;
    private String Select;
    String selected;
    String newArea;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_location_main);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator);
        textView = (TextView)findViewById(R.id.textView);
        textV = (TextView)findViewById(R.id.textV);
        imageView =(ImageView)findViewById(R.id.imageVi);
       // locationindicator = AnimationUtils.loadAnimation(this,R.anim.locationindicator);
        recyclerViewcity = (RecyclerView)findViewById(R.id.viewOfCity);
       // imageView.startAnimation(locationindicator);
        updateLocationInMain = (Button)findViewById(R.id.updateLocationInMain);
        blankdata="blank";
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateLocationActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out_in);
            }
        });

        sharedPreferences = getSharedPreferences("FoodYummy",MODE_PRIVATE);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            city = extras.getString("city");
            area = extras.getString("place");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("select city", city);
            editor.putString("select area",area);
            editor.commit();
        }
        city = sharedPreferences.getString("select city", "");
        area = sharedPreferences.getString("select area", "");

        updatedCity = getIntent().getStringExtra("updated city");
        updatedArea = getIntent().getStringExtra("updated area");


        Select = "Select";


        textV.setText(city);


        if(updatedCity!=null){
            textView.setText(updatedCity);
            textV.setText(Select);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("select city", textV.getText().toString());
            editor.putString("select area", textView.getText().toString());
            editor.commit();
        }
        if(updatedCity==null){
            textView.setText(area);
            textV.setText(city);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("select city",textV.getText().toString());
            editor.putString("select area",textView.getText().toString());
            editor.commit();
        }

        if(updatedArea!=null){
            selected = getIntent().getStringExtra("selected city");
            textView.setText(selected);
            textV.setText(updatedArea);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("updated city",textV.getText().toString());
            editor.putString("updated area",textView.getText().toString());
            editor.commit();
        }



       /*if(updatedArea==textView.getText().toString()){
            textView.setText(updatedCity);
            textV.setText(newArea);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("updated city",textV.getText().toString());
            editor.putString("updated area",textView.getText().toString());
            editor.commit();
        }
*/
        updateLocationInMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textV.getText().toString().equals("Select")) {
                    SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                    snackbarText.append("  ");
                    int boldStart = snackbarText.length();
                    snackbarText.append("Please select your area from the list");
                    snackbarText.setSpan(new ForegroundColorSpan(0xFFa8d324), boldStart, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, snackbarText, Snackbar.LENGTH_LONG);
                    View snackbar_view = snackbar.getView();
                    TextView snackbar_text = (TextView)snackbar_view.findViewById(android.support.design.R.id.snackbar_text);
                    snackbar_text.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.logofinal,0,0,0);

                    snackbar.show();
                }else {

                    Intent intent = new Intent(UpdateLocationActivity.this, MainActivity.class);
                    intent.putExtra("updated city", textView.getText().toString());
                    intent.putExtra("updated area", textV.getText().toString());
                    startActivity(intent);
                }
            }

        });

    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.show();
    }

    public void selectCity(View view){

        Intent intent = new Intent(UpdateLocationActivity.this, SelectCityActivity.class);
        startActivity(intent);
    }


    public void selectArea(View view){

        Intent intent1 = new Intent(UpdateLocationActivity.this,SelectAreaActivity.class);
        newArea = textView.getText().toString();
        intent1.putExtra("selected city", newArea);
        startActivity(intent1);

    }


    /* public void gotoMain(View v){
        Intent intent = new Intent(UpdateLocationActivity.this,MainActivity.class);
        startActivity(intent);
    }*/
}
