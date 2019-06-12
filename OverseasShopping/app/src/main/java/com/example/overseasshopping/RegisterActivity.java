package com.example.overseasshopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.overseasshopping.Model.Message;
import com.example.overseasshopping.Model.User;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

        mWarningIcon.setBounds(0,0, mWarningIcon.getIntrinsicWidth(), mWarningIcon.getIntrinsicHeight());


        mEditTextRegisterUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEditTextRegisterUsername.getText().toString().isEmpty()) {
                    mEditTextRegisterUsername.setError(getResources().getString(R.string.register_error_empty_username), mWarningIcon);
                } else {
                    String mUsername = mEditTextRegisterUsername.getText().toString();
                    if (mDatabaseHelper.checkUser(mUsername)) {
                        mEditTextRegisterUsername.setError(getResources().getString(R.string.register_error_username_exist), mWarningIcon);
                    }
                }
            }
        });

        mEditTextRegisterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEditTextRegisterPassword.getText().toString().isEmpty()) {
                    mEditTextRegisterPassword.setError(getResources().getString(R.string.register_error_empty_password), mWarningIcon);
                } else if (mEditTextRegisterPassword.getText().toString().length() <= 8 || mEditTextRegisterPassword.getText().toString().length() >= 20) {
                    mEditTextRegisterPassword.setError(getResources().getString(R.string.register_error_password_length), mWarningIcon);
                }
            }
        });

        mEditTextRegisterPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEditTextRegisterPhone.getText().toString().isEmpty()) {
                    mEditTextRegisterPhone.setError(getResources().getString(R.string.register_error_empty_phone), mWarningIcon);
                }
            }
        });

        mEditTextRegisterCreditCardNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEditTextRegisterCreditCardNo.getText().toString().isEmpty()) {
                    mEditTextRegisterCreditCardNo.setError(getResources().getString(R.string.register_error_empty_credit_card_no), mWarningIcon);
                }
            }
        });

        mEditTextRegisterCreditCardExpiryDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEditTextRegisterCreditCardExpiryDate.getText().toString().isEmpty()) {
                    mEditTextRegisterCreditCardExpiryDate.setError(getResources().getString(R.string.register_error_empty_credit_card_expiry_date), mWarningIcon);
                } else if (mEditTextRegisterCreditCardExpiryDate.getText().toString().length() != 5) {
                    mEditTextRegisterCreditCardExpiryDate.setError(getResources().getString(R.string.register_error_invalid_date_format), mWarningIcon);
                } else if (mEditTextRegisterCreditCardExpiryDate.getText().toString().charAt(2) != '/') {
                    mEditTextRegisterCreditCardExpiryDate.setError(getResources().getString(R.string.register_error_invalid_date_format), mWarningIcon);
                }

            }
        });

        mEditTextRegisterCreditCardCcv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEditTextRegisterCreditCardCcv.getText().toString().isEmpty()) {
                    mEditTextRegisterCreditCardCcv.setError(getResources().getString(R.string.register_error_empty_credit_card_ccv), mWarningIcon);
                } else if (mEditTextRegisterCreditCardCcv.getText().toString().length() != 3) {
                    mEditTextRegisterCreditCardCcv.setError(getResources().getString(R.string.register_error_invalid_credit_card_ccv), mWarningIcon);
                }
            }
        });

        mEditTextRegisterAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEditTextRegisterAddress.getText().toString().isEmpty()) {
                    mEditTextRegisterAddress.setError(getResources().getString(R.string.register_error_empty_address), mWarningIcon);
                }
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextRegisterUsername.getError() != null || mEditTextRegisterPassword.getError() != null || mEditTextRegisterPhone.getError() != null ||
                mEditTextRegisterCreditCardNo.getError() != null || mEditTextRegisterCreditCardExpiryDate.getError() != null ||
                mEditTextRegisterCreditCardCcv.getError() != null || mEditTextRegisterAddress.getError() != null) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialogBuilder.setMessage(R.string.register_error_empty_invalid_field);
                    alertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    User user = new User();
                    user.setUsername(mEditTextRegisterUsername.getText().toString());
                    user.setPassword(mEditTextRegisterPassword.getText().toString());
                    user.setTelephone(mEditTextRegisterPhone.getText().toString());

                    user.setCreditCardNo(mEditTextRegisterCreditCardNo.getText().toString());
                    user.setExpiryDate(mEditTextRegisterCreditCardExpiryDate.getText().toString());

                    user.setSecurityNo(Integer.parseInt(mEditTextRegisterCreditCardCcv.getText().toString()));
                    user.setAddress(mEditTextRegisterAddress.getText().toString());
                    mDatabaseHelper.addUser(user);

                    AlertDialog.Builder alertDialogBuilder_2 = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialogBuilder_2.setMessage(R.string.register_successful);
                    alertDialogBuilder_2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                    });
                    AlertDialog alertDialog_2 = alertDialogBuilder_2.create();
                    alertDialog_2.show();
                }
            }
        });




    }

}
