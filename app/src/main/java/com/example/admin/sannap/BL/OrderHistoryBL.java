package com.example.admin.sannap.BL;

import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 20-01-2016.
 */
public class OrderHistoryBL {

    public String getOrderHistory(String userID) {

        String result = callWS(userID);
        validate(result);
        return "";
    }

    private String callWS(String userID) {
        String text = null;

        try
        {  String URL="user_id="+userID;
            text= RestFullWS.serverRequest(URL, Constant.WS_ORDER_HISTORY);

        }
        catch (Exception e)
        {

        }
        return text;
    }

    private void validate(String strValue)    {

        String status="";
        String detail="";
        JSONParser jsonP=new JSONParser();

        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;




                JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(0).toString());
                status=jsonObject.get("result").toString();

                if(Constant.WS_RESPONSE_SUCCESS.equalsIgnoreCase(status)){
                    detail=jsonObject.get("order_details").toString();
                    parseDetail(detail);
                }






        } catch (Exception e) {


        }

    }


    private void parseDetail(String strValue)    {

        String status="";
        String detail="";
        JSONParser jsonP=new JSONParser();

        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;

            Constant.orderID=new String[jsonArrayObject.size()];
            Constant.orderName=new String[jsonArrayObject.size()];
            Constant.orderAddress=new String[jsonArrayObject.size()];
            Constant.orderAmount=new String[jsonArrayObject.size()];
            Constant.orderItem=new String[jsonArrayObject.size()];
            Constant.orderDate=new String[jsonArrayObject.size()];
            Constant.orderStatus=new String[jsonArrayObject.size()];
            for(int i=0;i<jsonArrayObject.size();i++) {

                JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(i).toString());

                Constant.orderID[i]=jsonObject.get("order_id").toString();
                Constant.orderName[i]=jsonObject.get("name").toString();
                Constant.orderDate[i]=jsonObject.get("order_at").toString();
                Constant.orderAddress[i]=jsonObject.get("address").toString()+" , "+jsonObject.get("city").toString();
                Constant.orderAmount[i]=jsonObject.get("grand_total").toString();
                Constant.orderStatus[i]=jsonObject.get("order_status").toString();
                String itemns=jsonObject.get("item_list").toString();
                String result=parseItems(itemns);
                Constant.orderItem[i]=result;

            }





        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    private String parseItems(String strValue)    {

        String status="";
        String detail="";
        JSONParser jsonP=new JSONParser();

        try {
            Object obj =jsonP.parse(strValue);
            JSONArray jsonArrayObject = (JSONArray)obj;

            for(int i=0;i<jsonArrayObject.size();i++) {

                JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(i).toString());

                if(i==0)
                status=jsonObject.get("item_name").toString();
                else
                    status=status+" , "+jsonObject.get("item_name").toString();


            }





        } catch (Exception e) {
            e.printStackTrace();

        }
        return status;
    }
}
