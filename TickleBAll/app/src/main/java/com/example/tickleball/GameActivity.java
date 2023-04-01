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
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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
import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    RequestQueue queue;
    JsonObjectRequest request;
    JSONObject parameters;
    Intent tickleIntent;
    Intent failIntent;
    // String url = "http://10.0.2.2/rest_api/files/";
    String url = "http://192.168.1.158/rest_api/files/";
    // String url2 = "http://10.0.2.2/rest_api/update_coin_streak.php";
    String url2 = "http://192.168.1.158/rest_api/update_coin_streak.php";
    String user = "";
    String videoInfo = "";
    String coin = "";
    String streak = "";
    String success_type = "";
    String id ="";
    String idle_vid = "";
    String success_vid = "";
    String fail_vid = "";
    String videoUrl = "";
    Uri uri = Uri.parse("");
    String btn_txt = "";
    String dat1 = "";
    String respSucc = "";
    // Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Intent msg_intent = getIntent();
        String str = msg_intent.getStringExtra("message_key");
        VideoView videoView = findViewById(R.id.videoView);
        TextView coinView = findViewById(R.id.amount1);
        TextView streakView = findViewById(R.id.streak1);

        Log.d(LOG_TAG, "Tickle Vids: " + str);

        try {

            // parse the JSON object to get individual data
            user = (new JSONObject(str)).getString("user");
            streak = (new JSONObject(user)).getString("streak");
            coin = (new JSONObject(user)).getString("coin");
            videoInfo = (new JSONObject(str)).getString("video");
            success_type = (new JSONObject(videoInfo)).getString("tickle_btn");
            id = (new JSONObject(videoInfo)).getString("id");
            idle_vid = (new JSONObject(videoInfo)).getString("idle");
            success_vid = (new JSONObject(videoInfo)).getString("success");
            fail_vid = (new JSONObject(videoInfo)).getString("fail");
            videoUrl = url + idle_vid;
            uri = Uri.parse(videoUrl);

            Log.d(LOG_TAG, "User: " + user);
            Log.d(LOG_TAG, "Video Info: " + videoInfo);
            Log.d(LOG_TAG, "Btn: " + success_type);
            Log.d(LOG_TAG, "Idle URL: " + url + idle_vid);

        } catch (JSONException e) {

            throw new RuntimeException(e);

        }

        videoView.setVideoURI(uri);
        videoView.start();

        coinView.setText(coin);
        streakView.setText(streak);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                if (Objects.equals(btn_txt, "")) {

                    AutoFail();

                }

            }

        }, 5000L);

    }

    public void AutoFail() {

        // Subtract 1 coin for auto fail
        int i = Integer.parseInt(coin);
        if (i != 0) {

            coin = String.valueOf(i - 1);

        }

        streak = "0";

        failIntent = new Intent(GameActivity.this, FailActivity.class);

        queue = Volley.newRequestQueue(this);

        Map<String, String> params = new HashMap();
        params.put("coin", coin);
        params.put("streak", streak);
        parameters = new JSONObject(params);

        // Write coin & streak info to the database.
        request = new JsonObjectRequest(Request.Method.POST, url2, parameters, new Response.Listener<JSONObject>() {

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

                    // if (Objects.equals(btn_txt, "")) {

                        // Pass response data to FailActivity
                        failIntent.putExtra("message_key", id + "," + success_vid + "," + fail_vid + "," + coin);
                        startActivity(failIntent);

                    // }

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

    /*  Success or Fail scenarios are based on the
     *  button text (determined during the ButtonPress).
     *  If the button text equals the string from the database,
     *  the SuccessActivity Intent is started.
     *  Otherwise, the FailActivity Intent is chosen
     */
    public void ButtonPress(View view) {

        // VibrationEffect vibrationEffect;

        ImageButton b = (ImageButton) view;
        btn_txt = b.getContentDescription().toString();

        Log.d(LOG_TAG, success_type);
        Log.d(LOG_TAG, btn_txt);

        if (success_type.equals(btn_txt)) {

            // Set the 'success' vibration effect to EFFECT_TICK
            /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                vibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK);
                vibrator.vibrate(vibrationEffect);

            } */

            // view.setHapticFeedbackEnabled(true);
            // view.performHapticFeedback(HapticFeedbackConstants.CONFIRM);

            int j = Integer.parseInt(coin);
            coin = String.valueOf(j + 3);

            int k = Integer.parseInt(streak);
            streak = String.valueOf(k + 1);

            tickleIntent = new Intent(this, SuccessActivity.class);
            tickleIntent.putExtra("message_key", id + "," + success_vid + "," + coin + "," + streak);
            // startActivity(tickleIntent);

        } else {

            // Set the 'fail' vibration effect to EFFECT_HEAVY_CLICK
            /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                vibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK);
                vibrator.vibrate(vibrationEffect);

            } */

            // view.setHapticFeedbackEnabled(true);
            // view.performHapticFeedback(HapticFeedbackConstants.REJECT);

            streak = "0";

            tickleIntent = new Intent(this, FailActivity.class);
            tickleIntent.putExtra("message_key", id + "," + success_vid + "," + fail_vid + "," + coin);
            // startActivity(tickleIntent);

        }

        queue = Volley.newRequestQueue(this);

        Map<String, String> params = new HashMap();
        params.put("coin", coin);
        params.put("streak", streak);
        parameters = new JSONObject(params);

        // Write coin & streak info to the database.
        request = new JsonObjectRequest(Request.Method.POST, url2, parameters, new Response.Listener<JSONObject>() {

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