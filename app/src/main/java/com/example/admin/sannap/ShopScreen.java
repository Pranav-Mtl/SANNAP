package com.example.admin.sannap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.admin.sannap.Adapter.ShopAdapter;

public class ShopScreen extends AppCompatActivity {


    ListView list;
    ShopAdapter shopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_screen);
        list= (ListView) findViewById(R.id.shop_list);
        
        shopAdapter=new ShopAdapter(getApplicationContext());

        list.setAdapter(shopAdapter);


    }

}
