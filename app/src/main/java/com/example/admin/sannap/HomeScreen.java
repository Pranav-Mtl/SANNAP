package com.example.admin.sannap;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.sannap.Adapter.DrawerAdapter;
import com.example.admin.sannap.BE.HomeScreenBE;
import com.example.admin.sannap.BL.HomeScreenBL;
import com.example.admin.sannap.Configuration.RecyclerItemClickListener;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.circularseekbar.CircularSeekBar;

import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HomeScreen extends AppCompatActivity implements CircularSeekBar.OnCircularSeekBarChangeListener {

    ImageView shop, subscription;

    Toolbar toolbar;

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    DrawerAdapter drawerAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout
    ActionBarDrawerToggle mDrawerToggle;

    //CircularSlider cs;

    TextView tvPosition, tvPeriod, tvDay, tvNextCycle;


    LinearLayout llMiddle;

    ImageButton btnStats;

    String userID;

    HomeScreenBL objHomeScreenBL;
    HomeScreenBE objHomeScreenBE;

    ProgressDialog progressDialog;

    CircularSeekBar seekbar;

    int period, bleakPeriod, fertile, afterFertile, pms;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    int xx,yy;

    View _itemColoured;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        shop = (ImageView) findViewById(R.id.shop_btn);
        subscription = (ImageView) findViewById(R.id.subscription_doc);
        //cs= (CircularSlider) findViewById(R.id.circular);
        tvPosition = (TextView) findViewById(R.id.circular_position);
        tvPeriod = (TextView) findViewById(R.id.circular_period);
        tvDay = (TextView) findViewById(R.id.circular_day);
        tvNextCycle = (TextView) findViewById(R.id.circular_nextcycle);
        btnStats= (ImageButton) findViewById(R.id.home__stats);

        llMiddle = (LinearLayout) findViewById(R.id.ll_middle);

        userID = Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_LOGIN_ID);
        objHomeScreenBL = new HomeScreenBL();
        objHomeScreenBE = new HomeScreenBE();

        progressDialog = new ProgressDialog(HomeScreen.this);

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

        initialize();

        if (Util.isInternetConnection(HomeScreen.this))
            new GetData().execute(userID);
        else
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();


        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        if (position != 0) {
                            if (_itemColoured != null) {
                                _itemColoured.setBackgroundColor(getResources().getColor(R.color.light_pink));
                                _itemColoured.invalidate();
                            }
                            _itemColoured = view;
                            view.setBackgroundColor(getResources().getColor(R.color.dark_pink));
                        }

                        if(position==1){
                            startActivity(new Intent(getApplicationContext(),OrderHistory.class));
                        }



                    }
                }));




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

        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TodayScreen.class).putExtra("CycleID",objHomeScreenBE.getCycleID()+""));
            }
        });


        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogShop(HomeScreen.this);

            }
        });

