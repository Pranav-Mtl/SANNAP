package com.example.admin.sannap;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.sannap.Adapter.CartAdapter;
import com.example.admin.sannap.Constant.Constant;

public class Cart extends AppCompatActivity implements View.OnClickListener {

    RecyclerView listCart;

    TextView tvItem,tvTotal;

    CartAdapter objCartAdapter;

    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        initialize();

        objCartAdapter=new CartAdapter(getApplicationContext(),tvTotal);
        listCart.setAdapter(objCartAdapter);
    }


    private void initialize(){

        listCart= (RecyclerView) findViewById(R.id.cart_list);
        tvItem= (TextView) findViewById(R.id.shop_cart_quantity);
        tvTotal= (TextView) findViewById(R.id.cart_total);
        btnDone= (Button) findViewById(R.id.cart_done);

        btnDone.setOnClickListener(this);

        tvItem.setText(Constant.cartItem+"");


        listCart.setHasFixedSize(true);

        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listCart.setLayoutManager(llm);




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
        String strTotal=tvTotal.getText().toString();

        if(strTotal.trim().length()>0){
            String ss[]=strTotal.split("₹");
            startActivity(new Intent(getApplicationContext(),ShopingConfirmed.class).putExtra("Price",ss[1]));
        }
    }
}
