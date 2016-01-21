package com.example.admin.sannap.BL;

import com.example.admin.sannap.BE.ShopingConfirmedBE;
import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 19-01-2016.
 */
public class ShopingConfirmedBL {

    ShopingConfirmedBE objShopingConfirmedBE;
    public String setShopData(ShopingConfirmedBE shopingConfirmedBE) {

        objShopingConfirmedBE=shopingConfirmedBE;
        String result = callWS();
        String status=validate(result);
        return status;
    }

    private String callWS() {
        String text = null;
        //http://appslure.com/sannap/android/order.php?user_id=2&name=ggg&email=tyyt@gn.com&phone=7787899&address=cgfdghfc&city=yyy&zip=878687&amount=12&tax=1&discount=23&grand_total=45&order_status=ordered&payment_type=cash&promo_code=&item=1:3,2:2,:3:2,4:1
        try
        {  String URL="user_id="+objShopingConfirmedBE.getUserID()+"&name="+objShopingConfirmedBE.getUserName()+"&email="+objShopingConfirmedBE.getUserEmail()+"&phone="+objShopingConfirmedBE.getUserMobile()+"&address="+objShopingConfirmedBE.getUserAddress()+"&city="+objShopingConfirmedBE.getUserCity()+"&zip="+objShopingConfirmedBE.getUserZip()+"&city="+objShopingConfirmedBE.getUserCity()+"&amount="+objShopingConfirmedBE.getBasePrice()+"&tax="+objShopingConfirmedBE.getTax()+"&discount="+objShopingConfirmedBE.getDiscountedPrice()+"&grand_total="+objShopingConfirmedBE.getGrandTotal()+"&payment_type="+objShopingConfirmedBE.getUserPayment()+"&promo_code="+objShopingConfirmedBE.getPromocode()+"&item="+objShopingConfirmedBE.getItemSelected();
            text= RestFullWS.serverRequest(URL, Constant.WS_SHOPING_ORDER);

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
