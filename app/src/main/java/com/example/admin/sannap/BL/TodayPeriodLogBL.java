package com.example.admin.sannap.BL;

import com.example.admin.sannap.Constant.Constant;
import com.example.admin.sannap.WS.RestFullWS;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by appslure on 28-01-2016.
 */
public class TodayPeriodLogBL {

    public String getLogData(String userID,String cycleID) {

        String result = callWS(userID,cycleID);
        String status=validate(result);
        return status;
    }

    private String callWS(String userID,String cycleID) {
        String text = null;

        try
        {  String URL="user_id="+userID+"&cycle_id="+cycleID;
            text= RestFullWS.serverRequest(URL, Constant.WS_CYCLE_LOG);

        }
        catch (Exception e)
        {

        }
        return text;
    }

    private String validate(String strValue)    {
        String status="";
        if(strValue.equalsIgnoreCase("[]")){
            status="Failure";
        }
        else {
            status="Success";
            JSONParser jsonP = new JSONParser();

            try {
                Object obj = jsonP.parse(strValue);
                JSONArray jsonArrayObject = (JSONArray) obj;

                Constant.cycle_id = new String[jsonArrayObject.size()];
                Constant.spotting = new String[jsonArrayObject.size()];
                Constant.cervical_mucus = new String[jsonArrayObject.size()];
                Constant.body_temp = new String[jsonArrayObject.size()];
                Constant.ovulation_test = new String[jsonArrayObject.size()];
                Constant.body = new String[jsonArrayObject.size()];
                Constant.mood = new String[jsonArrayObject.size()];
                Constant.notes = new String[jsonArrayObject.size()];
                Constant.sexual_activity = new String[jsonArrayObject.size()];
                Constant.pill_intake = new String[jsonArrayObject.size()];
                Constant.cycleDate = new String[jsonArrayObject.size()];

                for (int i = 0; i < jsonArrayObject.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonP.parse(jsonArrayObject.get(i).toString());
                    Constant.cycle_id[i] = jsonObject.get("cycle_id").toString();
                    Constant.spotting[i] = jsonObject.get("spotting").toString();
                    Constant.cervical_mucus[i] = jsonObject.get("cervical_mucus").toString();
                    Constant.body_temp[i] = jsonObject.get("body_temp").toString();
                    Constant.ovulation_test[i] = jsonObject.get("ovulation_test").toString();
                    Constant.body[i] = jsonObject.get("body").toString();
                    Constant.mood[i] = jsonObject.get("mood").toString();
                    Constant.notes[i] = jsonObject.get("notes").toString();
                    Constant.sexual_activity[i] = jsonObject.get("sexual_activity").toString();
                    Constant.pill_intake[i] = jsonObject.get("pill_intake").toString();
                    Constant.cycleDate[i] = jsonObject.get("date").toString();

                }


            } catch (Exception e) {


            }
        }

        return status;
    }
}
