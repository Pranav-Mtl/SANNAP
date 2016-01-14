package com.example.admin.sannap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.sannap.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by appslure on 08-01-2016.
 */
public class TodayBodyAdapter extends  RecyclerView.Adapter<TodayBodyAdapter.TodayBodyHolder> {

    Context mContext;
    private List bodyList;

    Integer image[]={R.drawable.ic_backpain,R.drawable.ic_cramps,R.drawable.ic_cravings,R.drawable.ic_headache,R.drawable.ic_flashes,R.drawable.ic_insomnia,R.drawable.ic_skin,R.drawable.ic_breasts,R.drawable.ic_tired,R.drawable.ic_stomach};

    public TodayBodyAdapter(Context context){
        mContext=context;
        bodyList=new ArrayList();
        bodyList.add("Back Pain");
        bodyList.add("Cramps");
        bodyList.add("Cravings");
        bodyList.add("Headache");
        bodyList.add("Hot Flashes");
        bodyList.add("Insomnia");
        bodyList.add("Skin Problems");
        bodyList.add("Tender Breasts");
        bodyList.add("Tired");
        bodyList.add("Upset Stomach");
    }

    @Override
    public TodayBodyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(mContext).
                inflate(R.layout.today_body_raw, parent, false);

        return new TodayBodyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodayBodyHolder holder, int position) {
        holder.tvBody.setText(bodyList.get(position).toString());
        holder.ivBody.setBackgroundResource(image[position]);
    }

    @Override
    public int getItemCount() {
        return bodyList.size();
    }

    public static class TodayBodyHolder extends RecyclerView.ViewHolder {
        ImageView ivBody;
        TextView tvBody;
        CheckBox cbBody;
        public TodayBodyHolder(View gridView) {
            super(gridView);

            ivBody= (ImageView) gridView.findViewById(R.id.body_image);
            tvBody= (TextView) gridView.findViewById(R.id.body_text);
            cbBody= (CheckBox) gridView.findViewById(R.id.body_checkbox);


        }
    }
}
