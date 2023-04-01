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
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FailActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    RequestQueue queue;
    JsonObjectRequest request;
    JSONObject parameters;
    Intent idleIntent;
    Intent tickleIntent;
    Uri uri = Uri.parse("");
    // String url = "http://10.0.2.2/rest_api/files/";
    String url = "http://192.168.1.158/rest_api/files/";
    // String url2 = "http://10.0.2.2/rest_api/query_next_tickle_vid.php";
    String url2 = "http://192.168.1.158/rest_api/query_next_tickle_vid.php";
    // String url3 = "http://10.0.2.2/rest_api/update_coin_streak.php";
    String url3 = "http://192.168.1.158/rest_api/update_coin_streak.php";
    String id ="";
    String success_vid = "";
    String fail_vid = "";
    String coin = "";
    String streak = "0";
    String videoUrl = "";
    String vid_txt;
    String dat1 = "";
    String respSucc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

        VideoView videoView = findViewById(R.id.videoView);
        TextView coinView = findViewById(R.id.amount3);
        TextView streakView = findViewById(R.id.streak3);

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
            fail_vid = dataStr[2];
            coin = dataStr[3];

            videoUrl = url + fail_vid;
            uri = Uri.parse(videoUrl);

            Log.d(LOG_TAG, str);
            Log.d(LOG_TAG, id);
            Log.d(LOG_TAG, success_vid);
            Log.d(LOG_TAG, fail_vid);
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

        queue = Volley.newRequestQueue(this);

        Map<String, String> params = new HashMap();
        params.put("previous_id", id);
        params.put("coin", coin);
        params.put("streak", streak);
        parameters = new JSONObject(params);

        // Gets the game data using my PHP REST API
        request = new JsonObjectRequest(Request.Method.POST, url2, parameters, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                dat1 = response.toString();
                Log.d(LOG_TAG, "Response: " + dat1);

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

    /*  If the player wants to see the
     *  success video, he / she can click
     *  the coin button to spend currency
     *  to view it.
     */
    public void LaunchSuccess(View view) {

        int i = Integer.parseInt(coin);
        if (i != 0) {

            coin = String.valueOf(i - 1);

        }

        streak = "0";

        vid_txt = id + "," + success_vid + "," + coin + "," + streak;

        tickleIntent = new Intent(this, SuccessActivity.class);

        queue = Volley.newRequestQueue(this);

        Map<String, String> params = new HashMap();
        params.put("coin", coin);
        params.put("streak", streak);
        parameters = new JSONObject(params);

        // Write coin & streak info to the database.
        request = new JsonObjectRequest(Request.Method.POST, url3, parameters, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                dat1 = response.toString();

                try {

                    respSucc = (new JSONObject(dat1)).getString("response");

                } catch (JSONException e) {

                    throw new RuntimeException(e);

                }

                Log.d(LOG_TAG, "Response: " + respSucc);

                // If DB update is successful, continue game
                if (respSucc.equals("success")) {

                    // Pass response data to SuccessActivity
                    tickleIntent.putExtra("message_key", vid_txt);
                    startActivity(tickleIntent);

                }

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