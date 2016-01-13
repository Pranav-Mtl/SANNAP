package com.example.admin.sannap;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.sannap.circularseekbar.CircularSeekBar;


public class HomeScreen extends AppCompatActivity implements CircularSeekBar.OnCircularSeekBarChangeListener {

    ImageView shop,subscription;

    //CircularSlider cs;

    TextView tvPosition,tvPeriod;

    LinearLayout llMiddle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        shop= (ImageView) findViewById(R.id.shop_btn);
        subscription= (ImageView) findViewById(R.id.subscription_btn);
        //cs= (CircularSlider) findViewById(R.id.circular);
        tvPosition= (TextView) findViewById(R.id.circular_position);
        tvPeriod= (TextView) findViewById(R.id.circular_period);
        llMiddle= (LinearLayout) findViewById(R.id.ll_middle);

        CircularSeekBar seekbar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        seekbar.setOnSeekBarChangeListener(this);
        seekbar.getProgress();
        seekbar.setProgress(5);
        seekbar.setMax(30);



        seekbar.setBackgroundResource(R.drawable.ic_home_circle);
        seekbar.setCircleColor(Color.TRANSPARENT);
        seekbar.setCircleProgressColor(Color.TRANSPARENT);
        seekbar.setPointerColor(getResources().getColor(R.color.white));
        seekbar.setPointerHaloColor(getResources().getColor(R.color.red));
        seekbar.setPointerAlpha(getResources().getColor(R.color.red_alpha));
        seekbar.setPointerAlphaOnTouch(getResources().getColor(R.color.red_alpha));

        seekbar.setCircleFillColor(Color.TRANSPARENT);




       /* cs.setOnSliderMovedListener(new CircularSlider.OnSliderMovedListener() {
            @Override
            public void onSliderMoved(double pos) {
                Double d = new Double(pos);
                int i = d.intValue();

                if(i<0)
                {
                    tvPosition.setText("Minus: " + String.format("%.2f",pos));
                }
                else {
                    tvPosition.setText("Plus: " + String.format("%.2f",pos));
                }
            }
        });*/

        llMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TodayScreen.class));
            }
        });


        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ShopScreen.class);
                startActivity(intent);
            }
        });



        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Subscription.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onProgressChanged(CircularSeekBar circularSeekBar, int progress, boolean fromUser) {
        tvPosition.setText("Day: "+progress);

    }

    @Override
    public void onStopTrackingTouch(CircularSeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(CircularSeekBar seekBar) {

    }
}
