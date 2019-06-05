package com.example.overseasshopping;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final Fragment mHomeFragment = new ProductListFragment();
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
                    return true;
                case R.id.navigation_order_history:
                    return true;
                case R.id.navigation_profile:
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

        fm.beginTransaction().add(R.id.main_container, mHomeFragment, "1").commit();
    }


//   @Override
//    protected Fragment createFragment(){
//        return new ProductFragment();
//
//    }
}
