package com.example.tickleball;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // Pull in game information from DB and assign to variables
    // btn name
    // idle vid
    // success vid
    // fail vid

    String success_type = "head";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    /*
     *  The game information is pulled from MongoDB
     *  Success or Fail scenarios are based on the
     *  button text (determined during the ButtonPress).
     *  If the button text equals the string from the database,
     *  the SuccessActivity Intent is started.
     *  Otherwise, the FailActivity Intent is chosen
     */

    public void ButtonPress(View view) {

        Log.d(LOG_TAG, success_type);

        Button b = (Button) view;
        String btn_txt = b.getText().toString();
        Log.d(LOG_TAG, btn_txt);

        if (success_type.equals(btn_txt)) {

            Intent tickleIntent = new Intent(this, SuccessActivity.class);
            startActivity(tickleIntent);

         } else {

            Intent tickleIntent = new Intent(this, FailActivity.class);
            startActivity(tickleIntent);

         }

    }

}