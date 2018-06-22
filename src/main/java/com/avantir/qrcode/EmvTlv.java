package com.avantir.qrcode;

import java.util.HashMap;
import java.util.Map;

public abstract class EmvTlv {

    protected abstract String encode();

    protected Map decode(String qrCodeTlv){

        Map<String, Object> tlvMap = new HashMap<>();
        int index = 0;
        String qrCodeTlvTmp = qrCodeTlv;
        while (qrCodeTlvTmp.length() > 0 && index < 99){
            String tag = qrCodeTlvTmp.substring(0, 2);
            qrCodeTlvTmp = qrCodeTlvTmp.substring(2);
            String len = qrCodeTlvTmp.substring(0, 2);
            qrCodeTlvTmp = qrCodeTlvTmp.substring(2);
            int lenInt = Integer.parseInt(len);
            String value = qrCodeTlvTmp.substring(0, lenInt);
            qrCodeTlvTmp = qrCodeTlvTmp.substring(lenInt);
            System.out.println("Tag: " + tag + " Value: " + value);

            /*
            if(templates.contains(tag)){
                Map<String, Object> templateTlvMap = new HashMap<>();
                while (value.length() > 0 && index < 99){
                    String templateTag = value.substring(0, 2);
                    value = value.substring(2);
                    String templateLen = value.substring(0, 2);
                    value = value.substring(2);
                    int templateLenInt = Integer.parseInt(templateLen);
                    String templateValue = value.substring(0, templateLenInt);
                    value = value.substring(templateLenInt);
                    templateTlvMap.put(templateTag, templateValue);
                }
                tlvMap.put(tag, templateTlvMap);
            }
            else{
                tlvMap.put(tag, value);
            }
            */
            tlvMap.put(tag, value);
        }
        return tlvMap;
    }



    /*
    public Map<String, Object> decode(String qrCodeTlv){

        Map<String, Object> tlvMap = new HashMap<>();
        int index = 0;
        String qrCodeTlvTmp = qrCodeTlv;
        while (qrCodeTlvTmp.length() > 0 && index < 99){
            String tag = qrCodeTlvTmp.substring(0, 2);
            qrCodeTlvTmp = qrCodeTlvTmp.substring(2);
            String len = qrCodeTlvTmp.substring(0, 2);
            qrCodeTlvTmp = qrCodeTlvTmp.substring(2);
            int lenInt = Integer.parseInt(len);
            String value = qrCodeTlvTmp.substring(0, lenInt);
            qrCodeTlvTmp = qrCodeTlvTmp.substring(lenInt);
            System.out.println("Tag: " + tag + " Value: " + value);

            if(templates.contains(tag)){
                Map<String, Object> templateTlvMap = new HashMap<>();
                while (value.length() > 0 && index < 99){
                    String templateTag = value.substring(0, 2);
                    value = value.substring(2);
                    String templateLen = value.substring(0, 2);
                    value = value.substring(2);
                    int templateLenInt = Integer.parseInt(templateLen);
                    String templateValue = value.substring(0, templateLenInt);
                    value = value.substring(templateLenInt);
                    templateTlvMap.put(templateTag, templateValue);
                }
                tlvMap.put(tag, templateTlvMap);
            }
            else{
                tlvMap.put(tag, value);
            }
        }
        return tlvMap;
    }
    */

}
