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
import com.example.agi51.yummyfood.Activities.Adapters.CityAdapter;
import com.example.agi51.yummyfood.Activities.Beans.DataBean;
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
public class SelectCityActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    String blankdata;
    RecyclerView recyclerViewcity;
    //TextView done;
    List<LocationBean> beanLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        blankdata = "blank";
        recyclerViewcity = (RecyclerView)findViewById(R.id.viewOfCity);
        //done = (TextView)findViewById(R.id.done);
        recyclerViewcity.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerViewcity.setLayoutManager(manager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

       /* done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectCityActivity.this, UpdateLocationActivity.class);
                startActivity(intent);
            }
        });
*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectCityActivity.this, UpdateLocationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out_in);
            }
        });

        RecyclerItemClickSupport.addTo(recyclerViewcity).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Intent intent = new Intent(SelectCityActivity.this, UpdateLocationActivity.class);
                intent.putExtra("updated city", beanLoc.get(position).getLocationCity().toString());
                startActivity(intent);
                //Toast.makeText(SelectCityActivity.this,"bean on click"+beanLoc.get(position).getLocationCity().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        searchCities();

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

    public void searchCities() {
        showProgressDialog();

        DataBean bean = new DataBean();
        bean.setRegion(blankdata);

        Gson gson = new Gson();
        String jsonString = gson.toJson(bean);
        String urlCity = Constants.WORKING_AREA_URL+"?datastring="+jsonString;

       // Toast.makeText(this,urlCity,Toast.LENGTH_LONG).show();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, urlCity, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {

                    //Toast.makeText(SelectCityActivity.this, "sucess" + response.toString(), Toast.LENGTH_LONG).show();
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
                //Toast.makeText(SelectCityActivity.this, "inside error listener", Toast.LENGTH_SHORT).show();
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
        beanLoc = new ArrayList<>();

        for(int i=0;i<response.length();i++ ){
            LocationBean locbean = new LocationBean();

            try {
                JSONObject obj = (JSONObject) response.get(i);
                // getting the databean object by giving the json string
                // to gson object with the Class of Data
                String jsonString = obj.toString();
                locbean = gson.fromJson(jsonString,LocationBean.class);
                //add databean to the list
                beanLoc.add(locbean);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //initialize adapter with the beanlist
        //and set the adapter to the recycler view
        CityAdapter cityadapter = new CityAdapter(beanLoc);

        recyclerViewcity.setAdapter(cityadapter);
    }



}
