package com.rickwan.bankcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rickwan.bankcard.verify.data.Bank;
import com.rickwan.bankcard.verify.utils.BankVerifyUtils;

public class MainActivity extends AppCompatActivity {

    private EditText intputView;

    private TextView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intputView = (EditText) findViewById(R.id.banck_card_et);
        cardView = (TextView) findViewById(R.id.banck_card_tv);

        findViewById(R.id.verify_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });


    }


    private void verify() {

//        String cardNumber = "6225881289141234";

        String input = intputView.getText().toString();

        BankVerifyUtils.verifyBankCard(this, input, new BankVerifyUtils.BankCardVerifyListener() {
            @Override
            public void verify(final Bank bank) {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bank == null) {
                            cardView.setText("银行卡无效");
                        } else {
                            cardView.setText("银行卡类型:" + bank.card_type + "\n银行卡名称:" + bank.bank_name);
                        }
                    }
                });

            }

        });
    }
}
