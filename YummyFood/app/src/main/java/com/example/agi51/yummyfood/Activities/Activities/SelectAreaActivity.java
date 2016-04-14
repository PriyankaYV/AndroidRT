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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.agi51.yummyfood.Activities.Adapters.AreaAdapter;
import com.example.agi51.yummyfood.Activities.Beans.AreaBean;
import com.example.agi51.yummyfood.Activities.Beans.LocationBean;
import com.example.agi51.yummyfood.Activities.utilities.Constants;
import com.example.agi51.yummyfood.Activities.utilities.MyVolley;
import com.example.agi51.yummyfood.Activities.utilities.RecyclerItemClickSupport;
import com.example.agi51.yummyfood.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agi51 on 2/3/16.
 */
public class SelectAreaActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    String citySelected;
    RecyclerView recyclerViewArea;
    List<AreaBean> beanArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);
        citySelected = getIntent().getStringExtra("selected city");
        recyclerViewArea = (RecyclerView)findViewById(R.id.viewOfArea);
        recyclerViewArea.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerViewArea.setLayoutManager(manager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAreaActivity.this, UpdateLocationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out_in);
            }
        });

        RecyclerItemClickSupport.addTo(recyclerViewArea).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Intent intent = new Intent(SelectAreaActivity.this, UpdateLocationActivity.class);
                intent.putExtra("selected city",citySelected);
                intent.putExtra("updated area", beanArea.get(position).getSubLocality().toString());
                startActivity(intent);
               // Toast.makeText(SelectAreaActivity.this,"bean on click"+beanArea.get(position).getSubLocality().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        searchAreas();

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

    public void searchAreas() {
        showProgressDialog();

        LocationBean beans = new LocationBean();
        beans.setLocationCity(citySelected);

        Gson gson = new Gson();
        String jsonString = gson.toJson(beans);
        String urlArea = Constants.WORKING_SUBAREA_URL+"?datastring="+jsonString;

       // Toast.makeText(this, urlArea, Toast.LENGTH_LONG).show();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, urlArea, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {

                    //Toast.makeText(SelectAreaActivity.this, "sucess" + response.toString(), Toast.LENGTH_LONG).show();
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

        beanArea = new ArrayList<>();

        for(int i=0;i<response.length();i++ ){
            AreaBean areabean = new AreaBean();

            try {
                JSONObject obj = (JSONObject) response.get(i);

                String jsonString = obj.toString();
                areabean = gson.fromJson(jsonString,AreaBean.class);

                beanArea.add(areabean);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        AreaAdapter areaadapter = new AreaAdapter(beanArea);

        recyclerViewArea.setAdapter(areaadapter);
    }




}
