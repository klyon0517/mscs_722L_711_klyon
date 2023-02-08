package com.example.tickleball;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FailActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

        ImageView imageView = (ImageView)findViewById(R.id.imageView3);

        Intent msg_intent = getIntent();
        String str = msg_intent.getStringExtra("message_key");

        if (str != null) {

            @SuppressLint("DiscouragedApi") int imaged = getResources().getIdentifier(str, "drawable", getPackageName());
            String imageName = getResources().getResourceName(imaged);

            imageView.setImageResource(imaged);

            Log.d(LOG_TAG, str);
            Log.d(LOG_TAG, imageName);

        }

    }

    public void LaunchGame(View view) {
        Intent idleIntent = new Intent(this, GameActivity.class);
        startActivity(idleIntent);
    }

    public void LaunchSuccess(View view) {

        String vid_txt = "tickle_success";

        Intent tickleIntent = new Intent(this, SuccessActivity.class);
        // add code to pass the str via an intent
        // str should be ticle_success always since it's the winning activity
        // Otherwise, the image will not show
        tickleIntent.putExtra("message_key", vid_txt);
        startActivity(tickleIntent);

    }

}