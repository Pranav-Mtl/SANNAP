package com.example.admin.sannap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.admin.sannap.Adapter.SubscriptionAdapter;
import com.example.admin.sannap.Configuration.RecyclerItemClickListener;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;

public class Subscription extends AppCompatActivity {


    RecyclerView listPackages;
    SubscriptionAdapter subscriptionAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        listPackages= (RecyclerView) findViewById(R.id.packages_list);
        Util.setSharedPrefrenceValue(getApplicationContext(),Constant.PREFS_NAME,Constant.preferenceTotalValue,"0");
        Util.setSharedPrefrenceValue(getApplicationContext(),Constant.PREFS_NAME,Constant.preferenceCurrentValue,"0");
        initialize();
        new GetSubscriptionData().execute();

        listPackages.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), SelectProduct.class).putExtra("numberOfProduct", Constant.modelName[position]).putExtra("selectedProduct","0");
                        startActivity(intent);

                    }

                })
        );


    }

    private void initialize(){


        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listPackages.setLayoutManager(layoutManager);
        listPackages.setHasFixedSize(true);

        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listPackages.setLayoutManager(llm);

        progressDialog=new ProgressDialog(Subscription.this);

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

    private class GetSubscriptionData extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            subscriptionAdapter=new SubscriptionAdapter(getApplicationContext());
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                listPackages.setAdapter(subscriptionAdapter);
            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }

}
