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
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    String success_type = "";
    String id ="";
    String idle_vid = "";
    String success_vid = "";
    String fail_vid = "";
    // String url = "http://10.0.2.2/rest_api/files/";
    String url = "http://192.168.1.158/rest_api/files/";
    String videoUrl = "";
    Uri uri = Uri.parse("");
    String btn_txt = "";
    // Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

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

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent failIntent = new Intent(GameActivity.this, FailActivity.class);
                failIntent.putExtra("message_key", id + "," + success_vid + "," + fail_vid);

                if (Objects.equals(btn_txt, "")) {

                    startActivity(failIntent);

                }

            }

        }, 5000L);

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

            Intent tickleIntent = new Intent(this, SuccessActivity.class);
            tickleIntent.putExtra("message_key", id + "," + success_vid);
            startActivity(tickleIntent);

        } else {

            // Set the 'fail' vibration effect to EFFECT_HEAVY_CLICK
            /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                vibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK);
                vibrator.vibrate(vibrationEffect);

            } */

            // view.setHapticFeedbackEnabled(true);
            // view.performHapticFeedback(HapticFeedbackConstants.REJECT);

            Intent tickleIntent = new Intent(this, FailActivity.class);
            tickleIntent.putExtra("message_key", id + "," + success_vid + "," + fail_vid);
            startActivity(tickleIntent);

        }

    }

}