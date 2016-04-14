package com.example.agi51.hmkcloud;

/**
 * Created by agi51 on 5/2/16.
 */
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.agi51.hmkcloud.GcmMessageHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainRecycler extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    List<DataBean> beanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main);;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        RecyclerAdapter adapter = new RecyclerAdapter(beanList);
        recyclerView.setAdapter(adapter);



    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.show();
    }


    public void fetchData(View v) {
        showProgressDialog();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,"", new Response.Listener<JSONArray>() {
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
        /**
         * Get the request queue and add the request to the queue
         * add Internet permission in android manifest.
         */
        RequestQueue queue = MyVolley.getRequestQueue();
        queue.add(request);
    }

    private void processResponse(JSONArray response) {
        /**
         * Here process the response , the response is a JSONArray
         * loop over the json array and get the data out of the array
         */
        if(dialog.isShowing()){
            dialog.dismiss();
        }
        //Create Gson object here
        Gson gson = new Gson();
        //Type beanList = new TypeToken<Map<String, DataBean>>() {}.getType();


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


    }
}
