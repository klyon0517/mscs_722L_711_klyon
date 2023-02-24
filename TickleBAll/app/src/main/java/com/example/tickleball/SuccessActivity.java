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

public class SuccessActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // View imageView;
    // private static final Context context = null;
    String id ="";
    String success_vid = "";
    String url = "http://10.0.2.2/rest_api/files/";
    String videoUrl = "";
    Uri uri = Uri.parse("");
    private static final String TAG = MainActivity.class.getName();
    String dat1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        // ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        // need a way to find the drawable name based on the string
        // imageView.setImageResource(R.drawable.tickle_success);
        VideoView videoView = findViewById(R.id.videoView);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setLooping(true);

            }

        });

        Intent msg_intent = getIntent();
        String str = msg_intent.getStringExtra("message_key");



        // Resources resources = context.getResources();
        // final int resourceId = resources.getIdentifier(str, "drawable", context.getPackageName());
        // String s = String.valueOf(resourceId);

        // @SuppressLint("DiscouragedApi") int imaged = getResources().getIdentifier(str, "drawable", getPackageName());
        // String imageName = getResources().getResourceName(imaged);

        if (str != null) {

            // @SuppressLint("DiscouragedApi") int imaged = getResources().getIdentifier(str, "drawable", getPackageName());
            // String imageName = getResources().getResourceName(imaged);

            // imageView.setImageResource(imaged);

            String[] dataStr = str.split(",", 2);
            id = dataStr[0];
            success_vid = dataStr[1];

            videoUrl = url + success_vid;
            uri = Uri.parse(videoUrl);

            Log.d(LOG_TAG, str);
            Log.d(LOG_TAG, id);
            Log.d(LOG_TAG, success_vid);
            // Log.d(LOG_TAG, imageName);

            // imageView.setBackground(R.drawable.tickle_success);

            videoView.setVideoURI(uri);
            videoView.start();

        }

    }

    public void LaunchGame(View view) {

        String url = "http://10.0.2.2/rest_api/query_next_tickle_vid.php";

        Intent idleIntent = new Intent(this, GameActivity.class);

        //RequestQueue initialized
        RequestQueue queue = Volley.newRequestQueue(this);

        Map<String, String> params = new HashMap();
        params.put("previous_id", id);

        JSONObject parameters = new JSONObject(params);

        // String Request initialized
        // display the response on screen
        // private StringRequest request;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // Toast.makeText(getApplicationContext(), "Response: " + response.toString(), Toast.LENGTH_LONG).show();

                dat1 = response.toString();
                Log.d(TAG, "Response :" + response);

                idleIntent.putExtra("message_key", dat1);
                startActivity(idleIntent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "Error :" + error.toString());
            }
        }); /*{ protected Map<String, String> getParams(){

                Map<String, String> nextTickle = new HashMap<String, String>();
                nextTickle.put("previous_id", id);
                return nextTickle;

            }
        }; */

        queue.add(request);

        // Intent idleIntent = new Intent(this, GameActivity.class);
        // startActivity(idleIntent);
    }

}