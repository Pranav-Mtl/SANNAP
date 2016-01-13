package com.example.admin.sannap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class Notification extends AppCompatActivity {

    ImageView toggleBtn;
    boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toggleBtn= (ImageView) findViewById(R.id.toggle_btn);

        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag) {

                    toggleBtn.setBackgroundResource(R.drawable.toggle_active_img);
                    flag=false;
                }


                else if(!flag)
                {
                    toggleBtn.setBackgroundResource(R.drawable.toggle_inactive_btn);
                    flag=true;
                }



            }
        });

    }

}
