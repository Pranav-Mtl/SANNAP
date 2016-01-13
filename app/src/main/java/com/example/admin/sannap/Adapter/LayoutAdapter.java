package com.example.admin.sannap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.sannap.LayoutFragment;
import com.example.admin.sannap.SecondLayoutFragment;

/**
 * Created by Admin on 12/29/2015.
 */
public class LayoutAdapter extends FragmentPagerAdapter {
    public LayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new LayoutFragment();

            case 1:
                return new SecondLayoutFragment();
        }

        return null;

    }


    @Override
    public int getCount() {
        return 2;
    }
}
