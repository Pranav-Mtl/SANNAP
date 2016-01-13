package com.example.admin.sannap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


import com.example.admin.sannap.Adapter.QuestionPagerAdapter;

public class QuestionScreen extends FragmentActivity {


    ViewPager viewPager;
    QuestionPagerAdapter questionPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        viewPager= (ViewPager) findViewById(R.id.pager);
        questionPagerAdapter=new QuestionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(questionPagerAdapter);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                viewPager.setCurrentItem(viewPager.getCurrentItem());
                return true;
            }
        });







    }

}
