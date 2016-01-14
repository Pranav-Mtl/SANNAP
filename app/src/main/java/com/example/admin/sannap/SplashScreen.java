package com.example.admin.sannap;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;

public class SplashScreen extends AppCompatActivity {

    String userID;

    int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        userID= Util.getSharedPrefrenceValue(SplashScreen.this, Constant.SP_LOGIN_ID);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if(userID==null) {
                    startActivity(new Intent(getApplicationContext(), FirstScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
                else {
                    startActivity(new Intent(getApplicationContext(), HomeScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
            }
        }, SPLASH_TIME);
    }
}
