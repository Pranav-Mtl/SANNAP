package com.example.admin.sannap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.admin.sannap.Adapter.SubscriptionDetailAdapter;
import com.example.admin.sannap.Constant.Constant;

public class SubscriptionDetail extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recycleDetail;

    String modelID,modelname,modelPrice;

    ProgressDialog progressDialog;

    SubscriptionDetailAdapter objSubscriptionDetailAdapter;

    Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_detail);
        initialize();

        new GetData().execute(modelID);
    }

    private void initialize(){
        recycleDetail= (RecyclerView) findViewById(R.id.subscription_detail);
        btnCheckout= (Button) findViewById(R.id.detail_checkout);

        btnCheckout.setOnClickListener(this);

        recycleDetail.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycleDetail.setLayoutManager(llm);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressDialog=new ProgressDialog(SubscriptionDetail.this);

        modelID=getIntent().getStringExtra("Model");
        modelname=getIntent().getStringExtra("ModelName");
        modelPrice=getIntent().getStringExtra("ModelPrice");
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
            case R.id.detail_checkout:
                startActivity(new Intent(getApplicationContext(),SubscriptionCheckout.class).putExtra("Model",modelID).putExtra("ModelName", modelname).putExtra("ModelPrice",modelPrice));
                break;
        }
    }


    private class GetData extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            objSubscriptionDetailAdapter=new SubscriptionDetailAdapter(getApplicationContext(),params[0]);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                recycleDetail.setAdapter(objSubscriptionDetailAdapter);
            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }
}
