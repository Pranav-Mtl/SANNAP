package com.example.admin.sannap.Constant;

import java.util.HashMap;
import java.util.List;

/**
 * Created by appslure on 07-01-2016.
 */
public class Constant {

    public static  String STREMAILADDREGEX="^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$"; //EMAIL REGEX
    public static String PREFS_NAME="MyPrefsFile";

    public static final String MSG_KEY = "m";

    public static int cartItem=0;



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
    public static String WS_SHOP="item_list";
    public static String WS_SUBSCRIPTION_DETAIL="subscription_detail";
    public static String WS_SUBSCRIPTION_CHECKOUT="user_subscribe";


    /**/

    public static String keyItemID="item";
    public static String keyItemNAME="itemName";
    public static String keyItemUnit="itemUnit";
    public static String keyItemPrice="itemPrice";
    public static String keyItemQuantity="itemQuantity";
    public static String keyItemImage="itemImage";


    /* SUBSCRIPTION LIST */

    public static String modelID[];
    public static String modelName[];
    public static String modelPrice[];
    public static String modelOriginal[];

     /* SHOP LIST */

    public static String shopID[];
    public static String shopName[];
    public static int shopPrice[];
    public static String shopUnit[];
    public static String shopImage[];


    /* SUBSCRIPTION DETAIL*/

    public static String detailName[];
    public static String detailQuantity[];
    public static String detailUnit[];

    public static HashMap hmItemUnit=new HashMap();
    public static HashMap hmItemID=new HashMap();
    public static HashMap hmItemName=new HashMap();
    public static HashMap hmItemImage=new HashMap();
    public static HashMap<String,Integer> hmItemPrice=new HashMap<String,Integer>();
    public static HashMap<String,Integer> hmItemQuantity=new HashMap<String,Integer>();

    public static List listUnit;
    public static List listId;
    public static List listName;
    public static List listPrice;
    public static List listQuantity;
    public static List listImage;

}
