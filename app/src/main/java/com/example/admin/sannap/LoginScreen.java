package com.example.admin.sannap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.sannap.BL.LoginBL;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;

import java.util.regex.Pattern;

public class LoginScreen extends AppCompatActivity {

    EditText emailEdt,paswdEdt;
    Button loginBtn,signup;
    String email,password;
    boolean flag=true;
    public final Pattern EMAIL_ADDRESS_PATTERN=Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}"+
                    "@"+
                    "[a-zA-Z0-9][a-zA-Z0-9-]{1,65}"+
                    "("+
                    "."+
                    "[a-zA-Z0-9][]a-zA-Z0-9-]{1,25}"+
                    ")+"
    );

    LoginBL objLoginBL;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        emailEdt= (EditText) findViewById(R.id.login_email);
        paswdEdt= (EditText) findViewById(R.id.login_password);
        loginBtn= (Button) findViewById(R.id.login_btn);

        objLoginBL=new LoginBL();
        progressDialog=new ProgressDialog(LoginScreen.this);
        /*signup= (Button) findViewById(R.id.signup_btn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpScreen.class));
            }
        });
*/
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=emailEdt.getText().toString();
                password=paswdEdt.getText().toString();

                if(checkAllfield())
                {
                    if(Util.isInternetConnection(LoginScreen.this))
                        new ValidateLogin().execute(email,password,"gcm");
                }

            }
        });

    }

    public  boolean checkAllfield()
    {
        flag=true;

        if(email.length()==0)
        {
            emailEdt.setError("Mandatory Field");
            flag =false;
        }

        if(!checkEmail(email))
        {
           emailEdt.setError("Enter valid email id");
            flag=false;
        }

        if(password.length()==0)
        {
            paswdEdt.setError("Mandatory Field");
            flag=false;
        }

        return flag;
    }

    public boolean checkEmail(String email)
    {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    private class ValidateLogin extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objLoginBL.login(getApplicationContext(),params[0],params[1],params[2]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                if(Constant.WS_RESPONSE_SUCCESS.equalsIgnoreCase(s)){
                    startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                }
            }catch (NullPointerException e){

            }catch (Exception e){

            }finally {
                progressDialog.dismiss();
            }
        }
    }
}
