package com.example.overseasshopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "com.example.overseasshopping.username";

    public static Intent newIntent(Context packageContext, String username) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        return intent;
    }

    FragmentManager fm = getSupportFragmentManager();



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().replace(R.id.main_container, new ProductListFragment()).commit();
                    return true;
                case R.id.navigation_message:
                    fm.beginTransaction().replace(R.id.main_container, new MessageFragment()).commit();
                    return true;
                case R.id.navigation_order_history:
                    return true;
                case R.id.navigation_profile:
                    fm.beginTransaction().replace(R.id.main_container, new ProfileFragment()).commit();
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

        fm.beginTransaction().add(R.id.main_container, new ProductListFragment(), "1").commit();
    }

    //   @Override
//    protected Fragment createFragment(){
//        return new ProductFragment();
//
//    }
}
