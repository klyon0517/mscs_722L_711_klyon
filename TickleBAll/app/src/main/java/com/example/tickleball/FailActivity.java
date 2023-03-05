/*  Tickle Ball

    * Software: Native Android mobile game.
    * Filename: FailActivity.java
    * Author: Kerry Lyon
    * Created: February 1, 2023

    * This file receives the fail video data and
    * contains the method for launching the next game
    * or viewing the success video.

 */

package com.example.tickleball;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class FailActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    String id ="";
    String success_vid = "";
    String fail_vid = "";
    String url = "http://10.0.2.2/rest_api/files/";
    String videoUrl = "";
    Uri uri = Uri.parse("");
    private static final String TAG = MainActivity.class.getName();
    String dat1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

        VideoView videoView = findViewById(R.id.videoView);

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

            String[] dataStr = str.split(",", 3);
            id = dataStr[0];
            success_vid = dataStr[1];
            fail_vid = dataStr[2];

            videoUrl = url + fail_vid;
            uri = Uri.parse(videoUrl);

            Log.d(LOG_TAG, str);
            Log.d(LOG_TAG, id);
            Log.d(LOG_TAG, success_vid);
            Log.d(LOG_TAG, fail_vid);

            videoView.setVideoURI(uri);
            videoView.start();

        }

    }

    // Starts a new game
    public void LaunchGame(View view) {

        String url = "http://10.0.2.2/rest_api/query_next_tickle_vid.php";
        Intent idleIntent = new Intent(this, GameActivity.class);
        RequestQueue queue = Volley.newRequestQueue(this);

        Map<String, String> params = new HashMap();
        params.put("previous_id", id);

        JSONObject parameters = new JSONObject(params);

        // Gets the game data using my PHP REST API
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                dat1 = response.toString();
                Log.d(TAG, "Response :" + response.toString());

                // Pass response data to GameActivity
                idleIntent.putExtra("message_key", dat1);
                startActivity(idleIntent);

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "Error :" + error.toString());

            }

        });

        queue.add(request);

    }

    /*  If the player wants to see the
     *  success video, he / she can click
     *  the coin button to spend currency
     *  to view it.
     */
    public void LaunchSuccess(View view) {

        String vid_txt = id + "," + success_vid;

        Intent tickleIntent = new Intent(this, SuccessActivity.class);

        // Pass response data to SuccessActivity
        tickleIntent.putExtra("message_key", vid_txt);
        startActivity(tickleIntent);

    }

}