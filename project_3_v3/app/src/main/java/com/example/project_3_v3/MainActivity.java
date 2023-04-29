/*  Project 3 ARCore

 * Software: Native Android ARCore Demo.
 * Filename: MainActivity.java
 * Author: Kerry Lyon
 * Created: April 2023

 * This file contains the methods for selecting and
 * viewing 3D tabletop miniatures and placing
 * them on the table map, rolling the D20, and
 * cloud anchors.

 */



package com.example.project_3_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.ar.sceneform.ux.ArFragment;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    ArFragment arFragment;
    String package_name;
    String view_id;
    int delayTime = 20;
    int rollAnimations = 40;
    int[] d20Images = new int[]{R.drawable.d20_1,
                                R.drawable.d20_2,
                                R.drawable.d20_3,
                                R.drawable.d20_4,
                                R.drawable.d20_5,
                                R.drawable.d20_6,
                                R.drawable.d20_7,
                                R.drawable.d20_8,
                                R.drawable.d20_9,
                                R.drawable.d20_10,
                                R.drawable.d20_11,
                                R.drawable.d20_12,
                                R.drawable.d20_13,
                                R.drawable.d20_14,
                                R.drawable.d20_15,
                                R.drawable.d20_16,
                                R.drawable.d20_17,
                                R.drawable.d20_18,
                                R.drawable.d20_19,
                                R.drawable.d20_20};
    Random random = new Random();
    ImageView d20;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d20 = findViewById(R.id.d20_dice);
        mp = MediaPlayer.create(this, R.raw.d20_roll);

        // Load model.glb from assets folder
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        // arFragment.setOnTapPlaneGlbModel("archer_elf.glb");
        // Set the default miniature as the knight
        setModel();

    }

    // Model is selected based on the ID name of the ImageView.
    public void modelPick(View view) {

        package_name = view.getResources().getResourceName(view.getId());
        // Separate the ID name from the resource string
        view_id = package_name.substring(28);
        Log.d(LOG_TAG, "ID: " + view_id);

        switch (view_id) {

            case "dwarfAxe":

                arFragment.setOnTapPlaneGlbModel("dwarf_axe.glb");
                break;

            case "dwarfBomb":

                arFragment.setOnTapPlaneGlbModel("dwarf_bomb.glb");
                break;

            case "elf":

                arFragment.setOnTapPlaneGlbModel("archer_elf.glb");
                break;

            case "goblin":

                arFragment.setOnTapPlaneGlbModel("goblin.glb");
                break;

            case "horseKnight":

                arFragment.setOnTapPlaneGlbModel("horse_knight.glb");
                break;

            case "knight":

                arFragment.setOnTapPlaneGlbModel("knight.glb");
                break;

            case "orc":

                arFragment.setOnTapPlaneGlbModel("orc.glb");
                break;

            case "troll":

                arFragment.setOnTapPlaneGlbModel("troll.glb");
                break;

            case "wizard":

                arFragment.setOnTapPlaneGlbModel("wizard.glb");
                break;

        }

    }

    private void setModel() {

        arFragment.setOnTapPlaneGlbModel("knight.glb");

    }

    public void rollDice(View view) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < rollAnimations; i++) {

                    int dice = random.nextInt(20) + 1;
                    d20.setImageResource(d20Images[dice - 1]);
                    try {

                        Thread.sleep(delayTime);

                    } catch (InterruptedException e) {

                        Log.d(LOG_TAG, "Error: " + e);

                    }

                }

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        if (mp != null) {

            mp.start();

        }

    }

}