package com.example.overseasshopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.overseasshopping.Model.User;

import javax.xml.datatype.Duration;

public class LoginActivity extends AppCompatActivity {

    public static String username = "user";
    public static String password = "user";

    private EditText mEditTextLoginUsername;
    private EditText mEditTextLoginPassword;
    private Button mButtonLogin;
    private TextView mTextViewRegisterNew;
    private SpannableString ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextLoginUsername = (EditText) findViewById(R.id.editTextLoginUserName);
        mEditTextLoginPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        mButtonLogin = (Button) findViewById(R.id.buttonLogin);
        mTextViewRegisterNew = (TextView) findViewById(R.id.textViewRegisterNew);

        ss = new SpannableString(getResources().getString(R.string.login_register));
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLUE);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(LoginActivity.this, "Hello", Toast.LENGTH_LONG).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 23, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcs, 23, 36, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTextViewRegisterNew.setText(ss);
        mTextViewRegisterNew.setMovementMethod(LinkMovementMethod.getInstance());
        mTextViewRegisterNew.setHighlightColor(Color.TRANSPARENT);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(i);
                if (mEditTextLoginUsername.getText().toString().length() == 0) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertDialogBuilder.setMessage(getResources().getString(R.string.login_empty_username));
                    alertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
//                    try {
//                        User user = DatabaseHelper.getUser
//                    }
                }

            }
        });
    }
}
