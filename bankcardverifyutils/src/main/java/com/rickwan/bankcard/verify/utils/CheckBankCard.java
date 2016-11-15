package com.rickwan.bankcard.verify.utils;

/**
 * author 万强
 * date 16/11/15 上午11:07
 * desc ${TODO}
 */

public class CheckBankCard {
    public CheckBankCard() {
    }

    public static boolean checkBank(String cardNumber) {
        if(cardNumber.length() != 16 && cardNumber.length() != 19) {
            System.out.println("卡号位数无效");
            return false;
        } else if(!checkBankCard(cardNumber)) {
            System.out.println("卡号校验失败");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isUnionPayCard(String cardNumber) {
        return !checkBank(cardNumber)?false:cardNumber.startsWith("62");
    }

    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        return bit == 78?false:cardId.charAt(cardId.length() - 1) == bit;
    }

    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if(nonCheckCodeCardId != null && nonCheckCodeCardId.trim().length() != 0 && nonCheckCodeCardId.matches("\\d+")) {
            char[] chs = nonCheckCodeCardId.trim().toCharArray();
            int luhmSum = 0;
            int i = chs.length - 1;

            for(int j = 0; i >= 0; ++j) {
                int k = chs[i] - 48;
                if(j % 2 == 0) {
                    k *= 2;
                    k = k / 10 + k % 10;
                }

                luhmSum += k;
                --i;
            }

            return luhmSum % 10 == 0?'0':(char)(10 - luhmSum % 10 + 48);
        } else {
            return 'N';
        }
    }
}
