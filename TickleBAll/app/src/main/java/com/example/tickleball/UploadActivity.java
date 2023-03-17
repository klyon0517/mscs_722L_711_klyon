/*  Tickle Ball

 * Software: Native Android mobile game.
 * Filename: UploadActivity.java
 * Author: Kerry Lyon
 * Created: March 12 2023

 * This file contains the methods for accessing the camera,
 * allowing players to create their own content.
 * It creates DB entries for the player and uploads the videos & info.

 */

package com.example.tickleball;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
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

public class UploadActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    Spinner tickleChoice;
    String dat1 = "";
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        tickleChoice = findViewById(R.id.tickleSpinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.tickle_spots, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tickleChoice.setAdapter(adapter);

        VideoView videoView = findViewById(R.id.videoView7);

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

    public void UploadVids(View view) {

        Log.d(LOG_TAG, "Upload Vids");

        EditText usrName = findViewById(R.id.editUsername);
        // EditText tickleSpot = findViewById(R.id.editTickleSpot);

        String user = usrName.getText().toString();
        // String tickle = tickleSpot.getText().toString();
        String tickle = tickleChoice.getSelectedItem().toString();

        if (!user.equals("") && !tickle.equals("")) {

            Log.d(LOG_TAG, user);
            Log.d(LOG_TAG, tickle);

            // String url = "http://10.0.2.2/rest_api/query_upload_vids.php";
            String url = "http://192.168.1.158/rest_api/query_upload_vids.php";
            RequestQueue queue = Volley.newRequestQueue(this);
            Map<String, String> params = new HashMap();
            params.put("usr_name", user);
            params.put("tickle_btn", tickle);

            JSONObject parameters = new JSONObject(params);

            Log.d(LOG_TAG, parameters.toString());

            // Gets the game data using my PHP REST API
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    dat1 = response.toString();

                    try {

                        message = (new JSONObject(dat1)).getString("message");

                    } catch (JSONException e) {

                        throw new RuntimeException(e);

                    }

                    Log.d(LOG_TAG, "Response : " + message);

                    Context context = getApplicationContext();
                    CharSequence text = message;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d(LOG_TAG, "Error :" + error.toString());

                }

            });

            queue.add(request);

        } else {

            Log.d(LOG_TAG, "Empty values");

        }

    }


}