package com.rifianduo.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class startScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        new Handler().postDelayed(() -> {
            if (prefUser.getInstance(startScreen.this).getBoolean(prefUser.isUserLogin)){
                utils.goAction(startScreen.this, startScreen.this, MainActivity.class);
            }else {
                utils.goAction(startScreen.this, startScreen.this, welcome.class);
            }
        },2500);
    }
}