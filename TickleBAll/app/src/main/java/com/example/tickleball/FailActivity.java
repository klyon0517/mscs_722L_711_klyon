package com.example.tickleball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);
    }

    public void LaunchGame(View view) {
        Intent idleIntent = new Intent(this, GameActivity.class);
        startActivity(idleIntent);
    }

    public void LaunchSuccess(View view) {
        Intent tickleIntent = new Intent(this, SuccessActivity.class);
        startActivity(tickleIntent);
    }

}