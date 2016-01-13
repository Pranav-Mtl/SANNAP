package com.example.admin.sannap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.sannap.BankCartTab;
import com.example.admin.sannap.CreditCardTab;
import com.example.admin.sannap.XYZCardTab;

/**
 * Created by Admin on 12/23/2015.
 */
public class PaymentAdapter  extends FragmentPagerAdapter {
    int tabCount;
    public PaymentAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new CreditCardTab();

            case 1:
                return new XYZCardTab();

            case 2:
                return  new BankCartTab();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
