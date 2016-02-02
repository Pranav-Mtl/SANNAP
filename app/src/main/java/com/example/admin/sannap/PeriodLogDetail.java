package com.example.admin.sannap;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.admin.sannap.Constant.Constant;


public class PeriodLogDetail extends AppCompatActivity {

    TextView tvDate,tvSpotting,tvCervical,tvTemperature,tvOvulation,tvBody,tvMood,tvNotes,tvSexual,tvPill;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_log_detail);
        initialize();
    }


    private void initialize(){
        tvDate= (TextView) findViewById(R.id.log_detail_date);
        tvSpotting= (TextView) findViewById(R.id.log_detail_spotting);

        tvCervical= (TextView) findViewById(R.id.log_detail_cervical);
        tvTemperature= (TextView) findViewById(R.id.log_detail_temperature);
        tvOvulation= (TextView) findViewById(R.id.log_detail_ovulation);
        tvBody= (TextView) findViewById(R.id.log_detail_body);
        tvMood= (TextView) findViewById(R.id.log_detail_mood);
        tvNotes= (TextView) findViewById(R.id.log_detail_notes);

        tvSexual= (TextView) findViewById(R.id.log_detail_sexual);
        tvPill= (TextView) findViewById(R.id.log_detail_pill);

        position=getIntent().getIntExtra("POSITION",-1);

        if(position!=-1) {

            tvDate.setText("Date: "+Constant.cycleDate[position]);
            tvSpotting.setText(Constant.spotting[position]);

            tvCervical.setText(Constant.cervical_mucus[position]);
            tvTemperature.setText(Constant.body_temp[position]);

            tvOvulation.setText(Constant.ovulation_test[position]);
            tvBody.setText(Constant.body[position]);

            tvMood.setText(Constant.mood[position]);
            tvNotes.setText(Constant.notes[position]);

            tvSexual.setText(Constant.sexual_activity[position]);
            tvPill.setText(Constant.pill_intake[position]);

        }

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
