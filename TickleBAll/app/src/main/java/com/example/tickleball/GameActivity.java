/*  Tickle Ball

    * Software: Native Android mobile game.
    * Filename: GameActivity.java
    * Author: Kerry Lyon
    * Created: February 1, 2023

    * This file contains the method for determining the display
    * of the success or fail video.

*/

package com.example.tickleball;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class GameActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // Pull in game information from DB and assign to variables
    // btn name
    // idle vid
    // success vid
    // fail vid

    String success_type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent msg_intent = getIntent();
        String str = msg_intent.getStringExtra("message_key");

        Log.d(LOG_TAG, "Tickle Vids: " + str);

        TextView txtView = (TextView) findViewById(R.id.textView2);

        try {
            success_type = (new JSONObject(str)).getString("tickle_btn");
            Log.d(LOG_TAG, "Btn: " + success_type);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        /* JSONObject jObj;
        try {
            jObj = new JSONObject(str);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        StringBuilder oneObjectsItem = new StringBuilder();

        for (int i = 0; i < jObj.length(); i++) {

            JSONObject oneObject;
            try {
                oneObject = jObj.getJSONObject(i);
                // oneObjectsItem.append(oneObject.getString("tickle_btn")).append("\n");
                success_type = oneObject.getString("tickle_btn");
                // txtView.setText(oneObjectsItem);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        } */

        // txtView.setText(oneObjectsItem.toString());
        txtView.setText(success_type);

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

            String vid_txt = "tickle_success";

            Intent tickleIntent = new Intent(this, SuccessActivity.class);
            tickleIntent.putExtra("message_key", vid_txt);
            startActivity(tickleIntent);

        } else {

            String vid_txt = "tickle_fail";

            Intent tickleIntent = new Intent(this, FailActivity.class);
            tickleIntent.putExtra("message_key", vid_txt);
            startActivity(tickleIntent);

        }


    }

}