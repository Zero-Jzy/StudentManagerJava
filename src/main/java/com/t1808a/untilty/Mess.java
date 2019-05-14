package com.t1808a.untilty;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Mess {
    private  static Locale locale = Locale.getDefault();

    public static String getMess(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale);
        String mes = resourceBundle.getString(key);
        try {
            return new String(mes.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setLocale(Locale locale) {
        Mess.locale = locale;
    }
}
