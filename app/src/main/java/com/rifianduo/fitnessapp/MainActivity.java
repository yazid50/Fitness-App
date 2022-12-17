package com.rifianduo.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNavigationView();
    }

    private void setNavigationView() {
        navigationView = findViewById(R.id.bottom_navigation);
        fragment = new frag_home(MainActivity.this, MainActivity.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameBase, fragment).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case (R.id.nav_home):
                    fragment = new frag_home(MainActivity.this, MainActivity.this);
                    break;
                case (R.id.nav_parametre):
                    fragment = new frag_settings(MainActivity.this, MainActivity.this);
                    break;
            }

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameBase, fragment).commit();
            } else {
                fragment = new frag_home(MainActivity.this, MainActivity.this);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameBase, fragment).commit();
                navigationView.setSelectedItemId(R.id.nav_home);
            }

            return true;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}