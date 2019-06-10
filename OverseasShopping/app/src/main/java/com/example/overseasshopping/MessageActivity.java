package com.example.overseasshopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {

    public static final String EXTRA_OTHERUSER_NO = "com.example.overseasshopping.otheruser_no";

    public static Intent newIntent(Context packageContext, int otherUser_no) {
        Intent intent = new Intent(packageContext, MessageFragment.class);
        intent.putExtra(EXTRA_OTHERUSER_NO, otherUser_no);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new MessageFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
