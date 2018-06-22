package com.avantir.qrcode;

import com.avantir.qrcode.generator.AdditionalDataTemplate;
import com.avantir.qrcode.generator.ArcaMerchAcctInfoTemplate;
import com.avantir.qrcode.generator.EmvQrTlv;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.File;
import java.io.FileOutputStream;

public class Application {

    public static void main(String[] args){
        try{
            // Total length cannot be more than 99
            ArcaMerchAcctInfoTemplate arcaMerchantAccountInfo = new ArcaMerchAcctInfoTemplate.Builder()
                    .arcaAppId("ARCA0001")
                    .acquirerId("627058")
                    .merchantId("MERCHANT0001")
                    .ptsp("CoralPay")
                    .build();

            // Total length cannot be more than 99
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

            // Total length (including ArcaMerchAcctInfoTemplate & AdditionalDataTemplate) cannot be more than 99
            EmvQrTlv emvQrTlv = new EmvQrTlv.Builder()
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
                    .convenienceIndicator(EmvQrTlv.ConvenienceFeeIndicator.PERENTAGE_CONVENIENCE_FEE)
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
