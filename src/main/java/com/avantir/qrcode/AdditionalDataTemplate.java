package com.avantir.qrcode;

import java.util.Map;

public class AdditionalDataTemplate extends EmvTlv{

    private String billNumber;
    private String mobileNumber;
    private String storeLabel;
    private String loyaltyNumber;
    private String referenceLabel;
    private String customerLabel;
    private String terminalLabel;
    private String purposeOfTransaction;
    private String additionalCustomerDataRequest;
    private String qrSchemeProvider;
    private String qrSchemeProcessor;
    private String bvn;
    private String billItemURI;
    private String vatTaxRate;
    private String vatTaxAmount;
    private String transactionLocationLong;
    private String transactionLocationLat;
    private String additionalDataTemplate;


    public AdditionalDataTemplate(String additionalDataTemplate) {
        this.additionalDataTemplate = additionalDataTemplate;
        Map tlvMap = decode(this.additionalDataTemplate);
        this.billNumber = tlvMap.containsKey("01") ? tlvMap.get("01").toString() : null;
        this.mobileNumber = tlvMap.containsKey("02") ? tlvMap.get("02").toString() : null;
        this.storeLabel = tlvMap.containsKey("03") ? tlvMap.get("03").toString() : null;
        this.loyaltyNumber = tlvMap.containsKey("04") ? tlvMap.get("04").toString() : null;
        this.referenceLabel = tlvMap.containsKey("05") ? tlvMap.get("05").toString() : null;
        this.customerLabel = tlvMap.containsKey("06") ? tlvMap.get("06").toString() : null;
        this.terminalLabel = tlvMap.containsKey("07") ? tlvMap.get("07").toString() : null;
        this.purposeOfTransaction = tlvMap.containsKey("08") ? tlvMap.get("08").toString() : null;
        this.additionalCustomerDataRequest = tlvMap.containsKey("09") ? tlvMap.get("09").toString() : null;
        this.qrSchemeProvider = tlvMap.containsKey("50") ? tlvMap.get("50").toString() : null;
        this.qrSchemeProcessor = tlvMap.containsKey("51") ? tlvMap.get("51").toString() : null;
        this.bvn = tlvMap.containsKey("52") ? tlvMap.get("52").toString() : null;
        this.billItemURI = tlvMap.containsKey("53") ? tlvMap.get("53").toString() : null;
        this.vatTaxRate = tlvMap.containsKey("54") ? tlvMap.get("54").toString() : null;
        this.vatTaxAmount = tlvMap.containsKey("55") ? tlvMap.get("55").toString() : null;
        this.transactionLocationLong = tlvMap.containsKey("56") ? tlvMap.get("56").toString() : null;
        this.transactionLocationLat = tlvMap.containsKey("57") ? tlvMap.get("57").toString() : null;
    }

    public AdditionalDataTemplate(Builder builder){
        this.billNumber = builder.billNumber;
        this.mobileNumber = builder.mobileNumber;
        this.storeLabel = builder.storeLabel;
        this.loyaltyNumber = builder.loyaltyNumber;
        this.referenceLabel = builder.referenceLabel;
        this.customerLabel = builder.customerLabel;
        this.terminalLabel = builder.terminalLabel;
        this.purposeOfTransaction = builder.purposeOfTransaction;
        this.additionalCustomerDataRequest = builder.additionalCustomerDataRequest;
        this.qrSchemeProvider = builder.qrSchemeProvider;
        this.qrSchemeProcessor = builder.qrSchemeProcessor;
        this.bvn = builder.bvn;
        this.billItemURI = builder.billItemURI;
        this.vatTaxRate = builder.vatTaxRate;
        this.vatTaxAmount = builder.vatTaxAmount;
        this.transactionLocationLong = builder.transactionLocationLong;
        this.transactionLocationLat = builder.transactionLocationLat;
    }

    public static class Builder{

        private String billNumber;
        private String mobileNumber;
        private String storeLabel;
        private String loyaltyNumber;
        private String referenceLabel;
        private String customerLabel;
        private String terminalLabel;
        private String purposeOfTransaction;
        private String additionalCustomerDataRequest;
        private String qrSchemeProvider;
        private String qrSchemeProcessor;
        private String bvn;
        private String billItemURI;
        private String vatTaxRate;
        private String vatTaxAmount;
        private String transactionLocationLong;
        private String transactionLocationLat;

