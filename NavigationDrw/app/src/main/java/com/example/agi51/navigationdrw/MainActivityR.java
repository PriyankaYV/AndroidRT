package com.example.agi51.navigationdrw;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by agi51 on 6/2/16.
 */
public class MainActivityR extends Activity {

    RecyclerView rv;
    private List beanList;
    private RecylAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainr);
        rv = (RecyclerView)findViewById(R.id.rv1);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        beanList = new ArrayList();
        adapter = new RecylAdapter(beanList);
        rv.setAdapter(adapter);

    }

    public void fectch(View v){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "http://jsonplaceholder.typicode.com/posts", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Gson gson = new Gson();


                for(int i=0;i<response.length();i++ ){
                    Beam bean = new Beam();

                    try {
                        JSONObject obj = (JSONObject) response.get(i);
                        String jsonString = obj.toString();
                        bean = gson.fromJson(jsonString, Beam.class);
                        beanList.add(bean);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();

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
}
