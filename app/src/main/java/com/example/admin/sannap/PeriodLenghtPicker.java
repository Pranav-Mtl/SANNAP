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

import com.example.admin.sannap.BE.SignupBE;

public class PeriodLenghtPicker extends AppCompatActivity implements View.OnClickListener {

    NumberPicker npPeriod;

    ImageButton btnOK;

    Intent intent;

    SignupBE objSignupBE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_lenght_picker);
        initialize();


    }

    private void initialize(){
        npPeriod= (NumberPicker) findViewById(R.id.period_picker);
        btnOK= (ImageButton) findViewById(R.id.btn_period_length);

        npPeriod.setMinValue(1);
        npPeriod.setMaxValue(10);
        npPeriod.setValue(4);

        btnOK.setOnClickListener(this);


        intent=getIntent();
        objSignupBE= (SignupBE) intent.getSerializableExtra("SignupBE");


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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_period_length:
                objSignupBE.setPeriodLength(npPeriod.getValue()+"");
                intent.putExtra("SignupBE",objSignupBE);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
