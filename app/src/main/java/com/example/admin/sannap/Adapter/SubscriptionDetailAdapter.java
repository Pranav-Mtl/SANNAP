package com.example.admin.sannap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.sannap.BL.SubscriptionDetailBL;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.R;
import com.example.admin.sannap.SubscriptionDetail;

/**
 * Created by appslure on 15-01-2016.
 */
public class SubscriptionDetailAdapter extends  RecyclerView.Adapter<SubscriptionDetailAdapter.SubscriptionDetailHolder>  {

    Context mContext;
    SubscriptionDetailBL objSubscriptionDetailBL;

    public SubscriptionDetailAdapter(Context context,String model){
        mContext=context;
        objSubscriptionDetailBL=new SubscriptionDetailBL();
        objSubscriptionDetailBL.getSubscriptionDetail(model);
    }
    @Override
    public SubscriptionDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(mContext).
                inflate(R.layout.subscription_detail_raw, parent, false);

        return new SubscriptionDetailHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubscriptionDetailHolder holder, int position) {
        holder.tvName.setText(Constant.detailName[position]);
        holder.tvQuantity.setText(Constant.detailQuantity[position]);
        holder.tvUnit.setText(Constant.detailUnit[position]);
    }

    @Override
    public int getItemCount() {
        if(Constant.detailName==null)
            return 0;
        return Constant.detailName.length;
    }

    public static class SubscriptionDetailHolder extends RecyclerView.ViewHolder  {

        TextView tvName,tvQuantity,tvUnit;
        public SubscriptionDetailHolder(View gridView) {
            super(gridView);

            tvName= (TextView) gridView.findViewById(R.id.detail_name);
            tvQuantity= (TextView) gridView.findViewById(R.id.detail_quantity);
            tvUnit= (TextView) gridView.findViewById(R.id.detail_unit);


        }
    }
}
