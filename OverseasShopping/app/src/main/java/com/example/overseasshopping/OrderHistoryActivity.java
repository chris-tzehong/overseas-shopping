package com.example.overseasshopping;

import android.support.v4.app.Fragment;

public class OrderHistoryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new OrderHistoryFragment();
    }
}