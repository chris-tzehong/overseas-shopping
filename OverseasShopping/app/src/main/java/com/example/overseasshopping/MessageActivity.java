package com.example.overseasshopping;

import android.support.v4.app.Fragment;

public class MessageActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MessageFragment();
    }

}