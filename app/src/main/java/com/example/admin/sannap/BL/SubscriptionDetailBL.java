package com.example.admin.sannap.BL;

import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 15-01-2016.
 */
public class SubscriptionDetailBL {

    public String getSubscriptionDetail(String modelID) {

        String result = callWS(modelID);
        validate(result);
        return "";
    }

    private String callWS(String modelID) {
        String text = null;

        try
        {  String URL="model_id="+modelID;
            text= RestFullWS.serverRequest(URL, Constant.WS_SUBSCRIPTION_DETAIL);

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

            Constant.detailName=new String[jsonArrayObject.size()];
            Constant.detailQuantity=new String[jsonArrayObject.size()];
            Constant.detailUnit=new String[jsonArrayObject.size()];

            for (int i=0;i<jsonArrayObject.size();i++) {
                JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(i).toString());
                Constant.detailName[i]=jsonObject.get("item_name").toString();
                Constant.detailQuantity[i]=jsonObject.get("quantity").toString();
                Constant.detailUnit[i]=jsonObject.get("si_unit").toString();
            }



        } catch (Exception e) {


        }

    }
}
