package com.example.admin.sannap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.sannap.Adapter.ShopAdapter;
import com.example.admin.sannap.Adapter.SubscriptionAdapter;
import com.example.admin.sannap.Constant.Constant;

public class ShopScreen extends AppCompatActivity implements View.OnClickListener {

    GridView list;
    ShopAdapter objShopAdapter;
    ProgressDialog progressDialog;

    RelativeLayout rlCart;

    TextView tvCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_screen);
        initialize();

        new GetShopData().execute();





    }

    private void initialize(){
        list= (GridView) findViewById(R.id.shopping_gridview);
        tvCart= (TextView) findViewById(R.id.shop_cart_quantity);
        rlCart= (RelativeLayout) findViewById(R.id.rl_shop_cart);
        progressDialog=new ProgressDialog(ShopScreen.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        rlCart.setOnClickListener(this);
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
            case R.id.rl_shop_cart:
                startActivity(new Intent(getApplicationContext(),Cart.class));
                break;
        }
    }


    private class GetShopData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
           objShopAdapter=new ShopAdapter(getApplicationContext(),tvCart);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                list.setAdapter(objShopAdapter);
            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }


}
