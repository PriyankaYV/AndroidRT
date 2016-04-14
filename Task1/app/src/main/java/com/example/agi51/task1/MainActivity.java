package com.example.agi51.task1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }
    public void fetchData(View v) {
        showProgressDialog();

        JsonArrayRequest request = new JsonArrayRequest( Method.GET,"http://jsonplaceholder.typicode.com/posts", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG,response.toString());
                    processResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue queue = MyVolley.getRequestQueue();
        queue.add(request);
    }

    private void processResponse(JSONArray response) {

        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        Gson gson = new Gson();
        List<DataBean> beanlist = new ArrayList<>();
        for(int i =0;i<response.length();i++){
            DataBean bean = new DataBean();

            try {
                JSONObject jsonObject = (JSONObject)response.get(i);
                String jsonString = jsonObject.toString();
                bean = gson.fromJson(jsonString,DataBean.class);
                beanlist.add(bean);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        RecyclerAdapter adapter = new RecyclerAdapter(beanlist);
        recyclerView.setAdapter(adapter);



    }


}

