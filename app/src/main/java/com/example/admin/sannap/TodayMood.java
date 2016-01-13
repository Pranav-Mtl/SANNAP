package com.example.admin.sannap;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.admin.sannap.Adapter.TodayBodyAdapter;
import com.example.admin.sannap.Adapter.TodayMoodAdapter;

public class TodayMood extends AppCompatActivity {

    RecyclerView listBooking;
    ImageButton btnBody;

    TodayMoodAdapter objTodayMoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_mood);
        initialize();

        objTodayMoodAdapter=new TodayMoodAdapter(getApplicationContext());
        listBooking.setAdapter(objTodayMoodAdapter);
    }


    private void initialize(){

        listBooking= (RecyclerView) findViewById(R.id.mood_list);


        listBooking.setHasFixedSize(true);

        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listBooking.setLayoutManager(llm);




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
