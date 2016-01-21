package com.example.admin.sannap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.sannap.BL.OrderHistoryBL;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.R;

import java.text.SimpleDateFormat;

/**
 * Created by appslure on 20-01-2016.
 */
public class OrderHistoryAdapter extends  RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryHolder> implements View.OnClickListener {

    Context mContext;

    OrderHistoryBL objOrderHistoryBL;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public OrderHistoryAdapter(Context context,String userID){
        mContext=context;
        objOrderHistoryBL=new OrderHistoryBL();
        objOrderHistoryBL.getOrderHistory(userID);
    }

    @Override
    public OrderHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(mContext).
                inflate(R.layout.order_history_raw, parent, false);

        return new OrderHistoryHolder(itemView);
    }


    @Override
    public void onBindViewHolder(OrderHistoryHolder holder, int position) {
        holder.tvID.setText("Order ID: SN"+Constant.orderID[position]);
        holder.tvName.setText("Name: "+Constant.orderName[position]);
        holder.tvAddress.setText("Address: "+Constant.orderAddress[position]);
        holder.tvAmount.setText("Amount: ₹"+Constant.orderAmount[position]);
        holder.tvStatus.setText("Status: "+Constant.orderStatus[position]);
        holder.tvItem.setText("Items: "+Constant.orderItem[position]);


        String dd[]=Constant.orderDate[position].split(" ");
        try {
            String onlydate=sdf.parse(dd[0])+"";
            onlydate=onlydate.substring(0,10);
            holder.tvDate.setText("Order on: "+onlydate);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        if(Constant.orderID==null)
            return 0;
        return Constant.orderID.length;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    public static class OrderHistoryHolder extends RecyclerView.ViewHolder {

       TextView tvID,tvName,tvAddress,tvAmount,tvItem,tvStatus,tvDate;

        public OrderHistoryHolder(View gridView) {
            super(gridView);

            tvID= (TextView) gridView.findViewById(R.id.history_orderid);
            tvName= (TextView) gridView.findViewById(R.id.history_order_name);
            tvAddress= (TextView) gridView.findViewById(R.id.history_order_address);
            tvAmount= (TextView) gridView.findViewById(R.id.history_order_amount);
            tvItem= (TextView) gridView.findViewById(R.id.history_order_items);
            tvStatus= (TextView) gridView.findViewById(R.id.history_order_status);
            tvDate= (TextView) gridView.findViewById(R.id.history_order_date);

        }
    }
}
