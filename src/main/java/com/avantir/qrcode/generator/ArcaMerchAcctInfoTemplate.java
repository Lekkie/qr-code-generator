package com.avantir.qrcode.generator;

import com.avantir.qrcode.utils.StringUtils;

import java.util.Map;

public class ArcaMerchAcctInfoTemplate  extends EmvTlv {

    private final String arcaAppId;
    private final String acquirerId;
    private final String merchantId;
    private final String ptsp;
    private final String arcaMerchAcctInfoTemplateQrCodeTlv;

    public ArcaMerchAcctInfoTemplate(String arcaMerchAcctInfoTemplateQrCodeTlv) {
        this.arcaMerchAcctInfoTemplateQrCodeTlv = arcaMerchAcctInfoTemplateQrCodeTlv;
        Map tlvMap = decode(this.arcaMerchAcctInfoTemplateQrCodeTlv);
        this.arcaAppId = tlvMap.containsKey("00") ? tlvMap.get("00").toString() : null;
        this.acquirerId = tlvMap.containsKey("01") ? tlvMap.get("01").toString() : null;
        this.merchantId = tlvMap.containsKey("02") ? tlvMap.get("02").toString() : null;
        this.ptsp = tlvMap.containsKey("03") ? tlvMap.get("03").toString() : null;
    }


    public ArcaMerchAcctInfoTemplate(Builder builder){
        this.arcaAppId = builder.arcaAppId;
        this.acquirerId = builder.acquirerId;
        this.merchantId = builder.merchantId;
        this.ptsp = builder.ptsp;
        this.arcaMerchAcctInfoTemplateQrCodeTlv = encode();
    }

    public static class Builder{

        private String arcaAppId;
        private String acquirerId;
        private String merchantId;
        private String ptsp;

        public Builder arcaAppId(String val){
            arcaAppId = val;
            return this;
        }
        public Builder acquirerId(String val){
            acquirerId = val;
            return this;
        }
        public Builder merchantId(String val){
            merchantId = val;
            return this;
        }
        public Builder ptsp(String val){
            ptsp = val;
            return this;
        }
        public ArcaMerchAcctInfoTemplate build(){
            return new ArcaMerchAcctInfoTemplate(this);
        }
    }

    public String encode(){
        StringBuilder sb = new StringBuilder();
        StringBuilder sbTemplate = new StringBuilder();
        if(arcaAppId == null || arcaAppId.isEmpty())
            throw new IllegalArgumentException("Merchant Global Unique Id cannot be empty");
        sbTemplate.append("00");
        sbTemplate.append(StringUtils.leftPad(String.valueOf(arcaAppId.length()), 2, "0"));
        sbTemplate.append(arcaAppId);

        if(acquirerId == null || acquirerId.isEmpty())
            throw new IllegalArgumentException("Acquirer Id cannot be empty");
        sbTemplate.append("01");
        sbTemplate.append(StringUtils.leftPad(String.valueOf(acquirerId.length()), 2, "0"));
        sbTemplate.append(acquirerId);

        if(merchantId == null || merchantId.isEmpty())
            throw new IllegalArgumentException("Merchant Id cannot be empty");
        sbTemplate.append("02");
        sbTemplate.append(StringUtils.leftPad(String.valueOf(merchantId.length()), 2, "0"));
        sbTemplate.append(merchantId);

        if(ptsp == null || ptsp.isEmpty())
            throw new IllegalArgumentException("PTSP cannot be empty");
        sbTemplate.append("03");
        sbTemplate.append(StringUtils.leftPad(String.valueOf(ptsp.length()), 2, "0"));
        sbTemplate.append(ptsp);

        sb.append("26");
        sb.append(StringUtils.leftPad(String.valueOf(sbTemplate.toString().length()), 2, "0"));
        sb.append(sbTemplate.toString());

        return sb.toString();
    }

    public String getArcaAppId() {
        return arcaAppId;
    }

    public String getAcquirerId() {
        return acquirerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getPtsp() {
        return ptsp;
    }

    public String getArcaMerchAcctInfoTemplateQrCodeTlv() {
        return arcaMerchAcctInfoTemplateQrCodeTlv;
    }
}
