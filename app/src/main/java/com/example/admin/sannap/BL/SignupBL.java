package com.example.admin.sannap.BL;

import android.content.Context;

import com.example.admin.sannap.BE.SignupBE;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 12-01-2016.
 */
public class SignupBL {

    SignupBE objSignupBE;
    Context mContext;
    public String signUpRecord(Context context,SignupBE signupBE) {

            objSignupBE=signupBE;
        mContext=context;
            String result = fetRecord();
            String finalvalue = validate(result);



        return finalvalue;

    }

    private String fetRecord() {
        String text = null;

        try
        {
            String userData="1:period:"+objSignupBE.getPeriodLength()+",2:cycle_length:"+objSignupBE.getPeriodLength()+",3:last_period:"+objSignupBE.getPeriodDate()+",4:age:"+objSignupBE.getAge()+",5:research:"+objSignupBE.getResearch()+",6:pms:"+objSignupBE.getPms();
            //String userData="1-period-"+objSignupBE.getPeriodLength()+",2-pms-"+objSignupBE.getPms()+",3-cycle_length-"+objSignupBE.getPeriodLength()+",4-last_period-"+objSignupBE.getPeriodDate()+",5-research-"+objSignupBE.getResearch()+",6-age-"+objSignupBE.getAge();
            ////http://oneclickwash.com/webservices/signup.php?mobile=&email=&city=&address=&password=&gcm_id=&otp=
            String URL="name="+objSignupBE.getName()+"&email="+objSignupBE.getEmail()+"&password="+objSignupBE.getPassword()+"&gcm_id="+objSignupBE.getGcmID()+"&device_id="+objSignupBE.getDeviceID()+"&user_data="+userData+"&age="+objSignupBE.getAge();
            text= RestFullWS.serverRequest(URL, Constant.WS_SIGNUP);

        }
        catch (Exception e)
        {

        }
        return text;
    }

    public String validate(String strValue)    {
        JSONParser jsonP=new JSONParser();
        String status="";
        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;
            JSONObject jsonObject=(JSONObject)jsonP.parse(jsonArrayObject.get(0).toString());
            status=jsonObject.get("result").toString();

            if(status.equalsIgnoreCase(Constant.WS_RESPONSE_SUCCESS)){
                String userID=jsonObject.get("user_id").toString();
                Util.setSharedPrefrenceValue(mContext,Constant.PREFS_NAME,Constant.SP_LOGIN_ID,userID);
            }




        } catch (Exception e) {


        }
        return status;
    }
}
