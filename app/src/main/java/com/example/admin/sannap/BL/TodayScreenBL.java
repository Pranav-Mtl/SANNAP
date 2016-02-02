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

        //http://appslure.com/sannap/android/cycle_data.php?user_id=2&cycle_id=1&spotting=Y&cervical_mucus=gg&body_temp=65.3&ovulation_test=fertile&body=888&mood=22,ff,gghf,ghh&notes=test&sexual_activity=protected&pill_intake=Y

        try
        {
            String URL="user_id="+objTodayBean.getUserID()+"&cycle_id="+objTodayBean.getCycleID()+"&spotting="+objTodayBean.getSpotting()+"&cervical_mucus="+objTodayBean.getCervical()+"&body_temp="+objTodayBean.getTemperature()+"&ovulation_test="+objTodayBean.getOvulation()+"&body="+objTodayBean.getBody()+"&mood="+objTodayBean.getMood()+"&notes="+objTodayBean.getNotes()+"&sexual_activity="+objTodayBean.getSexual()+"&pill_intake="+objTodayBean.getPill();
            text= RestFullWS.serverRequest(URL, Constant.WS_TODAY);
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
