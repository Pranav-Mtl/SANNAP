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

public class TodayCervical extends AppCompatActivity {

    NumberPicker npCervical;

    TodayBean objTodayBean;

    ImageButton btnDone;

    Intent intent;

    String ss[]=new String[]{"Dry", "Sticky", "Creamy", "Watery", "Egg White"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_cervical);
        initialize();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int click=npCervical.getValue();
                objTodayBean.setCervical(ss[click]);
                intent.putExtra("TodayBean", objTodayBean);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void initialize(){
        npCervical= (NumberPicker) findViewById(R.id.cervical_picker);
        btnDone= (ImageButton) findViewById(R.id.btn_body_carnival);

        npCervical.setMinValue(0);
        npCervical.setMaxValue(4);
        npCervical.setDisplayedValues(ss);

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
