package com.avantir.qrcode.scheme;


import com.avantir.qrcode.Schema;

import java.util.LinkedHashMap;
import java.util.Map;


public class EMV {

    private static final String PAYLOAD_FORMAT_INDICATOR = "00"; //M, 02 (N)
    private static final String POINT_OF_INITIATION_METHOD = "01"; //O, 02 (N)
    private static final String MERCHANT_ACCOUNT_INFORMATION_START = "02"; //M, up to 99 (ANS)
    private static final String MERCHANT_ACCOUNT_INFORMATION_END = "51"; //M, up to 99 (ANS)
    private static final String MERCHANT_CATEGORY_CODE = "52"; //M, 04 (N)
    private static final String TRANSACTION_CURRENCY = "53"; //M, 03 (N)
    private static final String TRANSACTION_AMOUNT = "54"; // C, up to 13, (ANS)
    private static final String TIP_OR_CONVENIENCE_INDICATOR = "55"; //O, 02 (N)
    private static final String VALUE_OF_CONVENIENCE_FEE_FIXED = "56"; // C, up to 13 (ANS)
    private static final String VALUE_OF_CONVENIENCE_FEE_PERCENTAGE = "57"; // C, up to 05 (ANS)
    private static final String COUNTRY_CODE = "58"; // M, 02 (ANS)
    private static final String MERCHANT_NAME = "59"; // M, up to 25 (ANS)
    private static final String MERCHANT_CITY = "60"; // M, up to 15 (ANS)
    private static final String POST_CODE = "61"; // O, up to 10 (ANS)
    private static final String ADDITIONAL_DATA_TEMPLATE = "62"; // O, up to 99 (S)
    private static final String MERCHANT_INFORMATION_LANGUAGE_TEMPLATE = "64"; // O, up to 99 (S)
    private static final String RFU_FOR_EMVCO_START = "65"; //O, up to 99 (S)
    private static final String RFU_FOR_EMVCO_END = "79"; //O, up to 99 (S)
    private static final String UNRESERVED_TEMPLATES_START = "80"; //O, up to 99 (S)
    private static final String UNRESERVED_TEMPLATES_END = "99"; //O, up to 99 (S)
    private static final String CYCLIC_REDUNDANCY_CHECK = "63";//M, 04 (ANS)



    public static void main(String[] args){
        try{

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /*
    @Override
    public Schema parseSchema(String code) {
        if (code == null || !code.startsWith(PAYLOAD_FORMAT_INDICATOR)) {
            throw new IllegalArgumentException("this is not a valid EMV QR code: " + code);
        }
        Map<String, String> parameters = getParameters(code);
        if (parameters.containsKey(NAME)) {
            setName(parameters.get(NAME));
        }
        if (parameters.containsKey(TITLE)) {
            setTitle(parameters.get(TITLE));
        }
        if (parameters.containsKey(COMPANY)) {
            setCompany(parameters.get(COMPANY));
        }
        if (parameters.containsKey(ADDRESS)) {
            setAddress(parameters.get(ADDRESS));
        }
        if (parameters.containsKey(EMAIL)) {
            setEmail(parameters.get(EMAIL));
        }
        if (parameters.containsKey(WEB)) {
            setWebsite(parameters.get(WEB));
        }
        if (parameters.containsKey(PHONE)) {
            setPhoneNumber(parameters.get(PHONE));
        }
        if (parameters.containsKey(NOTE)) {
            setNote(parameters.get(NOTE));
        }
        return this;
    }

    @Override
    public String generateString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BEGIN_VCARD).append(LINE_FEED);
        sb.append("VERSION:3.0").append(LINE_FEED);
        if (name != null) {
            sb.append(NAME).append(":").append(name);
        }
        if (company != null) {
            sb.append(LINE_FEED).append(COMPANY).append(":").append(company);
        }
        if (title != null) {
            sb.append(LINE_FEED).append(TITLE).append(":").append(title);
        }
        if (phoneNumber != null) {
            sb.append(LINE_FEED).append(PHONE).append(":").append(phoneNumber);
        }
        if (website != null) {
            sb.append(LINE_FEED).append(WEB).append(":").append(website);
        }
        if (email != null) {
            sb.append(LINE_FEED).append(EMAIL).append(":").append(email);
        }
        if (address != null) {
            sb.append(LINE_FEED).append(ADDRESS).append(":").append(address);
        }
        if (note != null) {
            sb.append(LINE_FEED).append(NOTE).append(":").append(note);
        }
        sb.append(LINE_FEED).append("END:VCARD");
        return sb.toString();
    }


    public static Map<String, String> getParameters(final String qrCode) {


        Map<String, String> result = new LinkedHashMap<>();
        BerTag TAG_E0 = new BerTag(0xe0);

        String[] parts = qrCode.split(paramSeparator);
        for (int i = 0; i < parts.length; i++) {
            String[] param = parts[i].split(keyValueSeparator);
            if (param.length > 1) {
                result.put(param[0], param[1]);
            }
        }
        return result;
    }
    */


    /**
     * Returns the textual representation of this vcard of the form
     * <p>
     * BEGIN:VCARD N:John Doe ORG:Company TITLE:Title TEL:1234 URL:www.example.org
     * EMAIL:john.doe@example.org ADR:Street END:VCARD
     * </p>
     */
    /*
    public String toString() {
        return generateString();
    }

    public EMV parse(final String code) {
        parseSchema(code);
        return this;
    }
    */


    public class additionalData{

    }

    public class merchantInformationLanguage{

    }

}
