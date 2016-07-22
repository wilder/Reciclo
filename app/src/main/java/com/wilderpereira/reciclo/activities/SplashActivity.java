package com.wilderpereira.reciclo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: Check Sharedpreferences
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        //TODO: Put login or signup extras
        startActivity(intent);
        finish();
    }
}
