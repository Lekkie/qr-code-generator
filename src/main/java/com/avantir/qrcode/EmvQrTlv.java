package com.avantir.qrcode;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class EmvQrTlv extends EmvTlv{

    private PaymentSystem paymentSystem;
    private final String version;
    private final String pointOfInitiationMethod;
    private final String amount;
    private final ConvenienceFeeIndicator convenienceIndicator;
    private final String fixedConvenienceFeeValue;
    private final String percentageConvenienceFeeValue;
    private String visaMerchantAccountInfo;
    private String mastercardMerchantAccountInfo;
    private String discoverMerchantAccountInfo;
    private String amexMerchantAccountInfo;
    private String jcbMerchantAccountInfo;
    private String unionPayMerchantAccountInfo;
    private ArcaMerchAcctInfoTemplate arcaMerchantAccountInfo;
    private final String merchantCategoryCode;
    private final String transactionCurrencyCode;
    private final String countryCode;
    private final String merchantName;
    private final String merchantCity;
    private final String postCode;
    private final AdditionalDataTemplate additionalData;
    private final MerchantInfoLang merchantInfoLang;
    private final String checkSum;
    private final String qrCodeTlv;

    public EmvQrTlv(String qrCodeTlv) {
        this.qrCodeTlv = qrCodeTlv;
        Map tlvMap = decode(this.qrCodeTlv);
        version = tlvMap.containsKey("00") ? tlvMap.get("00").toString() : null;
        pointOfInitiationMethod = tlvMap.containsKey("01") ? tlvMap.get("01").toString() : null;

        if(tlvMap.containsKey("02")){
            this.visaMerchantAccountInfo = tlvMap.get("02").toString();
        }
        if(tlvMap.containsKey("03")){
            this.visaMerchantAccountInfo = tlvMap.get("03").toString();
        }
        if(tlvMap.containsKey("04")){
            this.mastercardMerchantAccountInfo = tlvMap.get("04").toString();
        }
        if(tlvMap.containsKey("05")){
            this.mastercardMerchantAccountInfo = tlvMap.get("05").toString();
        }
        if(tlvMap.containsKey("09")){
            this.paymentSystem = PaymentSystem.DISCOVER;
            this.discoverMerchantAccountInfo = tlvMap.get("09").toString();
        }
        if(tlvMap.containsKey("10")){
            this.paymentSystem = PaymentSystem.DISCOVER;
            this.discoverMerchantAccountInfo = tlvMap.get("10").toString();
        }
        if(tlvMap.containsKey("11")){
            //this.paymentSystem = PaymentSystem.AMEX;
            this.amexMerchantAccountInfo = tlvMap.get("11").toString();
        }
        if(tlvMap.containsKey("12")){
            //this.paymentSystem = PaymentSystem.AMEX;
            this.amexMerchantAccountInfo = tlvMap.get("12").toString();
        }
        if(tlvMap.containsKey("13")){
            //this.paymentSystem = PaymentSystem.JCB;
            this.jcbMerchantAccountInfo = tlvMap.get("13").toString();
        }
        if(tlvMap.containsKey("14")){
            //this.paymentSystem = PaymentSystem.JCB;
            this.jcbMerchantAccountInfo = tlvMap.get("14").toString();
        }
        if(tlvMap.containsKey("15")){
            //this.paymentSystem = PaymentSystem.UNIONPAY;
            this.unionPayMerchantAccountInfo = tlvMap.get("15").toString();
        }
        if(tlvMap.containsKey("16")){
            //this.paymentSystem = PaymentSystem.UNIONPAY;
            this.unionPayMerchantAccountInfo = tlvMap.get("16").toString();
        }
        if(tlvMap.containsKey("26")){
            //this.paymentSystem = PaymentSystem.ARCA;
            String template26 = tlvMap.containsKey("26") ? tlvMap.get("26").toString() : null;
            this.arcaMerchantAccountInfo = new ArcaMerchAcctInfoTemplate(template26);
        }
        else{
            throw new IllegalArgumentException("");
        }

        this.merchantCategoryCode = tlvMap.containsKey("52") ? tlvMap.get("52").toString() : null;
        this.transactionCurrencyCode = tlvMap.containsKey("53") ? tlvMap.get("53").toString() : null;
        this.amount = tlvMap.containsKey("54") ? tlvMap.get("54").toString() : null;
        String convIndStr = tlvMap.containsKey("55") ? tlvMap.get("55").toString() : null;
        if(convIndStr != null && !convIndStr.isEmpty()){
            switch(convIndStr){
                case "01":
                    this.convenienceIndicator = ConvenienceFeeIndicator.CUSTOMER_ENTER_TIP;
                    break;
                case "02":
                    this.convenienceIndicator = ConvenienceFeeIndicator.FIXED_CONVENIENCE_FEE;
                    break;
                case "03":
                    this.convenienceIndicator = ConvenienceFeeIndicator.PERENTAGE_CONVENIENCE_FEE;
                    break;
                default:
                    this.convenienceIndicator = null;
                    break;
            }
        }
        else{
            this.convenienceIndicator = null;
        }

        this.fixedConvenienceFeeValue = tlvMap.containsKey("56") ? tlvMap.get("56").toString() : null;
        this.percentageConvenienceFeeValue = tlvMap.containsKey("57") ? tlvMap.get("57").toString() : null;
        this.countryCode = tlvMap.containsKey("58") ? tlvMap.get("58").toString() : null;
        this.merchantName = tlvMap.containsKey("59") ? tlvMap.get("59").toString() : null;
        this.merchantCity = tlvMap.containsKey("60") ? tlvMap.get("60").toString() : null;
        this.postCode = tlvMap.containsKey("61") ? tlvMap.get("61").toString() : null;;
        String template62 = tlvMap.containsKey("62") ? tlvMap.get("62").toString() : null;
        this.additionalData = new AdditionalDataTemplate(template62);
        String template64 = tlvMap.containsKey("64") ? tlvMap.get("64").toString() : null;
        this.merchantInfoLang = null; //new MerchantInfoLang(template64);
        this.checkSum = tlvMap.containsKey("63") ? tlvMap.get("63").toString() : null;
    }

    public EmvQrTlv(Builder builder) {
        this.version = builder.version;
        this.pointOfInitiationMethod = builder.pointOfInitiationMethod;
        this.amount = builder.amount;
        this.convenienceIndicator = builder.convenienceIndicator;
        this.fixedConvenienceFeeValue = builder.fixedConvenienceFeeValue;
        this.percentageConvenienceFeeValue = builder.percentageConvenienceFeeValue;
        this.arcaMerchantAccountInfo = builder.arcaMerchantAccountInfo;
        this.visaMerchantAccountInfo = builder.visaMerchantAccountInfo;
        this.mastercardMerchantAccountInfo = builder.mastercardMerchantAccountInfo;
        this.discoverMerchantAccountInfo = builder.discoverMerchantAccountInfo;
        this.amexMerchantAccountInfo = builder.amexMerchantAccountInfo;
        this.jcbMerchantAccountInfo = builder.jcbMerchantAccountInfo;
        this.unionPayMerchantAccountInfo = builder.unionPayMerchantAccountInfo;
        if(arcaMerchantAccountInfo != null)
            paymentSystem = PaymentSystem.ARCA;
        else if(visaMerchantAccountInfo != null && !visaMerchantAccountInfo.isEmpty())
            paymentSystem = PaymentSystem.VISA;
        else if(mastercardMerchantAccountInfo != null && !mastercardMerchantAccountInfo.isEmpty())
            paymentSystem = PaymentSystem.MASTERCARD;
        else if(discoverMerchantAccountInfo != null && !discoverMerchantAccountInfo.isEmpty())
            paymentSystem = PaymentSystem.DISCOVER;
        else if(amexMerchantAccountInfo != null && !amexMerchantAccountInfo.isEmpty())
            paymentSystem = PaymentSystem.AMEX;
        else if(jcbMerchantAccountInfo != null && !jcbMerchantAccountInfo.isEmpty())
            paymentSystem = PaymentSystem.JCB;
        else if(unionPayMerchantAccountInfo != null && !unionPayMerchantAccountInfo.isEmpty())
            paymentSystem = PaymentSystem.UNIONPAY;
        else
            paymentSystem = null;
        this.merchantCategoryCode = builder.merchantCategoryCode;
        this.transactionCurrencyCode = builder.transactionCurrencyCode;
        this.countryCode = builder.countryCode;
        this.merchantName = builder.merchantName;
        this.merchantCity = builder.merchantCity;
        this.postCode = builder.postCode;
        this.additionalData = builder.additionalData;
        this.merchantInfoLang = builder.merchantInfoLang;
        this.checkSum = getCheckSum();
        this.qrCodeTlv = encode();
    }

    public static class Builder{
        private String version;
        private String pointOfInitiationMethod;
        private String amount;
        private ConvenienceFeeIndicator convenienceIndicator;
        private String fixedConvenienceFeeValue;
        private String percentageConvenienceFeeValue;
        private String visaMerchantAccountInfo;
        private String mastercardMerchantAccountInfo;
        private String discoverMerchantAccountInfo;
        private String amexMerchantAccountInfo;
        private String jcbMerchantAccountInfo;
        private String unionPayMerchantAccountInfo;
        private ArcaMerchAcctInfoTemplate arcaMerchantAccountInfo;
        private String merchantCategoryCode;
        private String transactionCurrencyCode;
        private String countryCode;
        private String merchantName;
        private String merchantCity;
        private String postCode;
        private AdditionalDataTemplate additionalData;
        private MerchantInfoLang merchantInfoLang;

        public Builder version(String val){
            version = val;
            return this;
        }
        public Builder pointOfInitiationMethod(String val){
            pointOfInitiationMethod = val;
            return this;
        }
        public Builder amount(String val){
            amount = val;
            return this;
        }
        public Builder convenienceIndicator(ConvenienceFeeIndicator val){
            convenienceIndicator = val;
            return this;
        }
        public Builder fixedConvenienceFeeValue(String val){
            fixedConvenienceFeeValue = val;
            return this;
        }
        public Builder percentageConvenienceFeeValue(String val){
            percentageConvenienceFeeValue = val;
            return this;
        }
        public Builder visaMerchantAccountInfo(String val){
            visaMerchantAccountInfo = val;
            return this;
        }
        public Builder mastercardMerchantAccountInfo(String val){
            mastercardMerchantAccountInfo = val;
            return this;
        }
        public Builder discoverMerchantAccountInfo(String val){
            discoverMerchantAccountInfo = val;
            return this;
        }
        public Builder amexMerchantAccountInfo(String val){
            amexMerchantAccountInfo = val;
            return this;
        }
        public Builder jcbMerchantAccountInfo(String val){
            jcbMerchantAccountInfo = val;
            return this;
        }
        public Builder unionPayMerchantAccountInfo(String val){
            unionPayMerchantAccountInfo = val;
            return this;
        }
        public Builder arcaMerchantAccountInfo(ArcaMerchAcctInfoTemplate val){
            arcaMerchantAccountInfo = val;
            return this;
        }
        public Builder merchantCategoryCode(String val){
            merchantCategoryCode = val;
            return this;
        }
        public Builder transactionCurrencyCode(String val){
            transactionCurrencyCode = val;
            return this;
        }
        public Builder countryCode(String val){
            countryCode = val;
            return this;
        }
        public Builder merchantName(String val){
            merchantName = val;
            return this;
        }
        public Builder merchantCity(String val){
            merchantCity = val;
            return this;
        }
        public Builder postCode(String val){
            postCode = val;
            return this;
        }
        public Builder additionalData(AdditionalDataTemplate val){
            additionalData = val;
            return this;
        }
        public Builder merchantInfoLang(MerchantInfoLang val){
            merchantInfoLang = val;
            return this;
        }
        public EmvQrTlv build(){
            return new EmvQrTlv(this);
        }
    }


    private String pack(){
        StringBuilder sb = new StringBuilder();
        if(version == null || version.isEmpty())
            throw new IllegalArgumentException("Payload Format Indicator (Version) cannot be empty");
        sb.append("00");
        sb.append(StringUtils.leftPad(String.valueOf(version.length()), 2, "0"));
        sb.append(version);

        if(pointOfInitiationMethod == null || pointOfInitiationMethod.isEmpty())
            throw new IllegalArgumentException("Point Of Initiation Method cannot be empty");
        sb.append("01");
        sb.append(StringUtils.leftPad(String.valueOf(pointOfInitiationMethod.length()), 2, "0"));
        sb.append(pointOfInitiationMethod);

        if(visaMerchantAccountInfo != null && !visaMerchantAccountInfo.isEmpty()){
            sb.append("02");
            sb.append(StringUtils.leftPad(String.valueOf(visaMerchantAccountInfo.length()), 2, "0"));
            sb.append(visaMerchantAccountInfo);
        }
        if(mastercardMerchantAccountInfo != null && !mastercardMerchantAccountInfo.isEmpty()){
            sb.append("04");
            sb.append(StringUtils.leftPad(String.valueOf(mastercardMerchantAccountInfo.length()), 2, "0"));
            sb.append(mastercardMerchantAccountInfo);
        }
        if(discoverMerchantAccountInfo != null && !discoverMerchantAccountInfo.isEmpty()){
            sb.append("09");
            sb.append(StringUtils.leftPad(String.valueOf(discoverMerchantAccountInfo.length()), 2, "0"));
            sb.append(discoverMerchantAccountInfo);
        }
        if(amexMerchantAccountInfo != null && !amexMerchantAccountInfo.isEmpty()){
            sb.append("11");
            sb.append(StringUtils.leftPad(String.valueOf(amexMerchantAccountInfo.length()), 2, "0"));
            sb.append(amexMerchantAccountInfo);
        }
        if(jcbMerchantAccountInfo != null && !jcbMerchantAccountInfo.isEmpty()){
            sb.append("13");
            sb.append(StringUtils.leftPad(String.valueOf(jcbMerchantAccountInfo.length()), 2, "0"));
            sb.append(jcbMerchantAccountInfo);
        }
        if(unionPayMerchantAccountInfo != null && !unionPayMerchantAccountInfo.isEmpty()){
            sb.append("15");
            sb.append(StringUtils.leftPad(String.valueOf(unionPayMerchantAccountInfo.length()), 2, "0"));
            sb.append(unionPayMerchantAccountInfo);
        }
        if(arcaMerchantAccountInfo != null){
            sb.append(arcaMerchantAccountInfo.getArcaMerchAcctInfoTemplateQrCodeTlv());
        }
        else{
            throw new IllegalArgumentException("Merchant Account Info cannot be empty");
        }


        if(merchantCategoryCode == null || merchantCategoryCode.isEmpty())
            throw new IllegalArgumentException("Merchant Category Code cannot be empty");
        sb.append("52");
        sb.append(StringUtils.leftPad(String.valueOf(merchantCategoryCode.length()), 2, "0"));
        sb.append(merchantCategoryCode);

        if(transactionCurrencyCode == null || transactionCurrencyCode.isEmpty())
            throw new IllegalArgumentException("Transaction Currency Code cannot be empty");
        sb.append("53");
        sb.append(StringUtils.leftPad(String.valueOf(transactionCurrencyCode.length()), 2, "0"));
        sb.append(transactionCurrencyCode);

        if(amount != null && !amount.isEmpty()){
            sb.append("54");
            sb.append(StringUtils.leftPad(String.valueOf(amount.length()), 2, "0"));
            sb.append(amount);
        }

        if(convenienceIndicator != null){
            String indicator = "01";
            switch(convenienceIndicator){
                case CUSTOMER_ENTER_TIP:
                    indicator = "01";
                    break;
                case FIXED_CONVENIENCE_FEE:
                    indicator = "02";
                    break;
                case PERENTAGE_CONVENIENCE_FEE:
                    indicator = "03";
                    break;
            }
            sb.append("55");
            sb.append(StringUtils.leftPad(String.valueOf(indicator.length()), 2, "0"));
            sb.append(indicator);

            if(convenienceIndicator.equals(ConvenienceFeeIndicator.FIXED_CONVENIENCE_FEE)){
                sb.append("56");
                sb.append(StringUtils.leftPad(String.valueOf(fixedConvenienceFeeValue.length()), 2, "0"));
                sb.append(fixedConvenienceFeeValue);
            }
            else if(convenienceIndicator.equals(ConvenienceFeeIndicator.PERENTAGE_CONVENIENCE_FEE)){
                sb.append("57");
                sb.append(StringUtils.leftPad(String.valueOf(percentageConvenienceFeeValue.length()), 2, "0"));
                sb.append(percentageConvenienceFeeValue);
            }
        }

        if(countryCode == null || countryCode.isEmpty())
            throw new IllegalArgumentException("Country Code cannot be empty");
        sb.append("58");
        sb.append(StringUtils.leftPad(String.valueOf(countryCode.length()), 2, "0"));
        sb.append(countryCode);

        if(merchantName == null || merchantName.isEmpty())
            throw new IllegalArgumentException("Merchant Name cannot be empty");
        sb.append("59");
        sb.append(StringUtils.leftPad(String.valueOf(merchantName.length()), 2, "0"));
        sb.append(merchantName);

        if(merchantCity == null || merchantCity.isEmpty())
            throw new IllegalArgumentException("Merchant City cannot be empty");
        sb.append("60");
        sb.append(StringUtils.leftPad(String.valueOf(merchantCity.length()), 2, "0"));
        sb.append(merchantCity);

        if(postCode != null && !postCode.isEmpty()){
            sb.append("61");
            sb.append(StringUtils.leftPad(String.valueOf(postCode.length()), 2, "0"));
            sb.append(postCode);
        }

        if(postCode != null && !postCode.isEmpty()){
            sb.append("61");
            sb.append(StringUtils.leftPad(String.valueOf(postCode.length()), 2, "0"));
            sb.append(postCode);
        }

        if(additionalData != null){
            sb.append(additionalData.encode());
        }

        if(merchantInfoLang != null){
            sb.append(merchantInfoLang.encode());
        }

        sb.append("63");
        sb.append("04");

        return sb.toString();
    }

    private String getCheckSum(){
        String str = pack();
        String crc = crc16(str.getBytes());
        return crc;
    }


    protected String encode(){
        StringBuilder sb = new StringBuilder(pack());
        sb.append(StringUtils.leftPad(getCheckSum(), 4, "0"));
        return sb.toString();
    }




    public enum ConvenienceFeeIndicator{
        CUSTOMER_ENTER_TIP,
        FIXED_CONVENIENCE_FEE,
        PERENTAGE_CONVENIENCE_FEE
    }

    public enum PaymentSystem{
        VISA,
        MASTERCARD,
        DISCOVER,
        AMEX,
        JCB,
        UNIONPAY,
        ARCA,
        MULTI
    }



    public class MerchantInfoLang{

        private String globalUniqueId;
        Map<String, String> paymentSystemSpecific;

        public MerchantInfoLang(){

        }

        public String encode(){
            return null;
        }

        public MerchantInfoLang decode(String qrCodeTlv){
            return null;
        }
    }


    public String toString(){
        return "";
    }

    private static final int POLYNOMIAL   = 0x1021;
    private static final int PRESET_VALUE = 0xFFFF;


    /*
   The checksum shall be calculated according to [ISO/IEC 13239] using the polynomial '1021' (hex)
   and initial value 'FFFF' (hex). The data over which the checksum is calculated shall cover all
   data objects, including their ID, Length and Value, to be included in the QR Code, in their
   respective order, as well as the ID and Length of the CRC itself (but excluding its Value).

   Following the calculation of the checksum, the resulting 2-byte hexadecimal value shall be
   encoded as a 4-character Alphanumeric Special value by converting each nibble to an Alphanumeric
   Special character.
   Example: a CRC with a two-byte hexadecimal value of '007B' is included in the QR Code as "6304007B"
    */
    public String crc16(byte[] data)
    {
        int current_crc_value = PRESET_VALUE;
        for (int i = 0; i < data.length; i++ )
        {
            current_crc_value ^= data[i] & 0xFF;
            for (int j = 0; j < 8; j++)
            {
                if ((current_crc_value & 1) != 0)
                {
                    current_crc_value = (current_crc_value >>> 1) ^ POLYNOMIAL;
                }
                else
                {
                    current_crc_value = current_crc_value >>> 1;
                }
            }
        }
        current_crc_value = ~current_crc_value;

        return Integer.toHexString(current_crc_value & 0xFFFF).toUpperCase();
    }

    public String getVersion() {
        return version;
    }

    public String getPointOfInitiationMethod() {
        return pointOfInitiationMethod;
    }

    public String getAmount() {
        return amount;
    }

    public ConvenienceFeeIndicator getConvenienceIndicator() {
        return convenienceIndicator;
    }

    public String getFixedConvenienceFeeValue() {
        return fixedConvenienceFeeValue;
    }

    public String getPercentageConvenienceFeeValue() {
        return percentageConvenienceFeeValue;
    }

    public ArcaMerchAcctInfoTemplate getArcaMerchantAccountInfo() {
        return arcaMerchantAccountInfo;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public String getTransactionCurrencyCode() {
        return transactionCurrencyCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantCity() {
        return merchantCity;
    }

    public String getPostCode() {
        return postCode;
    }

    public AdditionalDataTemplate getAdditionalData() {
        return additionalData;
    }

    public MerchantInfoLang getMerchantInfoLang() {
        return merchantInfoLang;
    }

    public String getQrCodeTlv() {
        return qrCodeTlv;
    }

    public PaymentSystem getPaymentSystem() {
        return paymentSystem;
    }

    public static void main(String[] args){
        try{
            ArcaMerchAcctInfoTemplate arcaMerchantAccountInfo = new ArcaMerchAcctInfoTemplate.Builder()
                    .arcaAppId("ARCA0001")
                    .acquirerId("627058")
                    .merchantId("MERCHANT0001")
                    .ptsp("CoralPay")
                    .build();

            AdditionalDataTemplate additionalData = new AdditionalDataTemplate.Builder()
                    .billNumber("00000001")
                    //.mobileNumber("08032296229")
                    .terminalLabel("471098303")
                    //.referenceLabel("000213")
                    //.storeLabel("Shoprite Plaza (Game)")
                    //.customerLabel("lekkie.aydot@gmail.com")
                    //.purposeOfTransaction("Grocery")
                    .qrSchemeProvider("Arca")
                    //.billItemURI("http://www.arca.net/item/00000001")
                    .loyaltyNumber("0001")
                    .qrSchemeProcessor("Arca")
                    .transactionLocationLat("4")
                    .transactionLocationLong("32")
                    .vatTaxAmount("30")
                    .vatTaxRate("5.5")
                    .additionalCustomerDataRequest("Customer Data")
                    .bvn("...")
                    .build();

            EmvQrTlv emvQrTlv = new Builder()
                    .version("01")
                    .pointOfInitiationMethod("11")
                    //.visaMerchantAccountInfo("460067893452143")
                    //.mastercardMerchantAccountInfo("555544443333111")
                    .arcaMerchantAccountInfo(arcaMerchantAccountInfo)
                    .merchantCategoryCode("4999")
                    .transactionCurrencyCode("566")
                    //.amount("5000")
                    .countryCode("NG")
                    .merchantName("Shoprite")
                    .merchantCity("Lagos")
                    .additionalData(additionalData)
                    .convenienceIndicator(ConvenienceFeeIndicator.PERENTAGE_CONVENIENCE_FEE)
                    .percentageConvenienceFeeValue("3.00")
                    .build();

            String qrCodeTlv = "000201010211021546006789345214304155555444433331115204123453037025403...5802SG5908MEGAMART6009SINGAPORE62210708457843120305A60086304A68E";
            qrCodeTlv = emvQrTlv.getQrCodeTlv();
            System.out.println(qrCodeTlv);

            EmvQrTlv emvQrTlv2 = new EmvQrTlv(qrCodeTlv);

            QRCode qrCode = QRCode.from(qrCodeTlv);
            qrCode.to(ImageType.PNG);
            qrCode.withSize(400, 400);
            File file = new File("/Users/lekanomotayo/Dropbox/projects/arca/qr-code/arca.png");
            FileOutputStream fos = new FileOutputStream(file);
            qrCode.writeTo(fos);


            //Map<String, Object> tlvMap =  emvQrTlv.decode(qrCodeTlv);
            //System.out.println(tlvMap);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
