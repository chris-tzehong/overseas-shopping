package com.example.overseasshopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static com.example.overseasshopping.MainActivity.EXTRA_USER_NO;

public class MessageActivity extends SingleFragmentActivity {

    private static final String EXTRA_OTHERUSER_NO = "com.example.overseasshopping.otheruser_no";

    public static Intent newIntent(Context packageContext, int user_no, int otherUser_no) {
        Intent intent = new Intent(packageContext, MessageActivity.class);
        intent.putExtra(EXTRA_OTHERUSER_NO, otherUser_no);
        intent.putExtra(EXTRA_USER_NO, user_no);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        int user_no = (int) getIntent().getIntExtra(EXTRA_USER_NO, 1);
        int otherUser_no = (int) getIntent().getIntExtra(EXTRA_OTHERUSER_NO, 2);
        Log.d("MessageActivity", user_no + "/" + otherUser_no);
        return new MessageFragment().newInstance(user_no, otherUser_no);

    }
}
