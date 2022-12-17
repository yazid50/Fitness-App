package com.rifianduo.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class welcome extends AppCompatActivity {

    private Button goLogin,goSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        goLogin = findViewById(R.id.btnGoLogin);
        goSignUp = findViewById(R.id.btnGoSignUp);
        goLogin.setOnClickListener(view -> utils.goAction(welcome.this, welcome.this, Login.class));
        goSignUp.setOnClickListener(view -> utils.goAction(welcome.this, welcome.this, SignUp.class));
    }
}