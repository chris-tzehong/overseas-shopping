package com.example.overseasshopping;

import android.support.v4.app.Fragment;

public class ChatListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ChatListFragment();
    }
}
