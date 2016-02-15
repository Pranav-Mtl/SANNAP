package com.example.admin.sannap.BL;

import com.example.admin.sannap.BE.HomeScreenBE;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 15-01-2016.
 */
public class SubscriptionBL
{

    String userId;
    public String getSubscriptionData(String userId) {

        this.userId=userId;
        String result = callWS();
         validate(result);
        return "";
    }

    private String callWS() {
        String text = null;

        try
        {  String URL="user_id="+userId;
            text= RestFullWS.serverRequest(URL, Constant.WS_SUBSCRIPTION);

        }
        catch (Exception e)
        {

        }
        return text;
    }

    private void validate(String strValue)    {
        JSONParser jsonP=new JSONParser();

        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;

            Constant.modelID=new String[jsonArrayObject.size()];
            Constant.modelName=new String[jsonArrayObject.size()];
            Constant.modelOriginal=new String[jsonArrayObject.size()];
            Constant.modelPrice=new String[jsonArrayObject.size()];

            for (int i=0;i<jsonArrayObject.size();i++) {
                JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(i).toString());
                Constant.modelID[i]=jsonObject.get("model_id").toString();
                Constant.modelName[i]=jsonObject.get("item_allowed").toString();
                Constant.modelPrice[i]=jsonObject.get("discount_price").toString();
            }



        } catch (Exception e) {


        }

    }
}
