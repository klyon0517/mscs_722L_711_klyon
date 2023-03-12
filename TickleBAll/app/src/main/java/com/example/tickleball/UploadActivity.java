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

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

    }


}