        public Builder billNumber(String val){
            billNumber = val;
            return this;
        }
        public Builder mobileNumber(String val){
            mobileNumber = val;
            return this;
        }
        public Builder storeLabel(String val){
            storeLabel = val;
            return this;
        }
        public Builder loyaltyNumber(String val){
            loyaltyNumber = val;
            return this;
        }
        public Builder referenceLabel(String val){
            referenceLabel = val;
            return this;
        }
        public Builder customerLabel(String val){
            customerLabel = val;
            return this;
        }
        public Builder terminalLabel(String val){
            terminalLabel = val;
            return this;
        }
        public Builder purposeOfTransaction(String val){
            purposeOfTransaction = val;
            return this;
        }
        public Builder additionalCustomerDataRequest(String val){
            additionalCustomerDataRequest = val;
            return this;
        }
        public Builder qrSchemeProvider(String val){
            qrSchemeProvider = val;
            return this;
        }
        public Builder qrSchemeProcessor(String val){
            qrSchemeProcessor = val;
            return this;
        }
        public Builder bvn(String val){
            bvn = val;
            return this;
        }
        public Builder billItemURI(String val){
            billItemURI = val;
            return this;
        }
        public Builder vatTaxRate(String val){
            vatTaxRate = val;
            return this;
        }
        public Builder vatTaxAmount(String val){
            vatTaxAmount = val;
            return this;
        }
        public Builder transactionLocationLong(String val){
            transactionLocationLong = val;
            return this;
        }
        public Builder transactionLocationLat(String val){
            transactionLocationLat = val;
            return this;
        }

        public AdditionalDataTemplate build(){
            return new AdditionalDataTemplate(this);
        }
    }

    public String encode(){
        StringBuilder sb = new StringBuilder();
        StringBuilder sbTemplate = new StringBuilder();
        if(billNumber != null && !billNumber.isEmpty()){
            sbTemplate.append("01");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(billNumber.length()), 2, "0"));
            sbTemplate.append(billNumber);
        }
        if(mobileNumber != null && !mobileNumber.isEmpty()){
            sbTemplate.append("02");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(mobileNumber.length()), 2, "0"));
            sbTemplate.append(mobileNumber);
        }
        if(storeLabel != null && !storeLabel.isEmpty()){
            sbTemplate.append("03");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(storeLabel.length()), 2, "0"));
            sbTemplate.append(storeLabel);
        }
        if(loyaltyNumber != null && !loyaltyNumber.isEmpty()){
            sbTemplate.append("04");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(loyaltyNumber.length()), 2, "0"));
            sbTemplate.append(loyaltyNumber);
        }
        if(referenceLabel != null && !referenceLabel.isEmpty()){
            sbTemplate.append("05");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(referenceLabel.length()), 2, "0"));
            sbTemplate.append(referenceLabel);
        }
        if(customerLabel != null && !customerLabel.isEmpty()){
            sbTemplate.append("06");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(customerLabel.length()), 2, "0"));
            sbTemplate.append(customerLabel);
        }
        if(terminalLabel != null && !terminalLabel.isEmpty()){
            sbTemplate.append("07");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(terminalLabel.length()), 2, "0"));
            sbTemplate.append(terminalLabel);
        }
        if(purposeOfTransaction != null && !purposeOfTransaction.isEmpty()){
            sbTemplate.append("08");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(purposeOfTransaction.length()), 2, "0"));
            sbTemplate.append(purposeOfTransaction);
        }
        if(additionalCustomerDataRequest != null && !additionalCustomerDataRequest.isEmpty()){
            sbTemplate.append("09");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(additionalCustomerDataRequest.length()), 2, "0"));
            sbTemplate.append(additionalCustomerDataRequest);
        }
        if(qrSchemeProvider != null && !qrSchemeProvider.isEmpty()){
            sbTemplate.append("50");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(qrSchemeProvider.length()), 2, "0"));
            sbTemplate.append(qrSchemeProvider);
        }
        if(qrSchemeProcessor != null && !qrSchemeProcessor.isEmpty()){
            sbTemplate.append("51");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(qrSchemeProcessor.length()), 2, "0"));
            sbTemplate.append(qrSchemeProcessor);
        }
        if(bvn != null && !bvn.isEmpty()){
            sbTemplate.append("52");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(bvn.length()), 2, "0"));
            sbTemplate.append(bvn);
        }
        if(billItemURI != null && !billItemURI.isEmpty()){
            sbTemplate.append("53");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(billItemURI.length()), 2, "0"));
            sbTemplate.append(billItemURI);
        }
        if(vatTaxRate != null && !vatTaxRate.isEmpty()){
            sbTemplate.append("54");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(vatTaxRate.length()), 2, "0"));
            sbTemplate.append(vatTaxRate);
        }
        if(vatTaxAmount != null && !vatTaxAmount.isEmpty()){
            sbTemplate.append("55");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(vatTaxAmount.length()), 2, "0"));
            sbTemplate.append(vatTaxAmount);
        }
        if(transactionLocationLong != null && !transactionLocationLong.isEmpty()){
            sbTemplate.append("56");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(transactionLocationLong.length()), 2, "0"));
            sbTemplate.append(transactionLocationLong);
        }
        if(transactionLocationLat != null && !transactionLocationLat.isEmpty()){
            sbTemplate.append("57");
            sbTemplate.append(StringUtils.leftPad(String.valueOf(transactionLocationLat.length()), 2, "0"));
            sbTemplate.append(transactionLocationLat);
        }

        sb.append("62");
        sb.append(StringUtils.leftPad(String.valueOf(sbTemplate.toString().length()), 2, "0"));
        sb.append(sbTemplate.toString());

        return sb.toString();
    }


}
