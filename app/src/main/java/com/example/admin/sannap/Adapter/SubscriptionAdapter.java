package com.example.admin.sannap.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.sannap.BL.SubscriptionBL;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.R;

/**
 * Created by Admin on 12/21/2015.
 */

public class SubscriptionAdapter extends  RecyclerView.Adapter<SubscriptionAdapter.SubscriptionHolder>{

    Context context;
    SubscriptionBL objSubscriptionBL;
    int i=1;

    public SubscriptionAdapter(Context context) {

        this.context=context;
        String userId=Util.getSharedPrefrenceValue(context,Constant.SP_LOGIN_ID);
        objSubscriptionBL=new SubscriptionBL();
        objSubscriptionBL.getSubscriptionData(userId);

    }

    @Override
    public SubscriptionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(context).
                inflate(R.layout.subscription_adapter, parent, false);

        return new SubscriptionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubscriptionHolder holder, int position) {


        i=position+1;

        holder.subscriptionModel.setText(Constant.modelName[position]);
        holder.subscriptionModelPrice.setText(" Subscription packages"+" @" + Constant.modelPrice[position]);
        if(i%2==0) {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#E98457"));
        }
        else {
            System.out.println("getting in second position-->"+(position+1)/2);
            holder.linearLayout.setBackgroundColor(Color.parseColor("#F3735B"));
        }


    }

    @Override
    public int getItemCount() {
        return Constant.modelName.length;
    }

    public static class SubscriptionHolder extends RecyclerView.ViewHolder {

        TextView subscriptionModel,subscriptionModelPrice;
        LinearLayout linearLayout;

        public SubscriptionHolder(View gridView) {
            super(gridView);

            linearLayout=(LinearLayout)gridView.findViewById(R.id.linearLayout);
            subscriptionModel = (TextView) gridView.findViewById(R.id.subscription_model_name);
            subscriptionModelPrice=(TextView)gridView.findViewById(R.id.subscription_model_price);


        }

    }
}
