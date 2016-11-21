package com.rickwan.bankcard.verify.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.rickwan.bankcard.verify.data.Bank;

/**
 * author 万强
 * date 16/11/15 上午11:08
 * desc ${银行卡验证工具类}
 */
public class BankVerifyUtils {

    private static int DEFAULT_COUNT = 6;//默认6位开始验证银行卡


    public static  void verifyBankCard(Context context,String cardNumber, final BankCardVerifyListener verifyListener) {


        if (verifyListener == null) {
            return;
        }
        if (TextUtils.isEmpty(cardNumber)) {
            verifyListener.verify(null);
            return;
        }

        int length = cardNumber.trim().length();

        if (length < DEFAULT_COUNT) {
            verifyListener.verify(null);
            return;
        }


        XMLParserUtils.parse(context, cardNumber.substring(0, DEFAULT_COUNT), new XMLParserUtils.OnParseBankCallback() {
            @Override
            public void onCallback(final Bank bank) {

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        verifyListener.verify(bank);

                    }
                });

            }
        });

    }

    public interface BankCardVerifyListener {
        void verify(Bank bank);
    }




}