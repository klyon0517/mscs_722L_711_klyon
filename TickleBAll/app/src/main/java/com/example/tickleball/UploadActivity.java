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

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UploadActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static int CAMERA_PERMISSION_CODE = 100;
    ActivityResultLauncher<Intent> activityResultLauncher;
    VideoView videoView;
    TextView idleVideoName;
    TextView successVidName;
    TextView failVidName;
    Spinner tickleChoice;
    Uri uri;
    Cursor returnCursor;
    String dat1 = "";
    String message = "";
    String vidFileName = "";
    String idleVidFileName = "";
    String successVidFileName = "";
    String failVidFileName = "";
    String vidFileSize = "";
    String mimeType = "";
    String btn_txt = "";
    int nameIndex;
    int sizeIndex;
    // Uri videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        tickleChoice = findViewById(R.id.tickleSpinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.tickle_spots, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tickleChoice.setAdapter(adapter);

        videoView = findViewById(R.id.videoView7);

        // Sets video to loop
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setLooping(true);

            }

        });

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.starburst));
        videoView.start();

        if (cameraExists()) {

            Log.d(LOG_TAG, "Camera Detected");
            cameraPermission();

        } else {

            Log.d(LOG_TAG, "No Camera");

        }

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                            // Get information about the video file.
                            Intent videoPath = result.getData();
                            uri = videoPath.getData();
                            mimeType = getContentResolver().getType(uri);
                            returnCursor =
                                    getContentResolver().query(uri, null, null, null, null);
                            nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                            sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                            returnCursor.moveToFirst();
                            vidFileName = returnCursor.getString(nameIndex);
                            vidFileSize = Long.toString(returnCursor.getLong(sizeIndex));
                            // int foo = Integer.parseInt(vidFileSize);
                            // File file = new File(path);

                            // Convert to bytes, then string

                            // String s = new String(bytes, StandardCharsets.UTF_8);

                            switch (btn_txt) {

                                case "record idle vid btn":

                                    idleVideoName = findViewById(R.id.idleVidName);
                                    idleVideoName.setText(vidFileName);
                                    idleVidFileName = vidFileName;
                                    break;

                                case "record success vid btn":

                                    successVidName = findViewById(R.id.successVidName);
                                    successVidName.setText(vidFileName);
                                    successVidFileName = vidFileName;
                                    break;

                                case "record fail vid btn":

                                    failVidName = findViewById(R.id.failVidName);
                                    failVidName.setText(vidFileName);
                                    failVidFileName = vidFileName;
                                    break;

                            }

                            Log.d(LOG_TAG, "Video has been recorded. And is available here: " + uri.toString());
                            Log.d(LOG_TAG, uri.toString());
                            Log.d(LOG_TAG, vidFileName);
                            Log.d(LOG_TAG, vidFileSize);
                            Log.d(LOG_TAG, mimeType);
                            // Log.d(LOG_TAG, s);

                            videoView.start();

                        } else if (result.getResultCode() == RESULT_CANCELED) {

                            Log.d(LOG_TAG, "Video has been cancelled.");
                            videoView.start();

                        } else {

                            Log.d(LOG_TAG, "Video Error!");
                            videoView.start();

                        }

                    }

                }

        );

    }

    public void RecordVid(View view) {

        Button b = (Button) view;
        btn_txt = b.getContentDescription().toString();

        Log.d(LOG_TAG, btn_txt);

        // recordVideo();
        Intent recordIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        activityResultLauncher.launch(recordIntent);

    }

    private boolean cameraExists() {

        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {

            return true;

        } else {

            return false;

        }

    }

    private void cameraPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);

        }

    }

    /* private void recordVideo() {

        Intent recordIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        activityResultLauncher.launch(recordIntent);

    } */

    public void UploadVids(View view) {

        Log.d(LOG_TAG, "Upload Vids");

        EditText usrName = findViewById(R.id.editUsername);
        // EditText tickleSpot = findViewById(R.id.editTickleSpot);

        String user = usrName.getText().toString();
        // String tickle = tickleSpot.getText().toString();
        String tickle = tickleChoice.getSelectedItem().toString();

        if (!user.equals("") && !tickle.equals("")) {

            Log.d(LOG_TAG, user);
            Log.d(LOG_TAG, tickle);

            // String url = "http://10.0.2.2/rest_api/query_upload_vids.php";
            String url = "http://192.168.1.158/rest_api/query_upload_vids.php";
            RequestQueue queue = Volley.newRequestQueue(this);
            Map<String, String> params = new HashMap();
            params.put("usr_name", user);
            params.put("tickle_btn", tickle);
            params.put("idle", idleVidFileName);
            // params.put("idle_video", uri.toString());
            params.put("success", successVidFileName);
            // params.put("success_video", uri.toString());
            params.put("fail", failVidFileName);
            // params.put("fail_video", uri.toString());

            JSONObject parameters = new JSONObject(params);

            Log.d(LOG_TAG, parameters.toString());

            // Gets the game data using my PHP REST API
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    dat1 = response.toString();

                    try {

                        message = (new JSONObject(dat1)).getString("message");

                    } catch (JSONException e) {

                        throw new RuntimeException(e);

                    }

                    Log.d(LOG_TAG, "Response : " + message);

                    Context context = getApplicationContext();
                    CharSequence text = message;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d(LOG_TAG, "Error :" + error.toString());

                }

            });

            queue.add(request);

        } else {

            Log.d(LOG_TAG, "Empty values");

        }

    }


}