package com.example.admin.sannap;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class TodayScreen extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout btnBody,btnMood,btnTemperature,btnCervical,btnSpotting,btnOvulation,btnSexual,btnPill;

    int xx,yy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_screen);
        initialize();

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



        btnBody.setOnClickListener(this);
        btnMood.setOnClickListener(this);
        btnTemperature.setOnClickListener(this);
        btnCervical.setOnClickListener(this);
        btnSpotting.setOnClickListener(this);
        btnOvulation.setOnClickListener(this);
        btnSexual.setOnClickListener(this);
        btnPill.setOnClickListener(this);

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
                startActivity(new Intent(getApplicationContext(),TodayBody.class));
                break;
            case R.id.today_btn_mood:
                startActivity(new Intent(getApplicationContext(),TodayMood.class));
                break;
            case R.id.today_btn_temperature:
                startActivity(new Intent(getApplicationContext(),TodayTemperature.class));
                break;
            case R.id.today_btn_cervical:
                startActivity(new Intent(getApplicationContext(),TodayCervical.class));
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

        if(title.equalsIgnoreCase("Spotting"))
            btnSpotting.setVisibility(View.VISIBLE);
        else if(title.equalsIgnoreCase("Pill Intake"))
            llPill.setVisibility(View.VISIBLE);
        else if(title.equalsIgnoreCase("Ovulation Test"))
            llTest.setVisibility(View.VISIBLE);
        else if(title.equalsIgnoreCase("Sexual Activity"))
            llSex.setVisibility(View.VISIBLE);


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
}
