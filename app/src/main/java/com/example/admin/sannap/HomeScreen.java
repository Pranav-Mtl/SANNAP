package com.example.admin.sannap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.sannap.BE.HomeScreenBE;
import com.example.admin.sannap.BL.HomeScreenBL;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.circularseekbar.CircularSeekBar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HomeScreen extends AppCompatActivity implements CircularSeekBar.OnCircularSeekBarChangeListener {

    ImageView shop,subscription;

    //CircularSlider cs;

    TextView tvPosition,tvPeriod,tvDay,tvNextCycle;

    LinearLayout llMiddle;

    String userID;

    HomeScreenBL objHomeScreenBL;
    HomeScreenBE objHomeScreenBE;

    ProgressDialog progressDialog;

    CircularSeekBar seekbar;

    int period,bleakPeriod,fertile,afterFertile,pms;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        shop= (ImageView) findViewById(R.id.shop_btn);
        subscription= (ImageView) findViewById(R.id.subscription_btn);
        //cs= (CircularSlider) findViewById(R.id.circular);
        tvPosition= (TextView) findViewById(R.id.circular_position);
        tvPeriod= (TextView) findViewById(R.id.circular_period);
        tvDay= (TextView) findViewById(R.id.circular_day);
        tvNextCycle= (TextView) findViewById(R.id.circular_nextcycle);

        llMiddle= (LinearLayout) findViewById(R.id.ll_middle);

        userID= Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_LOGIN_ID);
        objHomeScreenBL=new HomeScreenBL();
        objHomeScreenBE=new HomeScreenBE();

        progressDialog=new ProgressDialog(HomeScreen.this);

        seekbar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        seekbar.setOnSeekBarChangeListener(this);
        seekbar.getProgress();




        seekbar.setBackgroundResource(R.drawable.ic_home_circle);
        seekbar.setCircleColor(Color.TRANSPARENT);
        seekbar.setCircleProgressColor(Color.TRANSPARENT);
        seekbar.setPointerColor(getResources().getColor(R.color.white));
        seekbar.setPointerHaloColor(getResources().getColor(R.color.red));
        seekbar.setPointerAlpha(getResources().getColor(R.color.red_alpha));
        seekbar.setPointerAlphaOnTouch(getResources().getColor(R.color.red_alpha));

        seekbar.setCircleFillColor(Color.TRANSPARENT);

        if(Util.isInternetConnection(HomeScreen.this))
            new GetData().execute(userID);
        else
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();




       /* cs.setOnSliderMovedListener(new CircularSlider.OnSliderMovedListener() {
            @Override
            public void onSliderMoved(double pos) {
                Double d = new Double(pos);
                int i = d.intValue();

                if(i<0)
                {
                    tvPosition.setText("Minus: " + String.format("%.2f",pos));
                }
                else {
                    tvPosition.setText("Plus: " + String.format("%.2f",pos));
                }
            }
        });*/

        llMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TodayScreen.class));
            }
        });


        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ShopScreen.class);
                startActivity(intent);
            }
        });



        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Subscription.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onProgressChanged(CircularSeekBar circularSeekBar, int progress, boolean fromUser) {
        tvPosition.setText("Day: "+progress);


        if(progress<=period){
            tvPeriod.setText("Period");
            llMiddle.setBackgroundResource(R.drawable.ic_home_inner_circle_red);
        }
        else if(progress>period && progress<=bleakPeriod){
            tvPeriod.setText("Fertile");
            llMiddle.setBackgroundResource(R.drawable.ic_home_inner_circle_gray);
        }
        else if(progress>bleakPeriod && progress<=fertile){
            tvPeriod.setText("Fertile");
            llMiddle.setBackgroundResource(R.drawable.ic_home_inner_circle_gray);
        }
        else if(progress>fertile && progress<=afterFertile){
            tvPeriod.setText("After Fertile");
            llMiddle.setBackgroundResource(R.drawable.ic_home_inner_circle_green);
        }
        else if(progress>afterFertile && progress<=pms){
            tvPeriod.setText("PMS");
            llMiddle.setBackgroundResource(R.drawable.ic_home_inner_circle_green);
        }

        try {
            String dt = objHomeScreenBE.getFirstDay();  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(dt));
            c.add(Calendar.DATE, progress);
            dt = sdf.format(c.getTime());

            String onlydate=sdf.parse(dt)+"";
            onlydate=onlydate.substring(0,10);
            tvDay.setText(onlydate);


        }catch (Exception e){

        }

    }

    @Override
    public void onStopTrackingTouch(CircularSeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(CircularSeekBar seekBar) {

    }

    private class  GetData extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objHomeScreenBL.getData(params[0],objHomeScreenBE);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                seekbar.setMax(objHomeScreenBE.getCycleLength());

                period=objHomeScreenBE.getPeriod();
                bleakPeriod=objHomeScreenBE.getPeriod()+objHomeScreenBE.getPeriodBleak();
                fertile=objHomeScreenBE.getPeriod()+objHomeScreenBE.getPeriodBleak()+objHomeScreenBE.getFertile();
                afterFertile=objHomeScreenBE.getPeriod()+objHomeScreenBE.getPeriodBleak()+objHomeScreenBE.getFertile()+objHomeScreenBE.getAfterFertile();
                pms=objHomeScreenBE.getPeriod()+objHomeScreenBE.getPeriodBleak()+objHomeScreenBE.getFertile()+objHomeScreenBE.getAfterFertile()+objHomeScreenBE.getPms();

                /* set current day*/

                String onlydate=sdf.parse(objHomeScreenBE.getFirstDay())+"";
                onlydate=onlydate.substring(0,10);
                tvDay.setText(onlydate);

                onlydate=sdf.parse(objHomeScreenBE.getNextFirstDay())+"";
                onlydate=onlydate.substring(0,10);
                tvNextCycle.setText("NP: " + onlydate);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                System.out.println(dateFormat.format(date)+"-"+objHomeScreenBE.getFirstDay());
                String date1 =dateFormat.format(date);
                String date2 =objHomeScreenBE.getFirstDay();

               /* String format = "MM-dd-yyyy HH:mm:ss";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                Date fromDate = sdf.parse(date1);
                Date toDate = sdf.parse(date2).getTime();

                long diff = fromDate.getTime() - toDate.getTime();

                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);



                Log.d("Day diff",diffDays+"");
                if(diffDays>0){

                    seekbar.setProgress(Math.abs((int)diffDays));
                }*/

                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                Calendar now = Calendar.getInstance();

                String strDate2 = objHomeScreenBE.getFirstDay();
                Calendar date3 = Calendar.getInstance();
                date3.setTimeInMillis(sdfDate.parse(strDate2).getTime());

                System.out.println(now.get(Calendar.DATE));
                System.out.println(date3.get(Calendar.DATE));

                int diffDays=Integer.valueOf(now.get(Calendar.DATE))-Integer.valueOf(date3.get(Calendar.DATE));
                seekbar.setProgress(Math.abs(diffDays));

                tvPosition.setText("Day: " + diffDays);






            }catch (NullPointerException e){

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                progressDialog.dismiss();
            }
        }
    }
}
