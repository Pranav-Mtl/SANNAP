package com.example.admin.sannap;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.admin.sannap.BE.TodayBean;

public class TodayNotes extends AppCompatActivity {

    ImageButton btnOK;
    EditText tvNotes;

    TodayBean objTodayBean;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_notes);
        initialize();

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvNotes.length()>0){
                    objTodayBean.setNotes(tvNotes.getText().toString());
                }
                else {
                    objTodayBean.setNotes("");
                }
                intent.putExtra("TodayBean",objTodayBean);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void initialize(){
        btnOK= (ImageButton) findViewById(R.id.notes_ok);
        tvNotes= (EditText) findViewById(R.id.tv_today_notes);

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
