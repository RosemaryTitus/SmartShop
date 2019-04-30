package com.softsolutions.smartshop;

import java.util.Random;

public class utilities {
    public static String uid="";
    public static String EMAIL="";
    public static String URL="http://192.168.21.127:8080";//192.168.43.197
    public static char[] OTP(int len) {
        String numbers = "0123456789";
        Random rndm_method = new Random();

        char[] otp = new char[len];
        for (int i = 0; i < len; i++)
        {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }

    public static String item_quantity="";
    public static String amount="";
    public static String pro_id="";
    public static String cart_amount = "";

}