/*

        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Subscription.class);
                startActivity(intent);
            }
        });
*/

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);
        // Setting the adapter to RecyclerView
        // Creating a layout Manager
        mLayoutManager = new LinearLayoutManager(HomeScreen.this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer iscom/example/admin/oneclickwash/HomeScreen.java:50
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }

        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();

        popupSize();

    }

    @Override
    public void onProgressChanged(CircularSeekBar circularSeekBar, int progress, boolean fromUser) {
        tvPosition.setText("Day: " + progress);


        if (progress <= period) {
            tvPeriod.setText("Period");
            llMiddle.setBackgroundResource(R.drawable.ic_home_inner_circle_red);
        } else if (progress > period && progress <= bleakPeriod) {
            tvPeriod.setText("Fertile");
            llMiddle.setBackgroundResource(R.drawable.ic_home_inner_circle_gray);
        } else if (progress > bleakPeriod && progress <= fertile) {
            tvPeriod.setText("Fertile");
            llMiddle.setBackgroundResource(R.drawable.ic_home_inner_circle_gray);
        } else if (progress > fertile && progress <= afterFertile) {
            tvPeriod.setText("After Fertile");
            llMiddle.setBackgroundResource(R.drawable.ic_home_inner_circle_green);
        } else if (progress > afterFertile && progress <= pms) {
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

            String onlydate = sdf.parse(dt) + "";
            onlydate = onlydate.substring(0, 10);
            tvDay.setText(onlydate);


        } catch (Exception e) {

        }

    }

    @Override
    public void onStopTrackingTouch(CircularSeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(CircularSeekBar seekBar) {

    }


    private class GetData extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result = objHomeScreenBL.getData(params[0], objHomeScreenBE);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                seekbar.setMax(objHomeScreenBE.getCycleLength());

                if (objHomeScreenBE.getSubscribe().equalsIgnoreCase("Y")) {

                }
                Constant.Name=objHomeScreenBE.getName();
                drawerAdapter = new DrawerAdapter(Constant.TITLES,Constant.ICONS,Constant.Name,getApplicationContext());// Letting the s0ystem know that the list objects are of fixed size
                mRecyclerView.setAdapter(drawerAdapter);

                period = objHomeScreenBE.getPeriod();
                bleakPeriod = objHomeScreenBE.getPeriod() + objHomeScreenBE.getPeriodBleak();
                fertile = objHomeScreenBE.getPeriod() + objHomeScreenBE.getPeriodBleak() + objHomeScreenBE.getFertile();
                afterFertile = objHomeScreenBE.getPeriod() + objHomeScreenBE.getPeriodBleak() + objHomeScreenBE.getFertile() + objHomeScreenBE.getAfterFertile();
                pms = objHomeScreenBE.getPeriod() + objHomeScreenBE.getPeriodBleak() + objHomeScreenBE.getFertile() + objHomeScreenBE.getAfterFertile() + objHomeScreenBE.getPms();

                /* set current day*/

                String onlydate = sdf.parse(objHomeScreenBE.getFirstDay()) + "";
                onlydate = onlydate.substring(0, 10);
                tvDay.setText(onlydate);

                onlydate = sdf.parse(objHomeScreenBE.getNextFirstDay()) + "";
                onlydate = onlydate.substring(0, 10);
                tvNextCycle.setText("NP: " + onlydate);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                System.out.println(dateFormat.format(date) + "-" + objHomeScreenBE.getFirstDay());
                String date1 = dateFormat.format(date);
                String date2 = objHomeScreenBE.getFirstDay();

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

                int diffDays = Integer.valueOf(now.get(Calendar.DATE)) - Integer.valueOf(date3.get(Calendar.DATE));
                seekbar.setProgress(Math.abs(diffDays));

                tvPosition.setText("Day: " + diffDays);


            } catch (NullPointerException e) {

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                progressDialog.dismiss();
            }
        }
    }

    private void popupSize(){
        Display display = getWindowManager().getDefaultDisplay();

        int width = display.getWidth();
        int height = display.getHeight();

        // System.out.println("width" + width + "height" + height);

        if(width>=1000 && height>=1500){
            xx=800;
            yy=750;
        }
        else if(width>=700 && height>=1000)
        {
            xx=600;
            yy=550;
        }
        else
        {
            xx=450;
            yy=500;
        }

    }



    /* popup for add referal */
    private void showDialogShop(Context context){
        // x -->  X-Cordinate
        // y -->  Y-Cordinate

        final TextView tvTitle;
        Button btnClosePopup,btnsave;
        RadioGroup rgPayment;
        final RadioButton rbCash,rbOnline;

        final Dialog dialog  = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.popup_shoping);
        dialog.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.CENTER;
        wmlp.width=xx;
        wmlp.height=yy;




        btnClosePopup = (Button) dialog.findViewById(R.id.popup_cancel);
        btnsave= (Button) dialog.findViewById(R.id.popup_add);






        btnClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Subscription.class);
                startActivity(intent);
                //Toast.makeText(SellerQuestionExpandable.this,edittext.getText().toString(),Toast.LENGTH_LONG).show();
                dialog.dismiss();
                //finish();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           Intent intent = new Intent(getApplicationContext(), ShopScreen.class);
                                           startActivity(intent);
                                           dialog.dismiss();
                                       }
                                   }

        );
        dialog.show();
    }
}
