package com.example.admin.sannap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.sannap.EmotionFragment;
import com.example.admin.sannap.EnergyFragment;
import com.example.admin.sannap.FifthFragment;
import com.example.admin.sannap.PainFragment;
import com.example.admin.sannap.SleepFragment;
import com.example.admin.sannap.TodayFragment;

/**
 * Created by Admin on 12/29/2015.
 */
public class TodayPagerAdapter extends FragmentPagerAdapter {

    public TodayPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new TodayFragment();

            case 1:
                return new PainFragment();

            case 2:
                return new PainFragment();

            case 3:
                return  new PainFragment();

            case 4:
                return  new PainFragment();

            case 5:
                return new PainFragment();
        }


        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
