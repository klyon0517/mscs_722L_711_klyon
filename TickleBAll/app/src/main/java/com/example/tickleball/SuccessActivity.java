/*  Tickle Ball

    * Software: Native Android mobile game.
    * Filename: SuccessActivity.java
    * Author: Kerry Lyon
    * Created: February 1, 2023

    * This file receives the success video data and
    * contains the method for launching the next game.

 */

package com.example.tickleball;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SuccessActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    Intent idleIntent;
    Uri uri = Uri.parse("");
    // String url = "http://10.0.2.2/rest_api/files/";
    String url = "http://192.168.1.158/rest_api/files/";
    // String url2 = "http://10.0.2.2/rest_api/query_next_tickle_vid.php";
    String url2 = "http://192.168.1.158/rest_api/query_next_tickle_vid.php";
    String id ="";
    String success_vid = "";
    String streak = "";
    String coin = "";
    String videoUrl = "";
    String dat1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        VideoView videoView = findViewById(R.id.videoView);
        TextView coinView = findViewById(R.id.amount2);
        TextView streakView = findViewById(R.id.streak2);

        // Sets video to loop
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setLooping(true);

            }

        });

        Intent msg_intent = getIntent();
        String str = msg_intent.getStringExtra("message_key");

        // Parse the data if it has been received
        if (str != null) {

            String[] dataStr = str.split(",", 4);
            id = dataStr[0];
            success_vid = dataStr[1];
            coin = dataStr[2];
            streak = dataStr[3];

            videoUrl = url + success_vid;
            uri = Uri.parse(videoUrl);

            Log.d(LOG_TAG, str);
            Log.d(LOG_TAG, id);
            Log.d(LOG_TAG, success_vid);
            Log.d(LOG_TAG, coin);

            videoView.setVideoURI(uri);
            videoView.start();

            coinView.setText(coin);
            streakView.setText(streak);

        }

    }

    // Starts a new game
    public void LaunchGame(View view) {

        idleIntent = new Intent(this, GameActivity.class);

        RequestQueue queue = Volley.newRequestQueue(this);

        Map<String, String> params = new HashMap();
        params.put("previous_id", id);
        params.put("coin", coin);
        params.put("streak", streak);
        JSONObject parameters = new JSONObject(params);

        // Gets the game data using my PHP REST API
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url2, parameters, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                dat1 = response.toString();
                Log.d(LOG_TAG, "Response: " + response);

                // Pass response data to GameActivity
                idleIntent.putExtra("message_key", dat1);
                startActivity(idleIntent);

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(LOG_TAG, "Error: " + error.toString());

            }

        });

        queue.add(request);

    }

}