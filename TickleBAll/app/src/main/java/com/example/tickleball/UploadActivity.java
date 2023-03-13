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

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

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


}