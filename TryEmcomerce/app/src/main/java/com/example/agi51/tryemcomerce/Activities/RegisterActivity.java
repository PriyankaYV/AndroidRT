package com.example.agi51.tryemcomerce.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.agi51.tryemcomerce.Beans.RegisterBean;
import com.example.agi51.tryemcomerce.R;
import com.example.agi51.tryemcomerce.Utilities.Constants;
import com.example.agi51.tryemcomerce.Utilities.MyVolley;
import com.example.agi51.tryemcomerce.Utilities.Utility;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by agi51 on 11/2/16.
 */
public class RegisterActivity extends Activity {
    private static final String TAG = "RegisterActivity";
    private String role;
    EditText userName,email,mobile,password,repaswrd;
    String usrname,emil,mobil,paswrd,rpaswrd;
    private ProgressDialog progressDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        Intent intent =getIntent();
        role = intent.getStringExtra(Constants.ROLE);
        initView();
    }

    private void initView() {
        userName =(EditText)findViewById(R.id.tvUsername);
        email =(EditText)findViewById(R.id.idEmail);
        mobile =(EditText)findViewById(R.id.idMobile);
        password =(EditText)findViewById(R.id.idPaswrd);
        repaswrd =(EditText)findViewById(R.id.idRepaswrd);
    }

    public void startRegistration(View v) {

        Log.d(TAG, "start registration");
        usrname = userName.getText().toString();
        emil = email.getText().toString();
        mobil = mobile.getText().toString();
        paswrd = password.getText().toString();
        rpaswrd = repaswrd.getText().toString();

        if (validateInputss()) {
            progressDialogue = new ProgressDialog(RegisterActivity.this, R.style.AppTheme_Dark_Dialog);
            progressDialogue.setIndeterminate(true);
            progressDialogue.setMessage("Creating Account");
            progressDialogue.setCancelable(true);
            progressDialogue.show();

            RegisterBean bean = new RegisterBean();
            bean.setUsername(usrname);
            bean.setEmail(emil);
            bean.setMobile(mobil);
            bean.setPassword(paswrd);
            bean.setType_id(Constants.USER_TYPE_ID);
            Gson gson = new Gson();
            //Toast.makeText(this,"inside gson",Toast.LENGTH_SHORT).show();

            String jsonString = gson.toJson(bean);
            String url = Constants.REGISTER_BASE_URL+"?datastring="+jsonString;
            Utility.logThis(TAG, url);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Utility.logThis(TAG, "Response from server = " + response.toString());
                    progressDialogue.dismiss();
                    try {
                        if (response.getInt(Constants.RESPONSE_FLAG) == Constants.SUCCESS_RESPONSE_FLAG) {
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    progressDialogue.dismiss();
                }
            });
            RequestQueue queue = MyVolley.getRequestQueue();
            request.setShouldCache(false);
            request.setRetryPolicy(Utility.getRetryPolicy());
            if (Utility.checkInternet(this)) {
                queue.add(request);
            } else {
                Utility.getAlertDialogue(this, null, "Please turn On the internet and retry ", null, "OK").show();
            }
        }
    }
    private boolean validateInputss() {
        boolean flag = true;
        userName.setError(null);
        password.setError(null);
        email.setError(null);
        repaswrd.setError(null);
        password.setError(null);
        if(TextUtils.isEmpty(usrname)){
            userName.setError("please enter an username");
            userName.requestFocus();
            flag = false;
        }
        if(TextUtils.isEmpty(emil)){
            email.setError("Enter email");
            email.requestFocus();
            flag = false;
        }else if(!Utility.validateEmail(emil)){
            email.setError("Invalid email");
            email.requestFocus();
            flag = false;
        }
        if(TextUtils.isEmpty(mobil)&&Utility.validatePhone(mobil)) {
            mobile.setError("Invalid mobile number");
            mobile.requestFocus();
        }
        if(TextUtils.isEmpty(paswrd)){
            password.setError("Invalid password");
            password.requestFocus();
            flag = false;
        }
        if(TextUtils.isEmpty(rpaswrd)){
            repaswrd.setError("Invalid password");
            repaswrd.requestFocus();
            flag = false;
        }else{
            if(!paswrd.equals(rpaswrd)){
                repaswrd.setError("passwords do not match");
                flag = false;
            }
        }
        return flag;
    }

    private void registerSuccess() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        },3000);
    }

    public void openLogin(View view){
        startActivity(new Intent(this,Login.class));
    }
}

