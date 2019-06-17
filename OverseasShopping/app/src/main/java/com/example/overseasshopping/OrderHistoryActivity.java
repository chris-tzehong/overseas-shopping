package com.example.overseasshopping;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import static com.example.overseasshopping.MainActivity.EXTRA_USERNAME;

public class OrderHistoryActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext, String user_name) {
        Intent intent = new Intent(packageContext, OrderHistoryActivity.class);
        intent.putExtra(EXTRA_USERNAME, user_name);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
//        String user_name =  getIntent().getStringExtra(EXTRA_USERNAME);
//        return new OrderHistoryFragment().newInstance(user_name);
        return new OrderHistoryFragment();
    }
}