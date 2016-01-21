package com.example.admin.sannap;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;

import com.example.admin.sannap.Adapter.OrderHistoryAdapter;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;

public class OrderHistory extends AppCompatActivity {

    RecyclerView listBooking;
    ProgressDialog progressDialog;

    int xx,yy;

    String userID;

    OrderHistoryAdapter objOrderHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        initialize();

        if(Util.isInternetConnection(OrderHistory.this));
            new GetHistory().execute(userID);
    }


    private void initialize(){
        listBooking= (RecyclerView) findViewById(R.id.booking_list);


        listBooking.setHasFixedSize(true);


        progressDialog=new ProgressDialog(OrderHistory.this);

        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listBooking.setLayoutManager(llm);

        userID= Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_LOGIN_ID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        popupSize();
    }

    private void popupSize(){
        Display display = getWindowManager().getDefaultDisplay();

        int width = display.getWidth();
        int height = display.getHeight();

        // System.out.println("width" + width + "height" + height);

        if(width>=1000 && height>=1500){
            xx=700;
            yy=650;
        }
        else if(width>=700 && height>=1000)
        {
            xx=550;
            yy=500;
        }
        else
        {
            xx=450;
            yy=400;
        }

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

    private class GetHistory extends AsyncTask<String,String,String >{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            objOrderHistoryAdapter=new OrderHistoryAdapter(getApplicationContext(),params[0]);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            try{

                    listBooking.setAdapter(objOrderHistoryAdapter);

            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }

}
