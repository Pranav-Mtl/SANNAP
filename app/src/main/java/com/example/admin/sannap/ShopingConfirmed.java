package com.example.admin.sannap;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.sannap.BE.HomeScreenBE;
import com.example.admin.sannap.BE.ShopingConfirmedBE;
import com.example.admin.sannap.BL.HomeScreenBL;
import com.example.admin.sannap.BL.ShopingConfirmedBL;
import com.example.admin.sannap.BL.SubscriptionCheckoutBL;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;

public class ShopingConfirmed extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etEmail,etPhone,etAddress,etCity,etZip;

    Button btnDone;

    TextView tvsubTotal,tvTax,tvTotal;



    String strPhone,strAddress,strCity,strZip,strName,strEmail;

    HomeScreenBL objHomeScreenBL;
    HomeScreenBE objHomeScreenBE;

    ShopingConfirmedBE objShopingConfirmedBE;
    ShopingConfirmedBL objShopingConfirmedBL;

    ProgressDialog progressDialog;

    String totalPrice;

    double taxPrice,intTotalPrice;

    int xx,yy;



    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_confirmed);

        initialize();

        if(Util.isInternetConnection(ShopingConfirmed.this))
            new GetData().execute(userID);
    }

    private void initialize(){

        etName= (EditText) findViewById(R.id.checkout_name);
        etEmail= (EditText) findViewById(R.id.checkout_email);
        etPhone= (EditText) findViewById(R.id.checkout_phone);
        etAddress= (EditText) findViewById(R.id.checkout_address);
        etCity= (EditText) findViewById(R.id.checkout_city);
        etZip= (EditText) findViewById(R.id.checkout_zip);
        btnDone= (Button) findViewById(R.id.checkout_done);

        tvsubTotal= (TextView) findViewById(R.id.shop_checkout_subtotal);
        tvTax= (TextView) findViewById(R.id.shop_checkout_tax);
        tvTotal= (TextView) findViewById(R.id.shop_checkout_grandtotal);

        objShopingConfirmedBE=new ShopingConfirmedBE();
        objShopingConfirmedBL=new ShopingConfirmedBL();

        btnDone.setOnClickListener(this);



        objHomeScreenBE=new HomeScreenBE();
        objHomeScreenBL=new HomeScreenBL();

        progressDialog=new ProgressDialog(ShopingConfirmed.this);

        totalPrice=getIntent().getStringExtra("Price");
        tvsubTotal.setText("₹" + totalPrice);

        double intPrice=Double.valueOf(totalPrice);

        taxPrice=(14 * intPrice)/100;

        tvTax.setText("₹" + Math.ceil(taxPrice)+"");

         intTotalPrice=intPrice+taxPrice;

        tvTotal.setText("₹" + Math.ceil(intTotalPrice)+"");



        userID= Util.getSharedPrefrenceValue(getApplicationContext(), Constant.SP_LOGIN_ID);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        popupSize();
    }


    private void popupSize(){
        Display display = getWindowManager().getDefaultDisplay();

        int width = display.getWidth();
        int height = display.getHeight();

        // System.out.println("width" + width + "height" + height);

        if(width>=1000 && height>=1500){
            xx=700;
            yy=650;
        }
        else if(width>=700 && height>=1000)
        {
            xx=550;
            yy=500;
        }
        else
        {
            xx=450;
            yy=400;
        }

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

        switch (v.getId()) {

            case R.id.checkout_done:

                if(validate()) {
                    objShopingConfirmedBE.setUserID(userID);
                    objShopingConfirmedBE.setBasePrice(totalPrice);
                    objShopingConfirmedBE.setTax(Math.ceil(taxPrice) + "");
                    objShopingConfirmedBE.setGrandTotal(Math.ceil(intTotalPrice) + "");

                    objShopingConfirmedBE.setUserAddress(strAddress);
                    objShopingConfirmedBE.setUserCity(strCity);
                    objShopingConfirmedBE.setUserMobile(strPhone);
                    objShopingConfirmedBE.setUserZip(strZip);
                    objShopingConfirmedBE.setDiscountedPrice("");
                    objShopingConfirmedBE.setPromocode("");
                    objShopingConfirmedBE.setUserName(strName);
                    objShopingConfirmedBE.setUserEmail(strEmail);

                    String item="";

                    for(int i=0;i<Constant.listId.size();i++){
                        if(i==0)
                        item=item+Constant.listId.get(i)+":"+Constant.listQuantity.get(i);
                        else
                            item=item+","+Constant.listId.get(i)+":"+Constant.listQuantity.get(i);

                    }

                    objShopingConfirmedBE.setItemSelected(item);


                    showDialogPayment(ShopingConfirmed.this);
                }

                break;
        }
    }

    private class GetData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objHomeScreenBL.getData(params[0],objHomeScreenBE);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try{


                etName.setText(objHomeScreenBE.getName());
                etEmail.setText(objHomeScreenBE.getEmail());
                etPhone.setText(objHomeScreenBE.getPhone());
                etAddress.setText(objHomeScreenBE.getAddress());
                etCity.setText(objHomeScreenBE.getCity());
                etZip.setText(objHomeScreenBE.getZip());





            }catch (NullPointerException e){

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                progressDialog.dismiss();
            }
        }
    }

    private boolean validate(){
        boolean flag=true;
        strPhone=etPhone.getText().toString();
        strAddress=etAddress.getText().toString();
        strCity=etCity.getText().toString();
        strZip=etZip.getText().toString();
        strEmail=etEmail.getText().toString();
        strName=etName.getText().toString();

        if(strPhone.trim().length()==0){
            flag=false;
            etPhone.setError("Required");

        }

        if(strName.trim().length()==0){
            flag=false;
            etName.setError("Required");
        }

        if(strEmail.trim().length()==0){
            flag=false;
            etEmail.setError("Required");
        }

        if(strAddress.trim().length()==0){
            flag=false;
            etAddress.setError("Required");
        }

        if(strCity.trim().length()==0){
            flag=false;
            etCity.setError("Required");
        }

        if(strZip.trim().length()==0){
            flag=false;
            etZip.setError("Required");
        }

        return flag;
    }


    /* popup for add referal */
    private void showDialogPayment(Context context){
        // x -->  X-Cordinate
        // y -->  Y-Cordinate

        final TextView tvTitle;
        Button btnClosePopup,btnsave;
        RadioGroup rgPayment;
        final RadioButton rbCash,rbOnline;

        final Dialog dialog  = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.payment_popup);
        dialog.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.CENTER;
        wmlp.width=xx;
        wmlp.height=yy;




        btnClosePopup = (Button) dialog.findViewById(R.id.popup_cancel);
        btnsave= (Button) dialog.findViewById(R.id.popup_add);
        rgPayment= (RadioGroup) dialog.findViewById(R.id.group_payment);
        tvTitle= (TextView) dialog.findViewById(R.id.popup_title);
        rbCash= (RadioButton) dialog.findViewById(R.id.payment_cash);
        rbOnline= (RadioButton) dialog.findViewById(R.id.payment_online);

        tvTitle.setText(getResources().getString(R.string._payment_title));
        //tvMsg.setText(getResources().getString(R.string._referral_message));
        btnClosePopup.setText(getResources().getString(R.string._payment_cancel));
        btnsave.setText(getResources().getString(R.string._payment_save));


        btnClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(SellerQuestionExpandable.this,edittext.getText().toString(),Toast.LENGTH_LONG).show();
                dialog.dismiss();
                //finish();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           if(rbCash.isChecked())
                                           {
                                                objShopingConfirmedBE.setUserPayment("Cash");
                                               new SendData().execute();
                                               dialog.dismiss();
                                           }else if(rbOnline.isChecked()){
                                               objShopingConfirmedBE.setUserPayment("Online");
                                               new SendData().execute();
                                               dialog.dismiss();
                                           }


                                       }
                                   }

        );


        dialog.show();
    }

    private class SendData extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setCancelable(false);
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objShopingConfirmedBL.setShopData(objShopingConfirmedBE);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            try{
                if(Constant.WS_RESPONSE_SUCCESS.equalsIgnoreCase(s)){
                    startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                }else
                    Toast.makeText(getApplicationContext(),"Something went wrong. Please try again",Toast.LENGTH_SHORT).show();
            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }

        }
    }


}
