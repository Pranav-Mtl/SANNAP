package com.example.admin.sannap.BL;

import android.support.v4.print.PrintHelper;

import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 15-01-2016.
 */
public class SubscriptionCheckoutBL {

    public String setSubscriptionData(String phone,String address,String city,String zip,String userID,String modelID) {

        String result = callWS(phone,address,city,zip,userID,modelID);
        String status=validate(result);
        return status;
    }

    private String callWS(String phone,String address,String city,String zip,String userID,String modelID) {
        String text = null;
        //http://appslure.com/sannap/android/user_subscribe.php?phone=98989898&address=ffff&city=fff&zip=5555&user_id=11&model_id=5
        try
        {  String URL="phone="+phone+"&address="+address+"&city="+city+"&zip="+zip+"&user_id="+userID+"&model_id="+modelID;
            text= RestFullWS.serverRequest(URL, Constant.WS_SUBSCRIPTION_CHECKOUT);

        }
        catch (Exception e)
        {

        }
        return text;
    }

    private String validate(String strValue)    {
        String status="";
        JSONParser jsonP=new JSONParser();

        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;



            for (int i=0;i<jsonArrayObject.size();i++) {
                JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(i).toString());
                status=jsonObject.get("result").toString();

            }



        } catch (Exception e) {


        }
        return status;
    }
}
