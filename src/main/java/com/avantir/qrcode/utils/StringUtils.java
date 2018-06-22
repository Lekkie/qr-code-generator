package com.avantir.qrcode.utils;

public class StringUtils {

    public static String leftPad(String str, int len, String pad) {
        if(str == null)
            return null;
        StringBuilder sb = new StringBuilder();
        while (sb.length() + str.length() < len) {
            sb.append(pad);
        }
        sb.append(str);
        String paddedString = sb.toString();
        return paddedString;
    }

}
