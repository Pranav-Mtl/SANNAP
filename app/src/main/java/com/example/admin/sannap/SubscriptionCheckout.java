package com.example.admin.sannap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.sannap.BE.HomeScreenBE;
import com.example.admin.sannap.BL.HomeScreenBL;
import com.example.admin.sannap.BL.SubscriptionCheckoutBL;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SubscriptionCheckout extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etEmail,etPhone,etAddress,etCity,etZip;
    TextView tvPackage,tvPackagePrice;

    Button btnDone;

    String modelID,modelName,modelPrice;

    String strPhone,strAddress,strCity,strZip;

    HomeScreenBL objHomeScreenBL;
    HomeScreenBE objHomeScreenBE;

    ProgressDialog progressDialog;

    SubscriptionCheckoutBL objSubscriptionCheckoutBL;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_checkout);

        initialize();

        if(Util.isInternetConnection(SubscriptionCheckout.this))
        new GetData().execute(userID);
    }


    private void initialize(){

        etName= (EditText) findViewById(R.id.checkout_name);
        etEmail= (EditText) findViewById(R.id.checkout_email);
        etPhone= (EditText) findViewById(R.id.checkout_phone);
        etAddress= (EditText) findViewById(R.id.checkout_address);
        etCity= (EditText) findViewById(R.id.checkout_city);
        etZip= (EditText) findViewById(R.id.checkout_zip);
        btnDone= (Button) findViewById(R.id.checkout_done);

        btnDone.setOnClickListener(this);

        tvPackage= (TextView) findViewById(R.id.checkout_package);
        tvPackagePrice= (TextView) findViewById(R.id.checkout_price);

        objHomeScreenBE=new HomeScreenBE();
        objHomeScreenBL=new HomeScreenBL();
        objSubscriptionCheckoutBL=new SubscriptionCheckoutBL();
        progressDialog=new ProgressDialog(SubscriptionCheckout.this);

        modelID=getIntent().getStringExtra("Model");
        modelName=getIntent().getStringExtra("ModelName");
        modelPrice=getIntent().getStringExtra("ModelPrice");

        tvPackage.setText("Name: "+modelName);
        tvPackage.setText("Price: ₹"+modelPrice);

        userID= Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_LOGIN_ID);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {


            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkout_done:
                if(validate()){
                    new Subscribe().execute(strPhone,strAddress,strCity,strZip,userID,modelID);
                }
                break;
        }
    }


    private class  GetData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objHomeScreenBL.getData(params[0],objHomeScreenBE);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try{

                etName.setText(objHomeScreenBE.getName());
                etEmail.setText(objHomeScreenBE.getEmail());
                etPhone.setText(objHomeScreenBE.getPhone());
                etAddress.setText(objHomeScreenBE.getAddress());
                etCity.setText(objHomeScreenBE.getCity());
                etZip.setText(objHomeScreenBE.getZip());

                etName.setEnabled(false);
                etEmail.setEnabled(false);



            }catch (NullPointerException e){

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                progressDialog.dismiss();
            }
        }
    }

    private boolean validate(){
        boolean flag=true;
        strPhone=etPhone.getText().toString();
        strAddress=etAddress.getText().toString();
        strCity=etCity.getText().toString();
        strZip=etZip.getText().toString();

        if(strPhone.trim().length()==0){
            flag=false;
            etPhone.setError("Required");

        }

        if(strAddress.trim().length()==0){
            flag=false;
            etAddress.setError("Required");
        }

        if(strCity.trim().length()==0){
            flag=false;
            etCity.setError("Required");
        }

        if(strZip.trim().length()==0){
            flag=false;
            etZip.setError("Required");
        }

        return flag;
    }

    private class Subscribe extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objSubscriptionCheckoutBL.setSubscriptionData(params[0], params[1], params[2], params[3], params[4], params[5]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try{

                if(Constant.WS_RESPONSE_SUCCESS.equalsIgnoreCase(s)){
                    Toast.makeText(getApplicationContext(),"Subscription Successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                }
                else
                    Toast.makeText(getApplicationContext(),"Something went wrong. Try again",Toast.LENGTH_LONG).show();
            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }
}
