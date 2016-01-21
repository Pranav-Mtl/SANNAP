package com.example.admin.sannap.BL;

import android.content.Context;

import com.example.admin.sannap.BE.HomeScreenBE;
import com.example.admin.sannap.Configuration.Util;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.HomeScreen;
import com.example.admin.sannap.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 13-01-2016.
 */

public class HomeScreenBL {

    HomeScreenBE objHomeScreenBE;
    public String getData(String userID,HomeScreenBE homeScreenBE) {

        objHomeScreenBE=homeScreenBE;
        String result = callWS(userID);
        String finalvalue = validate(result);
        return finalvalue;
    }

    private String callWS(String userID) {
        String text = null;

        try
        {
            String URL="user_id="+userID;
            text= RestFullWS.serverRequest(URL, Constant.WS_HOMESCREEN);

        }
        catch (Exception e)
        {

        }
        return text;
    }

    private String validate(String strValue)    {
        JSONParser jsonP=new JSONParser();
        String status="";
        String data,currentCycle,NextCycle;
        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;
            JSONObject jsonObject=(JSONObject)jsonP.parse(jsonArrayObject.get(0).toString());
            data=jsonObject.get("user_data").toString();
            currentCycle=jsonObject.get("current_cycle").toString();
            NextCycle=jsonObject.get("upcoming_cycle").toString();

            validateData(data);
            validateCycle(currentCycle);
            validateNextCycle((NextCycle));


        } catch (Exception e) {


        }
        return status;
    }

    /*parse data*/

    private void validateData(String strValue)    {
        JSONParser jsonP=new JSONParser();

        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;
            JSONObject jsonObject=(JSONObject)jsonP.parse(jsonArrayObject.get(0).toString());
            objHomeScreenBE.setName(jsonObject.get("name").toString());
            objHomeScreenBE.setEmail(jsonObject.get("email").toString());
            objHomeScreenBE.setAge(jsonObject.get("age").toString());
            objHomeScreenBE.setPhone(jsonObject.get("phone").toString());
            objHomeScreenBE.setAddress(jsonObject.get("address").toString());
            objHomeScreenBE.setCity(jsonObject.get("city").toString());
            objHomeScreenBE.setZip(jsonObject.get("zip").toString());
            objHomeScreenBE.setSubscribe(jsonObject.get("subscribe").toString());


        } catch (Exception e) {


        }

    }

    /*parse current cycle*/

    private void validateCycle(String strValue)    {
        JSONParser jsonP=new JSONParser();

        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;
            JSONObject jsonObject=(JSONObject)jsonP.parse(jsonArrayObject.get(0).toString());
            objHomeScreenBE.setPeriod(Integer.valueOf(jsonObject.get("period").toString()));
            objHomeScreenBE.setPeriodBleak(Integer.valueOf(jsonObject.get("bleak_period").toString()));
            objHomeScreenBE.setFertile(Integer.valueOf(jsonObject.get("fertile_period").toString()));
            objHomeScreenBE.setAfterFertile(Integer.valueOf(jsonObject.get("after_fertile").toString()));
            objHomeScreenBE.setPms(Integer.valueOf(jsonObject.get("pms").toString()));
            objHomeScreenBE.setCycleLength(Integer.valueOf(jsonObject.get("cycle_length").toString()));
            objHomeScreenBE.setFirstDay(jsonObject.get("first_day").toString());






        } catch (Exception e) {


        }
    }


    /*parse next cycle*/

    private void validateNextCycle(String strValue)    {
        JSONParser jsonP=new JSONParser();

        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;
            JSONObject jsonObject=(JSONObject)jsonP.parse(jsonArrayObject.get(0).toString());

            objHomeScreenBE.setNextPeriod(Integer.valueOf(jsonObject.get("period").toString()));
            objHomeScreenBE.setNextPeriodBleak(Integer.valueOf(jsonObject.get("bleak_period").toString()));
            objHomeScreenBE.setNextFertile(Integer.valueOf(jsonObject.get("fertile_period").toString()));
            objHomeScreenBE.setNextAfterFertile(Integer.valueOf(jsonObject.get("after_fertile").toString()));
            objHomeScreenBE.setNextPms(Integer.valueOf(jsonObject.get("pms").toString()));
            objHomeScreenBE.setNextCycleLength(Integer.valueOf(jsonObject.get("cycle_length").toString()));
            objHomeScreenBE.setNextFirstDay(jsonObject.get("first_day").toString());


        } catch (Exception e) {


        }

    }

}
