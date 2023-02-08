package com.example.tickleball;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // View imageView;
    // private static final Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        // need a way to find the drawable name based on the string
        // imageView.setImageResource(R.drawable.tickle_success);


        Intent msg_intent = getIntent();
        String str = msg_intent.getStringExtra("message_key");



        // Resources resources = context.getResources();
        // final int resourceId = resources.getIdentifier(str, "drawable", context.getPackageName());
        // String s = String.valueOf(resourceId);

        // @SuppressLint("DiscouragedApi") int imaged = getResources().getIdentifier(str, "drawable", getPackageName());
        // String imageName = getResources().getResourceName(imaged);

        if (str != null) {

            @SuppressLint("DiscouragedApi") int imaged = getResources().getIdentifier(str, "drawable", getPackageName());
            String imageName = getResources().getResourceName(imaged);

            imageView.setImageResource(imaged);

            Log.d(LOG_TAG, str);
            Log.d(LOG_TAG, imageName);

            // imageView.setBackground(R.drawable.tickle_success);

        }

    }

    public void LaunchGame(View view) {
        Intent idleIntent = new Intent(this, GameActivity.class);
        startActivity(idleIntent);
    }

}