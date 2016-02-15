package com.example.admin.sannap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.sannap.BL.ShopBL;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.R;

import java.util.ArrayList;

/**
 * Created by appslure on 05-02-2016.
 */
public class ShopListViewAdapter extends  RecyclerView.Adapter<ShopListViewAdapter.ShopListViewHolder> {

    Context mContext;

    public ShopListViewAdapter(Context context,TextView quantity) {
        mContext=context;



       /* Constant.hmItemUnit=new HashMap();
        Constant.hmItemID=new HashMap();
        Constant.hmItemName=new HashMap();
        Constant.hmItemImage=new HashMap();
        Constant.hmItemPrice=new HashMap<String,Integer>();
        Constant.hmItemQuantity=new HashMap<String,Integer>();*/

        Constant.cartItem=0;

        Constant.listId=new ArrayList();
        Constant.listName=new ArrayList();
        Constant.listQuantity=new ArrayList();
        Constant.listImage=new ArrayList();
        Constant.listUnit=new ArrayList();
        Constant.listPrice=new ArrayList();
    }



    @Override
    public ShopListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(mContext).
                inflate(R.layout.today_body_raw, parent, false);

        return new ShopListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShopListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(Constant.shopID==null)
            return 0;

        return Constant.shopID.length;
    }

    public static class ShopListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvPrice;
        ImageView ivImage;
        LinearLayout llCart,llCartAdded;
        public ShopListViewHolder(View gridView) {
            super(gridView);

            tvName= (TextView) gridView.findViewById(R.id.shop_name);
            tvPrice= (TextView) gridView.findViewById(R.id.shop_price);
            ivImage= (ImageView) gridView.findViewById(R.id.shop_image);
            llCart= (LinearLayout) gridView.findViewById(R.id.ll_add_cart);
            llCartAdded= (LinearLayout) gridView.findViewById(R.id.ll_added);


        }
    }
}
