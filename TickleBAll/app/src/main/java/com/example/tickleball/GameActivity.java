package com.example.tickleball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    // Need to set this up so it can launch either success or fail
    // based on which button is named as the success button
    // from the database call
    public void LaunchFail(View view) {
        Intent tickleIntent = new Intent(this, FailActivity.class);
        startActivity(tickleIntent);
    }

}