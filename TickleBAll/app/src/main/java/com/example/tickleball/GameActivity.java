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
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class GameActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    String success_type = "";
    String id ="";
    String idle_vid = "";
    String success_vid = "";
    String fail_vid = "";
    String url = "http://10.0.2.2/rest_api/files/";
    String videoUrl = "";
    Uri uri = Uri.parse("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent msg_intent = getIntent();
        String str = msg_intent.getStringExtra("message_key");
        VideoView videoView = findViewById(R.id.videoView);

        Log.d(LOG_TAG, "Tickle Vids: " + str);

        try {

            // parse the JSON object to get individual data
            success_type = (new JSONObject(str)).getString("tickle_btn");
            id = (new JSONObject(str)).getString("id");
            idle_vid = (new JSONObject(str)).getString("idle");
            success_vid = (new JSONObject(str)).getString("success");
            fail_vid = (new JSONObject(str)).getString("fail");
            videoUrl = url + idle_vid;
            uri = Uri.parse(videoUrl);

            Log.d(LOG_TAG, "Btn: " + success_type);
            Log.d(LOG_TAG, "Idle URL: " + url + idle_vid);

        } catch (JSONException e) {

            throw new RuntimeException(e);

        }

        videoView.setVideoURI(uri);
        videoView.start();

    }

    /*  Success or Fail scenarios are based on the
     *  button text (determined during the ButtonPress).
     *  If the button text equals the string from the database,
     *  the SuccessActivity Intent is started.
     *  Otherwise, the FailActivity Intent is chosen
     */
    public void ButtonPress(View view) {

        Button b = (Button) view;
        String btn_txt = b.getText().toString();

        Log.d(LOG_TAG, success_type);
        Log.d(LOG_TAG, btn_txt);

        if (success_type.equals(btn_txt)) {

            Intent tickleIntent = new Intent(this, SuccessActivity.class);
            tickleIntent.putExtra("message_key", id + "," + success_vid);
            startActivity(tickleIntent);

        } else {

            Intent tickleIntent = new Intent(this, FailActivity.class);
            tickleIntent.putExtra("message_key", id + "," + success_vid + "," + fail_vid);
            startActivity(tickleIntent);

        }


    }

}