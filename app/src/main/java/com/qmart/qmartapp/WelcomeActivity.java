package com.qmart.qmartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void CreateNew(View view){
        Intent intent = new Intent(this, SignActivity.class);
        startActivity(intent);
    }


    public void Login(View view){
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }
}