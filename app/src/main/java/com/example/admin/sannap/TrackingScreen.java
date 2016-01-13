package com.example.admin.sannap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class TrackingScreen extends AppCompatActivity {

    ToggleButton imageOne,imagetwo,imageThree,imagefour,imagefive,imagesix,imageSeven,imageNine;
    ToggleButton imageEight;
    boolean flag1=true,flag2=true,flag3=true,flag4=true,flag5=true,flag6=true,flag7=true,flag8=true,flag9=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_screen);

        imageOne= (ToggleButton) findViewById(R.id.first);
        imagetwo= (ToggleButton) findViewById(R.id.second);
        imageThree= (ToggleButton) findViewById(R.id.third);
        imagefour= (ToggleButton) findViewById(R.id.forth);
        imagefive= (ToggleButton) findViewById(R.id.fifth);
        imagesix= (ToggleButton) findViewById(R.id.sixth);
        imageSeven= (ToggleButton) findViewById(R.id.seventh);
        imageEight= (ToggleButton) findViewById(R.id.eighth);
        imageNine= (ToggleButton) findViewById(R.id.nine);





    }

}
