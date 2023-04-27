/*  Project 3 ARCore

 * Software: Native Android ARCore Demo.
 * Filename: MainActivity.java
 * Author: Kerry Lyon
 * Created: April 2023

 * This file contains the methods for selecting and
 * viewing 3D tabletop miniatures and placing
 * them on the table map.

 */



package com.example.project_3_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.ar.sceneform.ux.ArFragment;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    ArFragment arFragment;
    String package_name;
    String view_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}