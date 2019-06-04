package com.example.overseasshopping;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.overseasshopping.Model.CreditCard;
import com.example.overseasshopping.Model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEditTextRegisterUsername;
    private EditText mEditTextRegisterPassword;
    private EditText mEditTextRegisterPhone;
    private EditText mEditTextRegisterCreditCardNo;
    private EditText mEditTextRegisterCreditCardExpiryDate;
    private EditText mEditTextRegisterCreditCardCcv;
    private EditText mEditTextRegisterAddress;
    private Button mButtonRegister;
    private Drawable mWarningIcon;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEditTextRegisterUsername = (EditText) findViewById(R.id.editTextRegisterUsername);
        mEditTextRegisterPassword = (EditText) findViewById(R.id.editTextRegisterPassword);
        mEditTextRegisterPhone = (EditText) findViewById(R.id.editTextRegisterPhone);
        mEditTextRegisterCreditCardNo = (EditText) findViewById(R.id.editTextRegisterCreditCardNo);
        mEditTextRegisterCreditCardExpiryDate = (EditText) findViewById(R.id.editTextRegisterCreditCardExpiryDate);
        mEditTextRegisterCreditCardCcv = (EditText) findViewById(R.id.editTextRegisterCreditCardCcv);
        mEditTextRegisterAddress = (EditText) findViewById(R.id.editTextRegisterAddress);
        mButtonRegister = (Button) findViewById(R.id.buttonRegister);
        mWarningIcon = (Drawable) getResources().getDrawable(R.drawable.ic_alert_red_icon);
        mDatabaseHelper = new DatabaseHelper(this);
        User user = new User();
        CreditCard creditCard = new CreditCard();

        mWarningIcon.setBounds(0,0, mWarningIcon.getIntrinsicWidth(), mWarningIcon.getIntrinsicHeight());

        mEditTextRegisterUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mEditTextRegisterUsername.getText().toString().isEmpty()) {
                        mEditTextRegisterUsername.setError(getResources().getString(R.string.register_error_empty_username), mWarningIcon);
                    }
                }
            }
        });

        mEditTextRegisterPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mEditTextRegisterPassword.getText().toString().isEmpty()) {
                        mEditTextRegisterPassword.setError(getResources().getString(R.string.register_error_empty_password), mWarningIcon);
                    } else if (mEditTextRegisterPassword.getText().toString().length() <= 8 || mEditTextRegisterPassword.getText().toString().length() >= 20) {
                        mEditTextRegisterPassword.setError(getResources().getString(R.string.register_error_password_length), mWarningIcon);
                    } else if (!passwordValidation("(?=.*\\d)", mEditTextRegisterPassword.getText().toString())) {
                        mEditTextRegisterPassword.setError(getResources().getString(R.string.register_error_password_no_number), mWarningIcon);
                    }
                }
            }
        });

        mEditTextRegisterPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mEditTextRegisterPhone.getText().toString().isEmpty()) {
                        mEditTextRegisterPhone.setError(getResources().getString(R.string.register_error_empty_phone), mWarningIcon);
                    }
                }
            }
        });

        mEditTextRegisterCreditCardNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mEditTextRegisterCreditCardNo.getText().toString().isEmpty()) {
                        mEditTextRegisterCreditCardNo.setError(getResources().getString(R.string.register_error_empty_credit_card_no), mWarningIcon);
                    }
                }
            }
        });

        mEditTextRegisterCreditCardExpiryDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mEditTextRegisterCreditCardExpiryDate.getText().toString().isEmpty()) {
                        mEditTextRegisterCreditCardExpiryDate.setError(getResources().getString(R.string.register_error_empty_credit_card_expiry_date), mWarningIcon);
                    }
                }
            }
        });

        mEditTextRegisterCreditCardCcv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mEditTextRegisterCreditCardCcv.getText().toString().isEmpty()) {
                        mEditTextRegisterCreditCardCcv.setError(getResources().getString(R.string.register_error_empty_credit_card_ccv), mWarningIcon);
                    }
                }
            }
        });

        mEditTextRegisterAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mEditTextRegisterAddress.getText().toString().isEmpty()) {
                        mEditTextRegisterAddress.setError(getResources().getString(R.string.register_error_empty_address), mWarningIcon);
                    }
                }
            }
        });




    }

    private boolean passwordValidation(String regex, String password) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }
}
