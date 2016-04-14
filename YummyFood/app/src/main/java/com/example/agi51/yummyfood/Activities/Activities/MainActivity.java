package com.example.agi51.yummyfood.Activities.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.agi51.yummyfood.Activities.Adapters.RecyclerAdapter;
import com.example.agi51.yummyfood.Activities.Beans.DataBean;
import com.example.agi51.yummyfood.Activities.utilities.Constants;
import com.example.agi51.yummyfood.Activities.utilities.MyVolley;
import com.example.agi51.yummyfood.Activities.utilities.RecyclerItemClickSupport;
import com.example.agi51.yummyfood.Activities.utilities.Utility;
import com.example.agi51.yummyfood.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by agi51 on 22/2/16.
 */
public class MainActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "MainActivity";
    private ImageView imchanging;
    private int[] imageArray = { R.drawable.onn, R.drawable.newbi,
            R.drawable.newbin, R.drawable.newbinh};
    private GoogleApiClient mGoogleApiClient;
    private String place,city;
    private TextView idcaptionTitle;
    private Location mLastLocation;
    private SharedPreferences prefs;
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    private TextView mTitle;
    DrawerLayout drawer;
    List<DataBean> beanList;

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
  //  TextView tv1,tv2;
    private Double lat,lon;
    private TextView toolbartitle;
    String gmailName;
    String updatedCity,updatedArea;
    private String upCity;
    // SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclr);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        if (!Utility.checkInternet(MainActivity.this)) {

            Intent intent = new Intent(MainActivity.this,ConnectionLost.class);
            startActivity(intent);
            finish();

        }



   /* private void processResponse(JSONObject response) {
        *//**
         * Here process the response , the response is a JSONArray
         * loop over the json array and get the data out of the array
         *//*
        if(dialog.isShowing()){
            dialog.dismiss();
        }
       //Create Gson object here
        Gson gson = new Gson();

        //Type beanList = new TypeToken<Map<String, DataBean>>() {}.getType();*/
        /*List<DataBean> beanList = new ArrayList<>();


       RecyclerAdapter adapter = new RecyclerAdapter(beanList);
       recyclerView.setAdapter(adapter);*/


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        //setSupportActionBar(toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);


       /* tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);


*/
        Intent foname = getIntent();


        String names = foname.getStringExtra("names");

        prefs = getSharedPreferences("FoodyYummy", MODE_PRIVATE);
        if(prefs.getString("name",null)!=null){
            names = prefs.getString("name","");
            gmailName = names;

        }else{
            names="Guest";
        }

       // idcaptionTitle.setText(names);


        toolbartitle = (TextView)findViewById(R.id.toolbar_title);


       ImageView  im = (ImageView )toolbar.findViewById(R.id.loc);

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                snackbarText.append("  ");
                int boldStart = snackbarText.length();
                snackbarText.append("Your preferred location has been set to ur current Location");
                snackbarText.setSpan(new ForegroundColorSpan(0xFFa8d324), boldStart, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Snackbar snack = Snackbar.make(v,snackbarText , Snackbar.LENGTH_LONG);
                View snackbar_view = snack.getView();
                TextView snackbar_text = (TextView)snackbar_view.findViewById(android.support.design.R.id.snackbar_text);
                snackbar_text.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.logofinal,0,0,0);

                snack.show();
            }
        });

        //changing images
        imchanging = (ImageView)findViewById(R.id.imchanging);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {
                imchanging.setImageResource(imageArray[i]);
                i++;
                if (i > imageArray.length - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 4000);
            }
        };
        handler.postDelayed(runnable, 2000);

/*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View header= LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        TextView tv = (TextView) header.findViewById(R.id.ghest);

        tv.setText("Hello "+names);
        navigationView.addHeaderView(header);
        navigationView.setNavigationItemSelectedListener(this);
        initView();

        idcaptionTitle = (TextView)findViewById(R.id.captionTitleView);
        idcaptionTitle.setText("Hello " + names);


            //idcaptionTitle.setText("Hello "+names);

        final NavigationView navLeft = (NavigationView)findViewById(R.id.nav_left);
        ImageView imFilter = (ImageView)toolbar.findViewById(R.id.filter);
        imFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(navLeft);

            }
        });

        updatedCity = getIntent().getStringExtra("updated city");
        updatedArea = getIntent().getStringExtra("updated area");

/*

        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);


        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("updated city",updatedCity);
        editor.putString("updated area",updatedArea);
        editor.commit();

*/

