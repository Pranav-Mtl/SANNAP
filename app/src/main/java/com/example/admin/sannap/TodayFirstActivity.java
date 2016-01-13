package com.example.admin.sannap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.admin.sannap.Adapter.TodayPagerAdapter;

public class TodayFirstActivity extends FragmentActivity{

    TodayPagerAdapter todayPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_first);
        viewPager= (ViewPager) findViewById(R.id.today_pager);

        todayPagerAdapter=new TodayPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(todayPagerAdapter);





    }

}
