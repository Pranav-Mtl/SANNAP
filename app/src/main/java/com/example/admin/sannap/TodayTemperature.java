package com.example.admin.sannap;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import com.example.admin.sannap.BE.TodayBean;

public class TodayTemperature extends AppCompatActivity {

    NumberPicker npTemperature,npPoint;

    TodayBean objTodayBean;

    ImageButton btnDone;

    Intent intent;

    String ss[]=new String[]{"32.", "33.", "34.", "35.", "36.", "37.", "38.", "39.", "40.", "41.", "42."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_temperature);

        initialize();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                objTodayBean.setTemperature(ss[npTemperature.getValue()]+npPoint.getValue());
                intent.putExtra("TodayBean", objTodayBean);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void initialize(){
        npTemperature= (NumberPicker) findViewById(R.id.temperature_picker);
        npPoint= (NumberPicker) findViewById(R.id.temperature_point_picker);
        btnDone= (ImageButton) findViewById(R.id.btn_temperature_done);

        npTemperature.setMinValue(0);
        npTemperature.setMaxValue(10);
        npTemperature.setDisplayedValues(ss);

        npPoint.setMinValue(0);
        npPoint.setMaxValue(9);
        npPoint.setValue(5);

        intent=getIntent();

        objTodayBean= (TodayBean) intent.getSerializableExtra("TodayBean");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
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

}
