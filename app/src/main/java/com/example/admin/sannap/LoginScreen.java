package com.example.admin.sannap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.sannap.BL.LoginBL;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener ,GoogleApiClient.OnConnectionFailedListener{

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

    ProgressDialog mProgressDialog;

    LoginButton btnFB;
    CallbackManager callbackManager;

    private static final String TAG = "SignInActivity";


    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    SignInButton signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_screen);

        emailEdt= (EditText) findViewById(R.id.login_email);
        paswdEdt= (EditText) findViewById(R.id.login_password);
        loginBtn= (Button) findViewById(R.id.login_btn);
        btnFB = (LoginButton) findViewById(R.id.fb_login_button);
        btnFB.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);
        /* fb initialization*/

        LoginManager.getInstance().logOut();
        callbackManager = CallbackManager.Factory.create();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        objLoginBL=new LoginBL();
        progressDialog=new ProgressDialog(LoginScreen.this);



        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.healthcare.sannap",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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

        btnFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                Log.v("LoginActivity", response.toString());
                                try {
                                    Log.v("email", object.getString("email"));

                                    if (Util.isInternetConnection(LoginScreen.this))
                                       // new ValidateUserSocial().execute(object.getString("email"), gcmID, deviceId);

                                    LoginManager.getInstance().logOut();


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
                System.out.println("LoginResult" + loginResult);
            }

            @Override
            public void onCancel() {
                // App code
                System.out.println("Cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                exception.printStackTrace();
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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sign_in_button:
                signIn();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

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

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.d("GOOGLE API", acct.getEmail());

            //new ValidateUserSocial().execute(acct.getEmail(),gcmID,deviceId);
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }
    // [END handleSignInResult]

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
}
