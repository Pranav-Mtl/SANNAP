package com.example.admin.sannap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.admin.sannap.R;

/**
 * Created by Admin on 12/21/2015.
 */
public class ShopAdapter extends BaseAdapter {

    Context context;
    public ShopAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View gridView=null;
        if (convertView != null){
            gridView=convertView;
        }else{
            gridView = new View(context);

            gridView= layoutInflater.inflate(R.layout.shop, null);
        }


        return gridView;
    }
}
