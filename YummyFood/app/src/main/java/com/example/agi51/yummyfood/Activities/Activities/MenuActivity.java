package com.example.agi51.yummyfood.Activities.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.example.agi51.yummyfood.Activities.Adapters.MenuAdapter;
import com.example.agi51.yummyfood.Activities.Beans.RestuarantBean;
import com.example.agi51.yummyfood.Activities.utilities.Constants;
import com.example.agi51.yummyfood.Activities.utilities.MyVolley;
import com.example.agi51.yummyfood.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agi51 on 11/3/16.
 */
public class MenuActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView, textrate;
    private String rest_name, rest_rate, rest_logo;
    private ProgressDialog dialog;
    List<RestuarantBean> beanRestuarant;
    RecyclerView recyclerViewMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        imageView = (ImageView)findViewById(R.id.restim);
        textView = (TextView)findViewById(R.id.restname);
        textrate = (TextView)findViewById(R.id.restrate);
        recyclerViewMenu = (RecyclerView)findViewById(R.id.viewOfMenu);
        recyclerViewMenu.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerViewMenu.setLayoutManager(manager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out_in);
            }
        });




        rest_name = getIntent().getStringExtra("rest_name");
        rest_rate = getIntent().getStringExtra("rest_rating");
        rest_logo = getIntent().getStringExtra("logo");

        textView.setText(rest_name);
        textrate.setText(rest_rate);
        Glide.with(imageView.getContext()).load(rest_logo).into(imageView);

        listMenus();
    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setIcon(R.mipmap.logofinal);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.show();
    }


    public void listMenus() {
        showProgressDialog();

        RestuarantBean beans = new RestuarantBean();
        beans.setRestuarntName(rest_name);

        Gson gson = new Gson();
        String jsonString = gson.toJson(beans);
        String urlArea = Constants.WORKING_MENUS_URL+"?datastring="+jsonString;

        Log.d("urlMenu",urlArea);

        Toast.makeText(this, urlArea, Toast.LENGTH_LONG).show();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, urlArea, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                try {

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
                // Toast.makeText(SelectAreaActivity.this, "inside error listener", Toast.LENGTH_SHORT).show();
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

        beanRestuarant = new ArrayList<>();

        for(int i=0;i<response.length();i++ ){
            RestuarantBean menubean = new RestuarantBean();

            try {
                JSONObject obj = (JSONObject) response.get(i);

                String jsonString = obj.toString();
                menubean = gson.fromJson(jsonString,RestuarantBean.class);

                beanRestuarant.add(menubean);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        MenuAdapter menuadapter = new MenuAdapter(beanRestuarant);

        recyclerViewMenu.setAdapter(menuadapter);
    }

}
