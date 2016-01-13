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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.sannap.BE.SignupBE;
import com.example.admin.sannap.BL.SignupBL;
import com.example.admin.sannap.Constant.Constant;

import java.util.regex.Pattern;

public class SignUpScreen extends AppCompatActivity {


    EditText emailEdt,passwordEdt,confirmPaswdEdt,nameEdt;
    Button signUp,logIN;
    boolean flag=true;

    SignupBE objSignupBE;
    SignupBL objSignupBL;

    String email,password,confirmPaswd,Strname;
    public final Pattern EMAIL_ADDRESS_PATTERN=Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}"+
                    "@"+
                    "[a-zA-Z0-9][a-zA-Z0-9-]{1,65}"+
                    "("+
                    "."+
                    "[a-zA-Z0-9][]a-zA-Z0-9-]{1,25}"+
                    ")+"
    );

    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        emailEdt= (EditText) findViewById(R.id.email);
        nameEdt= (EditText) findViewById(R.id.name);
        passwordEdt= (EditText) findViewById(R.id.password);
        confirmPaswdEdt= (EditText) findViewById(R.id.confirm_password);
        signUp= (Button) findViewById(R.id.signup_btn);
        logIN= (Button) findViewById(R.id.login_btn);

        objSignupBL=new SignupBL();
        progressDialog=new ProgressDialog(SignUpScreen.this);

        objSignupBE= (SignupBE) getIntent().getSerializableExtra("SignupBE");

        Log.d("Tag",objSignupBE.getAge()+","+objSignupBE.getPeriodCycle()+","+objSignupBE.getPms()+","+objSignupBE.getPeriodLength()+","+objSignupBE.getPeriodDate());

        logIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginScreen.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=emailEdt.getText().toString();
                password=passwordEdt.getText().toString();
                confirmPaswd=confirmPaswdEdt.getText().toString();
                Strname=nameEdt.getText().toString();

               if(checkAllField())
               {
                   if(password.equals(confirmPaswd)) {
                       objSignupBE.setEmail(email);
                       objSignupBE.setPassword(password);
                       objSignupBE.setName(Strname);
                       objSignupBE.setGcmID("");
                       objSignupBE.setDeviceID("");

                       new SetData().execute();
                       /*Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                       startActivity(intent);*/
                   }

                   else
                   {
                       Toast.makeText(getApplicationContext(),"password doest not match",Toast.LENGTH_SHORT).show();
                   }

               }


            }
        });

    }

    public boolean checkAllField()
    {
        if (email.length()==0)
        {
            emailEdt.setError("Mandatory Field");
            flag = false;
        }

        if(Strname.length()==0){
            nameEdt.setError("Mandatory Field");
            flag = false;
        }



        if(password.length()==0)
        {
            passwordEdt.setError("Mandatory Field");
            flag=false;
        }



        if(confirmPaswd.length()==0)
        {
            confirmPaswdEdt.setError("Mandatory Field");
            flag=false;
        }


        if(!checkEmail(email))
        {
            emailEdt.setError("Enter valid email address");
            flag=false;
        }

        return flag;
    }


    public boolean checkEmail(String email)
    {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }


    private class SetData extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result=objSignupBL.signUpRecord(getApplicationContext(),objSignupBE);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                Log.d("Signup Result-->",s);
                if(Constant.WS_RESPONSE_SUCCESS.equalsIgnoreCase(s)){
                    startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                }
            }catch (NullPointerException e){

            }catch (Exception e){

            }
            finally {
                progressDialog.dismiss();
            }
        }
    }

}
