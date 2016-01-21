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
import java.util.HashMap;
import java.util.List;

/**
 * Created by appslure on 08-01-2016.
 */
public class TodayMoodAdapter extends  RecyclerView.Adapter<TodayMoodAdapter.TodayMoodHolder> {

    Context mContext;
    private List moodList;

    public int mSelectedItem = -1;
    CheckBox lastChecked;

    public HashMap<Integer,String> mapMood=new HashMap<Integer,String>();

    Integer image[]={R.drawable.ic_angry,R.drawable.ic_happy,R.drawable.ic_desire,R.drawable.ic_sad,R.drawable.ic_sensitive,R.drawable.ic_stressed,R.drawable.ic_uncomfortable,R.drawable.ic_unsocial};


    public TodayMoodAdapter(Context context){
        mContext=context;
        moodList=new ArrayList();
        moodList.add("Angry");
        moodList.add("Happy");
        moodList.add("High Desire");
        moodList.add("Sad");
        moodList.add("Sensitive");
        moodList.add("Stressed");
        moodList.add("Uncomfortable");
        moodList.add("Unsocial");


    }

    @Override
    public TodayMoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(mContext).
                inflate(R.layout.today_body_raw, parent, false);

        return new TodayMoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodayMoodHolder holder, final int position) {
        holder.tvBody.setText(moodList.get(position).toString());
        holder.ivBody.setBackgroundResource(image[position]);

        holder.cbBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckBox cb = (CheckBox) v;

                if (cb.isChecked()) {
                    mapMood.put(position,moodList.get(position).toString());
                    mSelectedItem++;


                } else {
                    mapMood.remove(position);
                    mSelectedItem--;
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return moodList.size();
    }

    public static class TodayMoodHolder extends RecyclerView.ViewHolder {
        ImageView ivBody;
        TextView tvBody;
        CheckBox cbBody;
        public TodayMoodHolder(View gridView) {
            super(gridView);

            ivBody= (ImageView) gridView.findViewById(R.id.body_image);
            tvBody= (TextView) gridView.findViewById(R.id.body_text);
            cbBody= (CheckBox) gridView.findViewById(R.id.body_checkbox);


        }
    }
}
