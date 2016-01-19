package com.example.admin.sannap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by appslure on 18-01-2016.
 */
public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.CartHolder> implements View.OnClickListener {

    Context mContext;
    String strID[];
    String strName[];
    String strQuantity[];
    String strImage[];
    String strUnit[];
    String strPrice[];

    int allPrice=0;

    TextView tvCartTotal;
    public CartAdapter(Context context,TextView tvTotal){
        mContext=context;
        tvCartTotal=tvTotal;

       /* Constant.listId=new ArrayList();
        Constant.listName=new ArrayList();
        Constant.listQuantity=new ArrayList();
        Constant.listImage=new ArrayList();
        Constant.listUnit=new ArrayList();
        Constant.listPrice=new ArrayList();

        Log.d("Item ID Price",Constant.hmItemPrice.values().toString());

        strID=Constant.hmItemID.values().toString().replace("[","").replace("]","").split(",");
        strName=Constant.hmItemName.values().toString().replace("[","").replace("]","").split(",");
        strQuantity=Constant.hmItemQuantity.values().toString().replace("[","").replace("]","").split(",");
        strImage=Constant.hmItemImage.values().toString().replace("[","").replace("]","").split(",");
        strUnit=Constant.hmItemUnit.values().toString().replace("[","").replace("]","").split(",");
        strPrice=Constant.hmItemPrice.values().toString().replace("[","").replace("]","").split(",");

        Log.d("STR Length",strID.length+"");

      *//*  for(int i=0;i<strID.length;i++){
            Constant.listId.add(strID[i]);
            Constant.listName.add(strID[i]);
            Constant.listQuantity.add(strID[i]);
            Constant.listImage.add(strID[i]);
            Constant.listUnit.add(strUnit[i]);
        }*/


    }

    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(mContext).
                inflate(R.layout.cart_item_raw, parent, false);

        return new CartHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {

        holder.tvTitle.setText(Constant.listName.get(position).toString());
        holder.tvPrice.setText("₹" + Constant.listPrice.get(position).toString()+ "/" +Constant.listUnit.get(position).toString());
        holder.tvQuantity.setText(Constant.listQuantity.get(position).toString());
        String price=calculatePrice(Constant.listPrice.get(position).toString(),Constant.listQuantity.get(position).toString());
        allPrice=allPrice+Integer.valueOf(price);
        tvCartTotal.setText("Sub Total ("+Constant.cartItem+" item) ₹"+allPrice);
        holder.tvPricePaid.setText("₹"+ Constant.listPrice.get(position).toString()+" X "+Constant.listQuantity.get(position).toString()+" = ₹"+price);


//        Log.d("IDDDD-->",strID[position]);

        Picasso.with(mContext)
                .load(Constant.listImage.get(position).toString())
                .resize(100, 100)
                .placeholder(R.drawable.shop_img)
                .error(R.drawable.shop_img)
                .into(holder.ivImage);

        holder.btnPlus.setOnClickListener(this);
        holder.btnPlus.setTag(position);

        holder.btnMinus.setOnClickListener(this);
        holder.btnMinus.setTag(position);
    }

    @Override
    public int getItemCount() {
        if(Constant.listId==null)
            return 0;
        return Constant.listId.size();
    }

    @Override
    public void onClick(View v) {
        int pos= (int) v.getTag();

        switch (v.getId()){
            case R.id.cart_btn_plus:
                int quan= (int) Constant.listQuantity.get(pos);
                quan++;
                Constant.listQuantity.set(pos,quan);
                allPrice=0;
                notifyDataSetChanged();
                break;
            case R.id.cart_btn_minus:
                int quanMinus= (int) Constant.listQuantity.get(pos);
                if(quanMinus>0) {
                    quanMinus--;
                    Constant.listQuantity.set(pos, quanMinus);
                    allPrice=0;
                    notifyDataSetChanged();
                }
                break;
        }
    }


    public static class CartHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle,tvPrice,tvQuantity,tvPricePaid;
        ImageButton btnPlus,btnMinus;

        public CartHolder(View gridView) {
            super(gridView);

            ivImage= (ImageView) gridView.findViewById(R.id.cart_image);
            tvTitle= (TextView) gridView.findViewById(R.id.cart_item_name);
            tvPrice= (TextView) gridView.findViewById(R.id.cart_item_price);
            tvPricePaid= (TextView) gridView.findViewById(R.id.cart_item_paid);
            tvQuantity= (TextView) gridView.findViewById(R.id.cart_quantity);
            btnPlus= (ImageButton) gridView.findViewById(R.id.cart_btn_plus);
            btnMinus= (ImageButton) gridView.findViewById(R.id.cart_btn_minus);


        }
    }

    private String calculatePrice(String price,String quantity){
        int pp=Integer.valueOf(price)*Integer.valueOf(quantity);
        return pp+"";
        }
}
