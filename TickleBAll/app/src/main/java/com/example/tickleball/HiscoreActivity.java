/*  Tickle Ball

    * Software: Native Android mobile game.
    * Filename: HiscoreActivity.java
    * Author: Kerry Lyon
    * Created: February 1, 2023

    * This file receives the hiscore streak data and
    * parses it for display.

 */

package com.example.tickleball;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HiscoreActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiscore);

        Intent msg_intent = getIntent();
        String str = msg_intent.getStringExtra("message_key");

        Log.d(LOG_TAG, "hiscore: " + str);

        TextView txtView = findViewById(R.id.streakDataView);
        VideoView videoView = findViewById(R.id.videoView3);

        // Sets video to loop
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setLooping(true);

            }

        });

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.starburst));
        videoView.start();

        JSONArray streakArr;

        try {

            streakArr = new JSONArray(str);

        } catch (JSONException e) {

            throw new RuntimeException(e);

        }

        StringBuilder streakObjectsItem = new StringBuilder();

        // Loop through the array to get individual streaks
        for (int i = 0; i < streakArr.length(); i++) {

            JSONObject streakObject;

            try {

                // parse the JSON object to get individual data
                streakObject = streakArr.getJSONObject(i);
                String usrName = streakObject.getString("usr_name");
                String streak = streakObject.getString("streak");
                // String streakDate = streakObject.getString("streak_date_formatted");

                // streakObjectsItem.append(usrName).append("  Streak: ").append(streak).append("  ").append(streakDate).append("\n");
                streakObjectsItem.append(streak).append("   ").append(usrName).append("\n");

            } catch (JSONException e) {

                throw new RuntimeException(e);

            }

        }

        txtView.setText(streakObjectsItem.toString());

    }

}