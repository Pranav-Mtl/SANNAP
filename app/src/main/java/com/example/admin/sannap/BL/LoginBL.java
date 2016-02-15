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
 * Created by appslure on 13-01-2016.
 */
public class LoginBL {

    Context mContext;
    public String login(Context context,String email,String password,String gcm) {


        mContext=context;
        String result = fetRecord(email,password,gcm);
        String finalvalue = validate(result);

        return finalvalue;

    }

    private String fetRecord(String email,String password,String gcm) {
        String text = null;

        try
        {

            String URL="email="+email+"&password="+password+"&gcm_id="+gcm;
            text= RestFullWS.serverRequest(URL, Constant.WS_SIGNIN);

        }
        catch (Exception e)
        {

        }
        return text;
    }

    private String validate(String strValue)    {
        JSONParser jsonP=new JSONParser();
        String status="";
        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;
            JSONObject jsonObject=(JSONObject)jsonP.parse(jsonArrayObject.get(0).toString());
            status=jsonObject.get("result").toString();
            if(status.equalsIgnoreCase(Constant.WS_RESPONSE_SUCCESS)){
                String userID=jsonObject.get("user_id").toString();
                Util.setSharedPrefrenceValue(mContext, Constant.PREFS_NAME, Constant.SP_LOGIN_ID, userID);
                String id=Util.getSharedPrefrenceValue(mContext,Constant.SP_LOGIN_ID);
                System.out.println("getting user id--"+id);
            }




        } catch (Exception e) {


        }
        return status;
    }
}
