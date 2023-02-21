package com.example.tickleball;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // View imageView;
    // private static final Context context = null;
    String id ="";
    String success_vid = "";
    String url = "http://10.0.2.2/rest_api/files/";
    String videoUrl = "";
    Uri uri = Uri.parse("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        // ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        // need a way to find the drawable name based on the string
        // imageView.setImageResource(R.drawable.tickle_success);
        VideoView videoView = findViewById(R.id.videoView);

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
        Intent idleIntent = new Intent(this, GameActivity.class);
        startActivity(idleIntent);
    }

}