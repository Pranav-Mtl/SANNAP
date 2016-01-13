package com.example.admin.sannap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.admin.sannap.QuestionEight;
import com.example.admin.sannap.QuestionFive;
import com.example.admin.sannap.QuestionFour;
import com.example.admin.sannap.QuestionOne;
import com.example.admin.sannap.QuestionSeven;
import com.example.admin.sannap.QuestionSixth;
import com.example.admin.sannap.QuestionThree;
import com.example.admin.sannap.QuestionTwo;

/**
 * Created by Admin on 12/22/2015.
 */
public class QuestionPagerAdapter extends FragmentPagerAdapter {


    public QuestionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new QuestionOne();



        }

        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }
}