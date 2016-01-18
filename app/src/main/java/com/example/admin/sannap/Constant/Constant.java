package com.example.admin.sannap.Constant;

/**
 * Created by appslure on 07-01-2016.
 */
public class Constant {

    public static  String STREMAILADDREGEX="^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$"; //EMAIL REGEX
    public static String PREFS_NAME="MyPrefsFile";

    public static final String MSG_KEY = "m";



    public static String WS_HTTP="Http";
    public static String WS_DOMAIN_NAME="appslure.com";
    public static String WS_PATH="/sannap/android/";

    public static String WS_RESPONSE_SUCCESS="success";
    public static String WS_RESPONSE_FAILURE="failure";

    public static String SP_LOGIN_ID="login";

    public static String WS_SIGNUP="signup.php";
    public static String WS_SIGNIN="login.php";
    public static String WS_HOMESCREEN="user_data";
    public static String WS_SUBSCRIPTION="subscription_list";
    public static String WS_SUBSCRIPTION_DETAIL="subscription_detail";
    public static String WS_SUBSCRIPTION_CHECKOUT="user_subscribe";


    /* SUBSCRIPTION LIST */

    public static String modelID[];
    public static String modelName[];
    public static String modelPrice[];
    public static String modelOriginal[];

    /* SUBSCRIPTION DETAIL*/

    public static String detailName[];
    public static String detailQuantity[];
    public static String detailUnit[];

}
