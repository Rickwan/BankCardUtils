package com.rickwan.bankcard.verify.utils;

import android.content.Context;
import android.text.TextUtils;

import com.rickwan.bankcard.verify.data.Bank;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStreamReader;


public class XMLParserUtils {

    public static final String DICT_TAG = "dict";
    public static final String ARRAY_TAG = "array";

    public interface OnParseBankCallback {
        void onCallback(Bank bank);
    }

    public static void parse(final Context context, final String code, final OnParseBankCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                Bank bank = null;
                try {
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(new InputStreamReader(context.getResources().getAssets().open("banklist.xml")));
                    xpp.next();

                    while (true) {
                        String tag = xpp.getName();
                        int event_type = xpp.getEventType();
                        if (event_type == XmlPullParser.START_TAG && ARRAY_TAG.equals(tag)) {
                        } else if (event_type == XmlPullParser.START_TAG && DICT_TAG.equals(tag)) {
                            bank = Bank.parse(xpp);

                        } else if (event_type == XmlPullParser.END_TAG && ARRAY_TAG.equals(tag)) {
                            break;
                        }
                        if (xpp.getEventType() == XmlPullParser.END_TAG && TextUtils.equals(xpp.getName(), DICT_TAG)) {
                            if (bank != null && bank.contains(code)) {
                                break;
                            } else {
                                bank = null;
                            }
                        }
                        xpp.next();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callback != null) {
                    callback.onCallback(bank);
                }
            }
        }).start();
    }


}
