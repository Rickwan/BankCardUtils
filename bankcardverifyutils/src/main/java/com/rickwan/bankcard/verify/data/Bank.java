package com.rickwan.bankcard.verify.data;

import android.content.Context;
import android.text.TextUtils;


import com.rickwan.bankcard.verify.utils.XMLParserUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Bank {

    public String belong_code;

    public String bank_name;

    public String card_type;

    public static final String KEY_TAG = "key";

    public static final String STRING_TAG = "string";

    public boolean contains(String code){
        if (TextUtils.isEmpty(belong_code) || TextUtils.isEmpty(code)){
            return false;
        }
        String[] splits = belong_code.split(";");
        for(String split : splits){
            if (split.startsWith(code)){
                return true;
            }
        }
        return false;

    }


    public static Bank parse(XmlPullParser xpp) throws IOException, XmlPullParserException {
        Bank bank = new Bank();
        String key = null;
        while(true){
            String tag = xpp.getName();
            int event_type = xpp.getEventType();

            if(event_type == XmlPullParser.START_TAG && KEY_TAG.equals(tag)) {
                key = xpp.nextText();
            } else if(event_type == XmlPullParser.START_TAG && STRING_TAG.equals(tag)) {

                if (!TextUtils.isEmpty(key)){
                    if (TextUtils.equals(key,"bank_name")){
                        bank.bank_name = xpp.nextText();
                        key = null;
                    } else if (TextUtils.equals(key,"belong_code")){
                        bank.belong_code = xpp.nextText();
                        key = null;
                    }else if (TextUtils.equals(key,"card_type")){
                        bank.card_type = xpp.nextText();
                        key = null;
                    }
                }
            } else if(event_type == XmlPullParser.END_TAG && XMLParserUtils.DICT_TAG.equals(tag)){
                break;
            }

            xpp.next();
        }
        return bank;
    }
}
