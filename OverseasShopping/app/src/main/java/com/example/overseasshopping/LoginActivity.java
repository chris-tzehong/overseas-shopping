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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.overseasshopping.Model.User;

public class LoginActivity extends AppCompatActivity {

    public static String username;

    private EditText mEditTextLoginUsername;
    private EditText mEditTextLoginPassword;
    private Button mButtonLogin;
    private TextView mTextViewRegisterNew;
    private SpannableString ss;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setBackgroundDrawableResource(R.drawable.login_background);

        mEditTextLoginUsername = (EditText) findViewById(R.id.editTextLoginUserName);
        mEditTextLoginPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        mButtonLogin = (Button) findViewById(R.id.buttonLogin);
        mTextViewRegisterNew = (TextView) findViewById(R.id.textViewRegisterNew);
        db = new DatabaseHelper(this);

//        User user = new User("ki", "ki", "la", "la", 3, 2);
//        db.addUser(user);
//        Product product = new Product("he", "he", "XD", 2, 3);
//        db.addProduct(product, user);

        ss = new SpannableString(getResources().getString(R.string.login_register));
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLUE);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
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
                if (mEditTextLoginUsername.getText().toString().length() == 0 || mEditTextLoginPassword.getText().toString().length() == 0)  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertDialogBuilder.setMessage(getResources().getString(R.string.login_error_empty_username));
                    alertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    try {
                        User user = db.getUser(mEditTextLoginUsername.getText().toString());
                        if (user.getPassword().equals(mEditTextLoginPassword.getText().toString())) {
                            Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
                            i2.putExtra("UserNo", user.getUserNo());
                            Intent i2 = MainActivity.newIntent(LoginActivity.this, mEditTextLoginUsername.getText().toString());
                            startActivity(i2);
                        } else {
                            AlertDialog.Builder alertDialogBuilder_2 = new AlertDialog.Builder(LoginActivity.this);
                            alertDialogBuilder_2.setMessage(getResources().getString(R.string.login_error_incorrect_password));
                            alertDialogBuilder_2.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            AlertDialog alertDialog_2 = alertDialogBuilder_2.create();
                            alertDialog_2.show();
                        }
                    } catch (RuntimeException e1) {
                        e1.printStackTrace();
                        AlertDialog.Builder alertDialogBuilder_3 = new AlertDialog.Builder(LoginActivity.this);
                        alertDialogBuilder_3.setMessage(getResources().getString(R.string.login_error_invalid_username));
                        alertDialogBuilder_3.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alertDialog_3 = alertDialogBuilder_3.create();
                        alertDialog_3.show();
                    }
                }

            }
        });
    }
}
