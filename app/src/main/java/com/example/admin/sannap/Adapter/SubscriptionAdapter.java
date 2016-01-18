package com.example.admin.sannap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.sannap.BL.SubscriptionBL;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.R;

/**
 * Created by Admin on 12/21/2015.
 */

public class SubscriptionAdapter extends BaseAdapter {

    Context context;

    TextView tvName,tvPrice,tvOriginal;
    SubscriptionBL objSubscriptionBL;

    public SubscriptionAdapter(Context context) {

        this.context=context;
        objSubscriptionBL=new SubscriptionBL();
        objSubscriptionBL.getSubscriptionData();

    }

    @Override
    public int getCount() {
        if(Constant.modelName==null)
            return 0;
        return Constant.modelName.length;
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

        View gridView;
        if(convertView!=null)
        {
            gridView=convertView;
        }

        else
        {
            gridView=new View(context);
            gridView=layoutInflater.inflate(R.layout.subscription_adapter,null);

            tvName= (TextView) gridView.findViewById(R.id.subscription_model_name);
            tvPrice= (TextView) gridView.findViewById(R.id.subscription_model_price);
            tvOriginal= (TextView) gridView.findViewById(R.id.subscription_model_original);
        }

        tvName.setText(Constant.modelName[position]);
        tvOriginal.setText("Original Price: ₹"+Constant.modelOriginal[position]);
        tvPrice.setText("Discounted Price: ₹"+Constant.modelPrice[position]);


        return gridView;
    }
}
