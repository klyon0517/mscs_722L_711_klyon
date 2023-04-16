package com.example.project_3_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.ar.sceneform.ux.ArFragment;

public class MainActivity extends AppCompatActivity {

    ArFragment arFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load model.glb from assets folder or http url
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        arFragment.setOnTapPlaneGlbModel("pixel_tank_sm.glb");

    }
}