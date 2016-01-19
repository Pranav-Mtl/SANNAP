package com.example.admin.sannap.BL;

import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 18-01-2016.
 */
public class ShopBL {

    public String getShopData() {

        String result = callWS();
        validate(result);
        return "";
    }

    private String callWS() {
        String text = null;

        try
        {  String URL="";
            text= RestFullWS.serverRequest(URL, Constant.WS_SHOP);

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

            Constant.shopID=new String[jsonArrayObject.size()];
            Constant.shopImage=new String[jsonArrayObject.size()];
            Constant.shopName=new String[jsonArrayObject.size()];
            Constant.shopPrice=new int[jsonArrayObject.size()];
            Constant.shopUnit=new String[jsonArrayObject.size()];

            for (int i=0;i<jsonArrayObject.size();i++) {
                JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(i).toString());
                Constant.shopID[i]=jsonObject.get("item_id").toString();
                Constant.shopImage[i]=jsonObject.get("item_image").toString();
                Constant.shopName[i]=jsonObject.get("item_name").toString();
                Constant.shopPrice[i]=Integer.valueOf(jsonObject.get("price").toString());
                Constant.shopUnit[i]=jsonObject.get("si_unit").toString();
            }



        } catch (Exception e) {


        }

    }
}
