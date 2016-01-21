package com.example.admin.sannap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.sannap.BL.ShopBL;
import com.example.admin.sannap.BL.SubscriptionBL;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Admin on 12/21/2015.
 */
public class ShopAdapter extends BaseAdapter implements View.OnClickListener  {

    Context context;

    TextView tvName,tvPrice;
    ImageView ivImage;
    LinearLayout llCart,llCartAdded;
    ShopBL objShopBL;

    TextView cartQuantity;



    public ShopAdapter(Context context,TextView quantity) {
        this.context=context;

        cartQuantity=quantity;
        objShopBL=new ShopBL();
        objShopBL.getShopData();

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
    public int getCount() {

        if(Constant.shopID==null)
            return 0;

        return Constant.shopID.length;
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
            gridView=layoutInflater.inflate(R.layout.shop,null);


            tvName= (TextView) gridView.findViewById(R.id.shop_name);
            tvPrice= (TextView) gridView.findViewById(R.id.shop_price);
            ivImage= (ImageView) gridView.findViewById(R.id.shop_image);
            llCart= (LinearLayout) gridView.findViewById(R.id.ll_add_cart);
            llCartAdded= (LinearLayout) gridView.findViewById(R.id.ll_added);


        }

        tvName.setText(Constant.shopName[position]);
        tvPrice.setText("₹" + Constant.shopPrice[position] + "/" + Constant.shopUnit[position]);

        Picasso.with(context)
                .load(Constant.shopImage[position])
                .resize(100, 100)
                .placeholder(R.drawable.shop_img)
                .error(R.drawable.shop_img)
                .into(ivImage);

        llCart.setOnClickListener(this);
        llCart.setTag(position);



        return gridView;
    }

    @Override
    public void onClick(View v) {
        int pos=(int )v.getTag();

        switch (v.getId()){
            case R.id.ll_add_cart:
                Constant.cartItem++;

                Constant.listId.add(Constant.shopID[pos]);
                Constant.listName.add(Constant.shopName[pos]);
                Constant.listPrice.add(Constant.shopPrice[pos]);
                Constant.listImage.add(Constant.shopImage[pos]);
                Constant.listUnit.add(Constant.shopUnit[pos]);
                Constant.listQuantity.add(1);


               /* Constant.hmItemID.put(Constant.shopID[pos], Constant.shopID[pos]);
                Constant.hmItemName.put(Constant.shopName[pos], Constant.shopName[pos]);
                Constant.hmItemPrice.put(Constant.shopName[pos], Constant.shopPrice[pos]);
                Constant.hmItemImage.put(Constant.shopImage[pos], Constant.shopImage[pos]);
                Constant.hmItemUnit.put(Constant.shopUnit[pos], Constant.shopUnit[pos]);*/
                cartQuantity.setText(Constant.cartItem + "");
                v.setVisibility(View.GONE);
                break;
        }
    }
}
