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
import com.example.admin.sannap.Constant.Constant;
import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
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

    CustomCalendarView calendarView;
    Calendar currentCalendar;

    ImageButton btnOK;

    Intent intent;

    SignupBE objSignupBE;

    String selectedDate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_date_picker);
        initialize();

        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                try {
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                     selectedDate = df.format(date);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                //Toast.makeText(TodayPeriodLog.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void initialize(){

        btnOK= (ImageButton) findViewById(R.id.btn_period_date);

        intent=getIntent();
        objSignupBE= (SignupBE) intent.getSerializableExtra("SignupBE");

        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

//Initialize calendar with date
        currentCalendar = Calendar.getInstance(Locale.getDefault());

//Show Monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);



//Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

//call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);
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




            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_period_date:
                if(selectedDate!=null){
                    objSignupBE.setPeriodDate(selectedDate);
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
