package com.example.overseasshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEditTextRegisterUsername;
    private EditText mEditTextRegisterPassword;
    private EditText mEditTextRegisterPhone;
    private EditText mEditTextRegisterCreditCardNo;
    private EditText mEditTextRegisterCreditCardExpiryDate;
    private EditText mEditTextRegisterCreditCardCcv;
    private EditText mEditTextRegisterAddress;
    private Button mButtonRegister;

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

        
    }
}
