package com.example.agi51.tryemcomerce.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

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
 * Created by agi51 on 10/2/16.
 */
public class Login extends Activity {

    private static final String TAG ="Login" ;
    EditText UserName, Paswrd;
    String[] roles;
    private String role=null;
    private int type_id;
    private AlertDialog dialog;
    String username, paswrdd;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        UserName = (EditText)findViewById(R.id.UserName);
        Paswrd = (EditText)findViewById(R.id.Paswrd);
        roles = new String[]{"User","Vendor","Admin"};
    }

    public void openRegister(View view){
        startActivity(new Intent(Login.this,RegisterActivity.class).putExtra(Constants.ROLE,"Users"));
    }

    public void startLogin(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogueCustom));
        builder.setTitle("How do u want to login..?");
        builder.setSingleChoiceItems(roles, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        role = Constants.USER;
                        type_id = Constants.USER_TYPE_ID;
                        break;

                    case 1:
                        role = Constants.VENDOR;
                        type_id = Constants.VENDOR_TYPE_ID;
                        break;

                    case 2:
                        role = Constants.ADMIN;
                        type_id = Constants.ADMIN_TYPE_ID;
                        break;
                }
                dialog.dismiss();
                doLogin(role);

            }
        });
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    private void doLogin(String role){

        username = UserName.getText().toString();
        paswrdd = Paswrd.getText().toString();
        if(validInputs()){

            progressDialog = new ProgressDialog(Login.this,R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Authenicating..");
            progressDialog.show();
            RegisterBean bean = new RegisterBean();
            bean.setUsername(username);
            bean.setPassword(paswrdd);
            bean.setType_id(type_id);
            Gson gson = new Gson();
            String jsonString = gson.toJson(bean);
            String url = Constants.LOGIN_BASE_URL+"?datastring="+jsonString;
            Utility.logThis(TAG, url);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getInt(Constants.RESPONSE_FLAG) == (Constants.SUCCESS_RESPONSE_FLAG)) {
                            startActivity(new Intent(Login.this,MainActivity.class));
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(Login.this,R.style.AlertDialogueCustom));
                            builder.setTitle("Login failed");
                            builder.setMessage(response.getString("message"))
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setIcon(R.drawable.warning);
                            AlertDialog dialog = builder.create();
                            dialog.show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    progressDialog.dismiss();

                }
            });
            RequestQueue queue = MyVolley.getRequestQueue();
            request.setShouldCache(false);
            request.setRetryPolicy(Utility.getRetryPolicy());
            if(Utility.checkInternet(this)){
                queue.add(request);
            }else{
                Utility.getAlertDialogue(this,null,"please on the internet and try",null,"OK").show();
            }

        }

    }
    private boolean validInputs(){
        boolean falg = true;
        UserName.setError(null);
        Paswrd.setError(null);
        if(TextUtils.isEmpty(username)){
            falg= false;
            UserName.setError("Enter a valid username");
            UserName.requestFocus();
        }
        if(TextUtils.isEmpty(paswrdd)){
            falg = false;
            Paswrd.setError("enter pasword");
            Paswrd.requestFocus();
        }
        return falg;
    }

    private void onLoginSuccess() {

        startActivity(new Intent(this,MainActivity.class));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



}
