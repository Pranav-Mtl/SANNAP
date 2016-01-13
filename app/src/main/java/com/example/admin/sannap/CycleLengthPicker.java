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

public class CycleLengthPicker extends AppCompatActivity implements View.OnClickListener {

    NumberPicker npCycle;

    ImageButton btnOK;

    Intent intent;

    SignupBE objSignupBE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_length_picker);
        initialize();


    }

    private void initialize(){
        npCycle= (NumberPicker) findViewById(R.id.cycle_picker);
        btnOK= (ImageButton) findViewById(R.id.btn_cycle_length);

        npCycle.setMinValue(10);
        npCycle.setMaxValue(90);
        npCycle.setValue(30);

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
            case R.id.btn_cycle_length:
                objSignupBE.setPeriodCycle(npCycle.getValue() + "");
                intent.putExtra("SignupBE",objSignupBE);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
