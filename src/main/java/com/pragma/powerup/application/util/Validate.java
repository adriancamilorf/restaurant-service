package com.pragma.powerup.application.util;

import java.util.regex.Pattern;

public class Validate {

    public static boolean onlyNumber(String text){
        return Pattern.matches("\\d+", text);
    }
    public static boolean isPhoneValid(String phone){
        String regex = "^(\\+\\d{10,12}|\\d{10,12})$";
        return phone.matches(regex);
    }

    public static boolean notIsString(String data) {
        return data == null || data.trim().isEmpty();
    }

}
