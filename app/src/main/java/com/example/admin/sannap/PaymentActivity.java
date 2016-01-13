package com.example.admin.sannap;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.example.admin.sannap.Adapter.PaymentAdapter;


public class PaymentActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PaymentAdapter paymentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar= (Toolbar) findViewById(R.id.payment_toolbar);
        ViewPager viewPager= (ViewPager) findViewById(R.id.payment_viewPager);
        TabLayout tabLayout= (TabLayout) findViewById(R.id.payment_tablayout);

        setSupportActionBar(toolbar);
        tabLayout.addTab(tabLayout.newTab().setText("Credit Card"));
        tabLayout.addTab(tabLayout.newTab().setText("XYZ Card"));
        tabLayout.addTab(tabLayout.newTab().setText("Bank Card"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

       paymentAdapter=new PaymentAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(paymentAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }


}