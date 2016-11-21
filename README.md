# BankCardUtils
## Description：

   verify BankCard information.

## To get a Git project into your build:

- Step 1. Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

- Step 2. Add the dependency：
```
  dependencies {
	    compile 'com.github.Rickwan:BankCardUtils:V1.0.0'
	}
```
------
## for exmaple:
```
     BankVerifyUtils.verifyBankCard(this, input, new BankVerifyUtils.BankCardVerifyListener() {
            @Override
            public void verify(Bank bank) {
                if (bank == null) {
                    cardView.setText("银行卡无效");
                } else {
                    cardView.setText("银行卡类型:" + bank.card_type + "\n银行卡名称:" + bank.bank_name);
                }
            }

        });
```
