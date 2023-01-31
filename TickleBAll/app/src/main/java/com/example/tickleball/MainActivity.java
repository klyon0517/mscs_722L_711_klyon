package com.example.tickleball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LaunchGame(View view) {
        Log.d(LOG_TAG, "button clicked");
        Intent idleIntent = new Intent(this, GameActivity.class);
        startActivity(idleIntent);
    }
}