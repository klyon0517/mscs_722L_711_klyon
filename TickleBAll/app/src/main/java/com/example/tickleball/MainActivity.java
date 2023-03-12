/*  Tickle Ball

    * Software: Native Android mobile game.
    * Filename: MainActivity.java
    * Author: Kerry Lyon
    * Created: February 1, 2023

    * This file contains the methods for launching the game
    * or showing the hiscore streaks.

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String TAG = MainActivity.class.getName();
    String dat1 = "";
    // MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // music = MediaPlayer.create(MainActivity.this, R.raw.roa_music_carnival);
        // music.start();

        VideoView videoView = findViewById(R.id.videoView2);

        // Sets video to loop
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setLooping(true);

            }

        });

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.starburst));
        videoView.start();

    }

    // Starts the game
    public void LaunchGame(View view) {

        // music.pause();

        Log.d(LOG_TAG, "button clicked");

        String url = "http://10.0.2.2/rest_api/query_tickle_vids.php";
        Intent idleIntent = new Intent(this, GameActivity.class);
        RequestQueue queue = Volley.newRequestQueue(this);

        // Gets the game data using my PHP REST API
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                dat1 = response.toString();
                Log.d(TAG, "Response :" + response);

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

    // Takes the player to the content creation screen.
    public void UploadGame(View view) {

        Log.d(LOG_TAG, "Upload");

        Intent uploadIntent = new Intent(this, UploadActivity.class);
        startActivity(uploadIntent);

    }

    // Shows the top 10 best streaks
    public void LaunchHiScore(View view) {

        Log.d(LOG_TAG, "HiScore");

        String url = "http://10.0.2.2/rest_api/query_hiscore.php";
        Intent hiscoreIntent = new Intent(this, HiscoreActivity.class);
        RequestQueue queue = Volley.newRequestQueue(this);

        // Gets the hiscore streak data using my PHP REST API
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                dat1 = response.toString();
                Log.d(TAG, "Response :" + response);

                // Pass response data to HiscoreActivity
                hiscoreIntent.putExtra("message_key", dat1);
                startActivity(hiscoreIntent);

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "Error :" + error.toString());

            }

        });

        queue.add(request);

    }

}