/*
        updatedCity = prefs.getString("updated city", "");
        updatedArea= prefs.getString("updated area", "");*/

        RecyclerItemClickSupport.addTo(recyclerView).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {


            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {



                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("rest_name", beanList.get(position).getRest_name().toString());
                intent.putExtra("rest_rating", beanList.get(position).getRest_rating().toString());
                intent.putExtra("logo", beanList.get(position).getLogo().toString());

                startActivity(intent);


                //Toast.makeText(MainActivity.this,"bean on click"+beanList.get(position).getRest_name(), Toast.LENGTH_SHORT).show();
            }
        });

       // title = sharedpreferences.getString("title","");



    }






    @Override
    public void onConnected(Bundle bundle) {


        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            lat= mLastLocation.getLatitude();
            lon =mLastLocation.getLongitude();

            Geocoder gcd = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = gcd.getFromLocation(lat, lon, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses.size() > 0) {
                place = addresses.get(0).getLocality();
                city = addresses.get(0).getSubLocality();
           /* tv1.setText(place);
            tv2.setText(city);*/
            }
            if (updatedCity == null) {
                Toast.makeText(this,"here",Toast.LENGTH_SHORT).show();
                toolbartitle.setText(place + "/" + city);
                passToDatabase(place, city);
            } /*else{

            }*/

        }


        if(updatedCity != null) {
            passToDatabase(updatedCity, updatedArea);
            //toolbartitle.setText(title);
            toolbartitle.setText(updatedCity + "/" + updatedArea);
            //passToDatabase(updatedCity,updatedArea);
        }

        mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = toolbartitle.getText().toString();
                Log.d(TAG, "String " + str.split("/")[0]);
                Intent intent = new Intent(MainActivity.this, UpdateLocationActivity.class);
                intent.putExtra("place", str.split("/")[0]);
                intent.putExtra("city", str.split("/")[1]);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out_in);
            }
        });

    }



    private void passToDatabase(String place, String city) {
        showProgressDialog();

        DataBean bean = new DataBean();
        bean.setCity(place);
        bean.setArea(city);
        if(gmailName!=null){
            bean.setGmailName(gmailName);
        }

        Gson gson = new Gson();
        String jsonString = gson.toJson(bean);

        String urlgot = Constants.LOGIN_BASE_URL+"?datastring="+jsonString;
        Log.d(TAG,urlgot);

       //Toast.makeText(this,urlgot,Toast.LENGTH_LONG).show();


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, urlgot, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {

                   // Toast.makeText(MainActivity.this, "sucess" + response.toString(), Toast.LENGTH_LONG).show();
                    Log.d("response", response.toString());
                    processResponse(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //Toast.makeText(MainActivity.this, "inside error listener", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = MyVolley.getRequestQueue();
        queue.add(request);

    }

    private void processResponse(JSONArray response) {

        if(dialog.isShowing()){
            dialog.dismiss();
        }

        Gson gson = new Gson();
        //Type beanList = new TypeToken<Map<String, DataBean>>() {}.getType();
        beanList = new ArrayList<>();

        for(int i=0;i<response.length();i++ ){
            DataBean bean = new DataBean();

            try {
                JSONObject obj = (JSONObject) response.get(i);
                // getting the databean object by giving the json string
                // to gson object with the Class of Data
                String jsonString = obj.toString();
                bean = gson.fromJson(jsonString,DataBean.class);
                //add databean to the list
                beanList.add(bean);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //initialize adapter with the beanlist
        //and set the adapter to the recycler view
        RecyclerAdapter adapter = new RecyclerAdapter(beanList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setIcon(R.drawable.logofinal);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.show();
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    private void initView() {

    }

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_home) {
            // Handle the camera action
        } else if (id == R.id.notification) {

        } else if (id == R.id.sign_out) {


            Intent intent = new Intent(this, SignInActivity.class);

            startActivity(intent);

        } else if (id == R.id.contact_us) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {

            SpannableStringBuilder snackbarText = new SpannableStringBuilder();
            snackbarText.append("  ");
            int boldStart = snackbarText.length();
            snackbarText.append("Pressing back again will exit the app");
            snackbarText.setSpan(new ForegroundColorSpan(0xFFa8d324), boldStart, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Snackbar snackbar = Snackbar
                    .make(drawer, snackbarText, Snackbar.LENGTH_LONG);
            View snackbar_view = snackbar.getView();
            TextView snackbar_text = (TextView)snackbar_view.findViewById(android.support.design.R.id.snackbar_text);
            snackbar_text.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.logofinal,0,0,0);

            snackbar.show();
            /*Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();*/
        }
        back_pressed = System.currentTimeMillis();
    }

}
