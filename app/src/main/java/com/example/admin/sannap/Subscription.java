package com.example.admin.sannap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.admin.sannap.Adapter.SubscriptionAdapter;

public class Subscription extends AppCompatActivity {


    ListView list;
    SubscriptionAdapter subscriptionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        list= (ListView) findViewById(R.id.subscription_list);

        subscriptionAdapter=new SubscriptionAdapter(getApplicationContext());
        list.setAdapter(subscriptionAdapter);




    }

}
