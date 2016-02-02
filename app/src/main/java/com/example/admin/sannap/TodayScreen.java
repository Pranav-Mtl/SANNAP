package com.example.admin.sannap;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.sannap.BE.TodayBean;
import com.example.admin.sannap.BL.TodayScreenBL;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;

public class TodayScreen extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout btnNotes,btnBody,btnMood,btnTemperature,btnCervical,btnSpotting,btnOvulation,btnSexual,btnPill,btnPeriodLog;

    int xx,yy;

    TodayBean objTodayBean;
    TodayScreenBL objTodayScreenBL;

    ImageButton btnDone;

    String userID;
    String cycleID;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_screen);
        initialize();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Today selection",objTodayBean.getBody()+"\n"+objTodayBean.getNotes()+"\n"+objTodayBean.getCervical()+"\n"+objTodayBean.getOvulation()+"\n"+objTodayBean.getPill()+"\n"+objTodayBean.getSexual()+"\n"+objTodayBean.getSpotting()+"\n"+objTodayBean.getTemperature());
                if (Util.isInternetConnection(TodayScreen.this))
                    new SendLog().execute();

            }
        });
    }

    private void initialize() {

        btnBody= (RelativeLayout) findViewById(R.id.today_btn_body);
        btnMood= (RelativeLayout) findViewById(R.id.today_btn_mood);
        btnCervical= (RelativeLayout) findViewById(R.id.today_btn_cervical);
        btnTemperature= (RelativeLayout) findViewById(R.id.today_btn_temperature);
        btnSpotting= (RelativeLayout) findViewById(R.id.today_btn_spotting);
        btnOvulation= (RelativeLayout) findViewById(R.id.today_btn_ovulation);
        btnSexual= (RelativeLayout) findViewById(R.id.today_btn_sexual);
        btnPill= (RelativeLayout) findViewById(R.id.today_btn_pill);
        btnDone= (ImageButton) findViewById(R.id.today_Screen_ok);
        btnNotes= (RelativeLayout) findViewById(R.id.today_btn_notes);
        btnPeriodLog= (RelativeLayout) findViewById(R.id.today_btn_period_log);

        userID= Util.getSharedPrefrenceValue(TodayScreen.this, Constant.SP_LOGIN_ID);
        cycleID=getIntent().getStringExtra("CycleID");
        progressDialog=new ProgressDialog(TodayScreen.this);


        btnBody.setOnClickListener(this);
        btnMood.setOnClickListener(this);
        btnTemperature.setOnClickListener(this);
        btnCervical.setOnClickListener(this);
        btnSpotting.setOnClickListener(this);
        btnOvulation.setOnClickListener(this);
        btnSexual.setOnClickListener(this);
        btnPill.setOnClickListener(this);
        btnNotes.setOnClickListener(this);
        btnPeriodLog.setOnClickListener(this);

        objTodayBean=new TodayBean();
        objTodayScreenBL=new TodayScreenBL();

        objTodayBean.setUserID(userID);
        objTodayBean.setCycleID(cycleID);
        objTodayBean.setNotes("");
        objTodayBean.setOvulation("");
        objTodayBean.setMood("");
        objTodayBean.setBody("");
        objTodayBean.setCervical("");
        objTodayBean.setPill("");
        objTodayBean.setSexual("");
        objTodayBean.setSpotting("No");
        objTodayBean.setTemperature("");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        popupSize();
    }

    private void popupSize(){
        Display display = getWindowManager().getDefaultDisplay();

        int width = display.getWidth();
        int height = display.getHeight();

        // System.out.println("width" + width + "height" + height);

        if(width>=1000 && height>=1500){
            xx=width;
            yy=650;
        }
        else if(width>=700 && height>=1000)
        {
            xx=width;
            yy=500;
        }
        else
        {
            xx=width;
            yy=400;
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {


            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.today_btn_body:
                startActivityForResult(new Intent(getApplicationContext(), TodayBody.class).putExtra("TodayBean", objTodayBean), 1);
                break;
            case R.id.today_btn_mood:
                startActivityForResult(new Intent(getApplicationContext(), TodayMood.class).putExtra("TodayBean", objTodayBean),1);
                break;
            case R.id.today_btn_temperature:
                startActivityForResult(new Intent(getApplicationContext(), TodayTemperature.class).putExtra("TodayBean", objTodayBean),1);
                break;
            case R.id.today_btn_cervical:
                startActivityForResult(new Intent(getApplicationContext(), TodayCervical.class).putExtra("TodayBean", objTodayBean),1);
                break;
            case R.id.today_btn_ovulation:
                showDialog(TodayScreen.this, "Ovulation Test");
                break;
            case R.id.today_btn_pill:
                showDialog(TodayScreen.this,"Pill Intake");
                break;
            case R.id.today_btn_sexual:
                showDialog(TodayScreen.this,"Sexual Activity");
                break;
            case R.id.today_btn_spotting:
                showDialog(TodayScreen.this,"Spotting");
                break;
            case R.id.today_btn_notes:
                startActivityForResult(new Intent(getApplicationContext(), TodayNotes.class).putExtra("TodayBean", objTodayBean), 1);
                break;
            case R.id.today_btn_period_log:
                startActivityForResult(new Intent(getApplicationContext(), TodayPeriodLog.class).putExtra("TodayBean", objTodayBean), 1);
                break;

        }
    }


    /* popup for no internet */
    private void showDialog(Context context,String title){
        // x -->  X-Cordinate
        // y -->  Y-Cordinate

        final TextView tvMsg,tvTitle;
        ImageButton btnClosePopup,btnsave;
        LinearLayout llPill,llTest,llSex;
        LinearLayout btnSpotting;

        LinearLayout btnOvulationPositive,btnOvulationNegative,btnSexProtected,btnSexUnprotected,btnPillTaken,btnPillLate,btnPillMissed,btnPillDouble;

        final Dialog dialog  = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.layout_common_popup);
        dialog.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.BOTTOM;
        wmlp.width=xx;
        wmlp.height=yy;




       // btnClosePopup = (Button) dialog.findViewById(R.id.popup_cancel);
        btnsave= (ImageButton) dialog.findViewById(R.id.popup_add);
        tvMsg= (TextView) dialog.findViewById(R.id.popup_message);
        tvTitle= (TextView) dialog.findViewById(R.id.popup_title);
        btnSpotting= (LinearLayout) dialog.findViewById(R.id.popup_spotting);
        llPill= (LinearLayout) dialog.findViewById(R.id.ll_pill);
        llTest= (LinearLayout) dialog.findViewById(R.id.ll_test);
        llSex= (LinearLayout) dialog.findViewById(R.id.ll_sex);
        btnOvulationPositive= (LinearLayout) dialog.findViewById(R.id.btn_ovulation_positive);
        btnOvulationNegative= (LinearLayout) dialog.findViewById(R.id.btn_ovulation_negative);

        btnSexProtected= (LinearLayout) dialog.findViewById(R.id.btn_sex_protected);
        btnSexUnprotected= (LinearLayout) dialog.findViewById(R.id.btn_sex_unprotected);

        btnPillTaken= (LinearLayout) dialog.findViewById(R.id.btn_pill_taken);
        btnPillDouble= (LinearLayout) dialog.findViewById(R.id.btn_pill_double);
        btnPillMissed= (LinearLayout) dialog.findViewById(R.id.btn_pill_missed);
        btnPillLate= (LinearLayout) dialog.findViewById(R.id.btn_pill_late);

        if(title.equalsIgnoreCase("Spotting"))
            btnSpotting.setVisibility(View.VISIBLE);
        else if(title.equalsIgnoreCase("Pill Intake"))
            llPill.setVisibility(View.VISIBLE);
        else if(title.equalsIgnoreCase("Ovulation Test"))
            llTest.setVisibility(View.VISIBLE);
        else if(title.equalsIgnoreCase("Sexual Activity"))
            llSex.setVisibility(View.VISIBLE);


        btnOvulationPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Ovulation Positive",Toast.LENGTH_SHORT).show();
                objTodayBean.setOvulation("Positive");
            }
        });

        btnOvulationNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Ovulation Negative",Toast.LENGTH_SHORT).show();
                objTodayBean.setOvulation("Negative");
            }
        });

        btnSexProtected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Protected",Toast.LENGTH_SHORT).show();
                objTodayBean.setSexual("Protected");
            }
        });

        btnSexUnprotected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Unprotected",Toast.LENGTH_SHORT).show();
                objTodayBean.setSexual("Unprotected");
            }
        });

        btnPillTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pill Taken",Toast.LENGTH_SHORT).show();
                objTodayBean.setPill("Taken");
            }
        });

        btnPillMissed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pill Missed",Toast.LENGTH_SHORT).show();
                objTodayBean.setPill("Missed");
            }
        });

        btnPillDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pill Double",Toast.LENGTH_SHORT).show();
                objTodayBean.setPill("Double");
            }
        });

        btnPillLate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pill Late",Toast.LENGTH_SHORT).show();
                objTodayBean.setPill("Late");
            }
        });

        btnSpotting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Spotting",Toast.LENGTH_SHORT).show();
                objTodayBean.setSpotting("Yes");
            }
        });




        tvTitle.setText(title);

        btnsave.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {


                                           dialog.dismiss();
                                           //finish();
                                       }
                                   }

        );


        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            objTodayBean= (TodayBean) data.getSerializableExtra("TodayBean");

        }
    }

    private class SendLog extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objTodayScreenBL.setPeriodLog(objTodayBean);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                if(Constant.WS_RESPONSE_SUCCESS.equalsIgnoreCase(s)){
                    Toast.makeText(getApplicationContext(),"Period Log Saved",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }
}
