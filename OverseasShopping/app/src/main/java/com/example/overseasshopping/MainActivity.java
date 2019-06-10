package com.example.overseasshopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "com.example.overseasshopping.username";
    public static final String EXTRA_USER_NO = "com.example.overseasshopping.user_no";

    public static Intent newIntent(Context packageContext, String username, int user_no) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        intent.putExtra(EXTRA_USER_NO, user_no);
        return intent;
    }

    final Fragment mHomeFragment = new ProductListFragment();
    final Fragment mProfileFragment = new ProfileFragment();
    final Fragment mMessageFragment = new MessageListFragment();
    final FragmentManager fm = getSupportFragmentManager();

    Fragment active = mHomeFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(mHomeFragment).commit();
                    active = mHomeFragment;
                    return true;
                case R.id.navigation_message:
                    fm.beginTransaction().hide(active).show(mMessageFragment).commit();
                    active = mMessageFragment;
                    return true;
                case R.id.navigation_order_history:
                    return true;
                case R.id.navigation_profile:
                    fm.beginTransaction().hide(active).show(mProfileFragment).commit();
                    active = mProfileFragment;
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, mProfileFragment, "2").hide(mProfileFragment).commit();
        fm.beginTransaction().add(R.id.main_container, mHomeFragment, "1").commit();
        fm.beginTransaction().add(R.id.main_container, mMessageFragment, "3").hide(mMessageFragment).commit();
    }


//   @Override
//    protected Fragment createFragment(){
//        return new ProductFragment();
//
//    }
}
