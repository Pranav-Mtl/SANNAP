package com.example.admin.sannap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.sannap.BE.SignupBE;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PeriodDatePicker extends AppCompatActivity implements View.OnClickListener {

    private TextView datePickerShowDialogButton;

    private int hour;

    private int minute;

    static final int TIME_DIALOG_ID = 999;

    MaterialCalendarView mt;

    ImageButton btnOK;

    Intent intent;

    SignupBE objSignupBE;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_date_picker);
        initialize();

    }

    private void initialize(){
        mt= (MaterialCalendarView) findViewById(R.id.calendarView);

        btnOK= (ImageButton) findViewById(R.id.btn_period_date);

        intent=getIntent();
        objSignupBE= (SignupBE) intent.getSerializableExtra("SignupBE");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnOK.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {


            Toast.makeText(PeriodDatePicker.this,mt.getSelectedDate()+"", Toast.LENGTH_SHORT).show();

            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_period_date:
                if(mt.getSelectedDate()!=null){
                    objSignupBE.setPeriodDate(mt.getSelectedDate().toString().replace("[", "").replace("]", "").replace("CalendarDay","").replace("{", "").replace("}",""));
                    intent.putExtra("SignupBE",objSignupBE);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                else
                   Toast.makeText(getApplicationContext(),"Please select date",Toast.LENGTH_SHORT).show();


                break;
        }
    }
}
