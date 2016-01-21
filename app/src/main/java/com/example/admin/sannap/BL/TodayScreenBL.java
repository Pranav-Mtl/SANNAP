package com.example.admin.sannap.BL;

import com.example.admin.sannap.BE.TodayBean;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 21-01-2016.
 */
public class TodayScreenBL {

    TodayBean objTodayBean;
    public String setPeriodLog(TodayBean todayBean){
        objTodayBean=todayBean;
        String result=callWS();
        String status=validate(result);
        return status;
    }

    private String callWS() {
        String text = null;
        try
        {
            String URL="";
            text= RestFullWS.serverRequest(URL, Constant.WS_SUBSCRIPTION_DETAIL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return text;
    }

    private String validate(String strValue)    {

        String text="";
        JSONParser jsonP=new JSONParser();

        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;

                JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(0).toString());
                text=jsonObject.get("result").toString();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return text;

    }
}
