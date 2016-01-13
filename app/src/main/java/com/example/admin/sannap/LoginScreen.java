package com.example.admin.sannap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        emailEdt= (EditText) findViewById(R.id.login_email);
        paswdEdt= (EditText) findViewById(R.id.login_password);
        loginBtn= (Button) findViewById(R.id.login_btn);
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
                    Intent intent=new Intent(getApplicationContext(),HomeScreen.class);
                    startActivity(intent);
                }

            }
        });

    }

    public  boolean checkAllfield()
    {
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

}
