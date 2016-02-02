package com.example.admin.sannap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.admin.sannap.BE.TodayBean;
import com.example.admin.sannap.BL.TodayPeriodLogBL;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;
import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TodayPeriodLog extends AppCompatActivity {

    ImageButton btnOK;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    TodayPeriodLogBL objTodayPeriodLogBL;

    CustomCalendarView calendarView;
    Calendar currentCalendar;

    String userID;

    String cycleID;

    TodayBean objTodayBean;
    Intent intent;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_period_log);

        initialize();



        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("TodayBean", objTodayBean);
                setResult(RESULT_OK, intent);
                finish();
            }
        });



//Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                try {
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    String selectedDate = sdf.format(date);
                    Date d1 = sdf.parse(selectedDate);
                    if (Constant.cycleDate != null) {
                        for (int i = 0; i < Constant.cycleDate.length; i++) {
                            Date d2 = sdf.parse(Constant.cycleDate[i]);
                            if(d2.compareTo(d1)==0){
                                startActivity(new Intent(getApplicationContext(), PeriodLogDetail.class).putExtra("POSITION", i));
                               // Toast.makeText(TodayPeriodLog.this, df.format(date)+"Saved Date", Toast.LENGTH_SHORT).show();
                            }
                        }
                        //Toast.makeText(TodayPeriodLog.this, df.format(date), Toast.LENGTH_SHORT).show();
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                //Toast.makeText(TodayPeriodLog.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });


        if(Util.isInternetConnection(TodayPeriodLog.this)){
            new GetData().execute(userID,objTodayBean.getCycleID());
        }
    }

    private void initialize(){
        btnOK= (ImageButton) findViewById(R.id.log_ok);
        objTodayPeriodLogBL=new TodayPeriodLogBL();

        userID= Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_LOGIN_ID);
        intent=getIntent();
        objTodayBean= (TodayBean) intent.getSerializableExtra("TodayBean");
        progressDialog=new ProgressDialog(TodayPeriodLog.this);

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



    private class DaysDecorator implements DayDecorator {
        @Override
        public void decorate(DayView dayView) {

            try {
                String date = sdf.format(dayView.getDate());
                Date d1 = sdf.parse(date);
                for(int i=0;i< Constant.cycleDate.length;i++){
                    Date d2 = sdf.parse(Constant.cycleDate[i]);
                    if(d2.compareTo(d1)==0){
                        dayView.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                    else
                    {

                        //dayView.setClickable(false);
                    }

                }

            }catch (Exception e){

            }

          /*  if(!isPastDay(dayView.getDate())){
                dayView.setBackgroundColor(getResources().getColor(R.color.light_bg));
                Log.d("LOG", dayView.getDate() + "");

            }*/
        }
    }


    private boolean isPastDay(Date date) {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // and get that as a Date
        Date today = c.getTime();



        // test your condition, if Date specified is before today
        if (date.before(today)) {
            return true;
        }
        return false;
    }


    private class GetData extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {

            String result=objTodayPeriodLogBL.getLogData(params[0], params[1]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if(Constant.WS_RESPONSE_SUCCESS.equalsIgnoreCase(s)) {
                    List decorators = new ArrayList<>();
                    decorators.add(new DaysDecorator());
                    calendarView.setDecorators(decorators);
                    calendarView.refreshCalendar(currentCalendar);
                }
                else
                    Toast.makeText(getApplicationContext(),"No Log Found",Toast.LENGTH_SHORT).show();
            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }
}
