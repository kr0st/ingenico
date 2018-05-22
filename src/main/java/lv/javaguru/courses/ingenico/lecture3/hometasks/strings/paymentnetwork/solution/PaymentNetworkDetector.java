package lv.javaguru.courses.ingenico.lecture3.hometasks.strings.paymentnetwork.solution;

public class PaymentNetworkDetector {

    /*
    * Detect payment network by PAN
    *  - ignore PAN validation, assume they are correct
    *  - return PaymentNetwork.UNKNOWN if payment network not detected
    * Bin ranges : https://www.bincodes.com/bin-list/
    * */
    public PaymentNetwork detectByPan(String pan){
        if (pan == null || pan.isEmpty() || !pan.matches("\\d+")){
            throw new IllegalArgumentException("PAN cannot be null, empty, must contain numbers only");
        }

        int bin = Integer.parseInt(pan.substring(0, 6));
        int panLength = pan.length();

        if (isMastercard(bin, panLength)){
            return PaymentNetwork.MASTERCARD;
        }
        if (isVisa(bin, panLength)){
            return PaymentNetwork.VISA;
        }
        if (isAmex(bin, panLength)){
            return PaymentNetwork.AMEX;
        }
        return PaymentNetwork.UNKNOWN;
    }

    private boolean isMastercard(int bin, int panLength) {
        return panLength == 16
                && ((bin >= 2221_00 && bin <= 2720_99) || (bin >= 51_0000 && bin <= 55_9999));
    }

    private boolean isVisa(int bin, int panLength) {
        return (panLength == 13 || panLength == 16 || panLength == 19)
                && bin >= 4_00000 && bin <= 4_99999;

    }

    private boolean isAmex(int bin, int panLength){
        if (panLength != 15){
            return false;
        }
        return bin >= 34_0000 && bin <= 37_9999;
    }



}
