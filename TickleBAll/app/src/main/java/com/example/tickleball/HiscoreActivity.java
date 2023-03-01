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

        TextView txtView = (TextView) findViewById(R.id.textView4);
        // txtView.setText(str);

        VideoView videoView = findViewById(R.id.videoView3);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setLooping(true);

            }

        });

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.starburst));
        videoView.start();

        JSONArray jArr;

        try {

            jArr = new JSONArray(str);

        } catch (JSONException e) {

            throw new RuntimeException(e);

        }

        StringBuilder oneObjectsItem = new StringBuilder();

        for (int i = 0; i < jArr.length(); i++) {

            // oneObjectsItem = "";
            JSONObject oneObject;

            try {

                oneObject = jArr.getJSONObject(i);
                String usrName = oneObject.getString("usr_name");
                String streak = oneObject.getString("streak");
                String streakDate = oneObject.getString("streak_date_formatted");

                oneObjectsItem.append(usrName).append("  Streak: ").append(streak).append("  ").append(streakDate).append("\n");
                // oneObjectsItem.append(oneObject.getString("usr_name")).append("\n");
                // txtView.setText(oneObjectsItem);

            } catch (JSONException e) {

                throw new RuntimeException(e);

            }

        }

        txtView.setText(oneObjectsItem.toString());

    }

}