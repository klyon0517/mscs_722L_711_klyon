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
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {

    ArFragment arFragment;
    private ModelRenderable archerElfRenderable,
                            dwarfAxeRenderable,
                            dwarfBombRenderable,
                            goblinRenderable,
                            horseKnightRenderable,
                            knightRenderable,
                            orcRenderable,
                            trollRenderable,
                            wizardRenderable;

    ImageView dwarfAxe, dwarfBomb, elf, goblin, horseKnight, knight, orc, troll, wizard;

    View[] arrayView;
    ViewRenderable miniature_name;
    int selected = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load model.glb from assets folder or http url
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        // Only worked from the assets folder
        // arFragment.setOnTapPlaneGlbModel("archer_elf.glb");

        // Set miniature views
        dwarfAxe = (ImageView)findViewById(R.id.dwarfAxe);
        dwarfBomb = (ImageView)findViewById(R.id.dwarfBomb);
        elf = (ImageView)findViewById(R.id.elf);
        goblin = (ImageView)findViewById(R.id.goblin);
        horseKnight = (ImageView)findViewById(R.id.horseKnight);
        knight = (ImageView)findViewById(R.id.knight);
        orc = (ImageView)findViewById(R.id.orc);
        troll = (ImageView)findViewById(R.id.troll);
        wizard = (ImageView)findViewById(R.id.wizard);

        setArrayView();
        setClickListener();
        setUpModel();

        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {

                // Tap to add miniature
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                createModel(anchorNode, selected);

            }

        });
    }

    private void setUpModel() {

        ModelRenderable.builder()
                .setSource(this, R.raw.archer_elf)
                .build().thenAccept(renderable -> archerElfRenderable = renderable)
                .exceptionally(

                        throwable -> {

                            Toast.makeText(this, "Unable to load Elf miniature.", Toast.LENGTH_SHORT).show();
                            return null;

                        }

                );

        ModelRenderable.builder()
                .setSource(this, R.raw.dwarf_axe)
                .build().thenAccept(renderable -> dwarfAxeRenderable = renderable)
                .exceptionally(

                        throwable -> {

                            Toast.makeText(this, "Unable to load Dwarf Axe miniature.", Toast.LENGTH_SHORT).show();
                            return null;

                        }

                );

        ModelRenderable.builder()
                .setSource(this, R.raw.dwarf_bomb)
                .build().thenAccept(renderable -> dwarfBombRenderable = renderable)
                .exceptionally(

                        throwable -> {

                            Toast.makeText(this, "Unable to load Dwarf Bomb miniature.", Toast.LENGTH_SHORT).show();
                            return null;

                        }

                );

        ModelRenderable.builder()
                .setSource(this, R.raw.goblin)
                .build().thenAccept(renderable -> goblinRenderable = renderable)
                .exceptionally(

                        throwable -> {

                            Toast.makeText(this, "Unable to load Goblin miniature.", Toast.LENGTH_SHORT).show();
                            return null;

                        }

                );

        ModelRenderable.builder()
                .setSource(this, R.raw.horse_knight)
                .build().thenAccept(renderable -> horseKnightRenderable = renderable)
                .exceptionally(

                        throwable -> {

                            Toast.makeText(this, "Unable to load Horse Knight miniature.", Toast.LENGTH_SHORT).show();
                            return null;

                        }

                );

        ModelRenderable.builder()
                .setSource(this, R.raw.knight)
                .build().thenAccept(renderable -> knightRenderable = renderable)
                .exceptionally(

                        throwable -> {

                            Toast.makeText(this, "Unable to load Knight miniature.", Toast.LENGTH_SHORT).show();
                            return null;

                        }

                );

        ModelRenderable.builder()
                .setSource(this, R.raw.orc)
                .build().thenAccept(renderable -> orcRenderable = renderable)
                .exceptionally(

                        throwable -> {

                            Toast.makeText(this, "Unable to load Orc miniature.", Toast.LENGTH_SHORT).show();
                            return null;

                        }

                );

        ModelRenderable.builder()
                .setSource(this, R.raw.troll)
                .build().thenAccept(renderable -> trollRenderable = renderable)
                .exceptionally(

                        throwable -> {

                            Toast.makeText(this, "Unable to load Troll miniature.", Toast.LENGTH_SHORT).show();
                            return null;

                        }

                );

        ModelRenderable.builder()
                .setSource(this, R.raw.wizard)
                .build().thenAccept(renderable -> wizardRenderable = renderable)
                .exceptionally(

                        throwable -> {

                            Toast.makeText(this, "Unable to load Wizard miniature.", Toast.LENGTH_SHORT).show();
                            return null;

                        }

                );

    }

    private void createModel(AnchorNode anchorNode, int selected) {

        // Default miniature is the Knight
        if (selected == 1) {

            TransformableNode knight = new TransformableNode(arFragment.getTransformationSystem());
            knight.setParent(anchorNode);
            knight.setRenderable(knightRenderable);
            knight.select();

        }

    }

    private void setClickListener() {

        for(int i = 0; i<arrayView.length; i++) {

            arrayView[i].setOnClickListener(this);

            /* arrayView[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); */

        }

    }

    private void setArrayView() {

        arrayView = new View[]{
                dwarfAxe, dwarfBomb, elf, goblin, horseKnight, knight, orc, troll, wizard
        };

    }

    public void onClick(View view) {



    }

    /* @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

        super.onPointerCaptureChanged(hasCapture);

    } */

}