package com.example.tickleball;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRequest = (Button) findViewById(R.id.button2);

        btnRequest.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v){

                  sendAndRequestResponse();

              }
          }

        );

        /* dbTest = findViewById(R.id.textView);

        //RequestQueue initialized
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // dbTest.setText("");
                // Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Response: " + response.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                // Log.i(TAG,"Error :" + error.toString());
                dbTest.setText("bummer!");

            }
        }), {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<String, String>();

                param.put("name", tempTxt);
                return param;

            }
        };

        // RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request); */

    }

    private void sendAndRequestResponse() {

        String url = "http://10.0.2.2/rest_api/query_test.php";

        //RequestQueue initialized
        RequestQueue queue = Volley.newRequestQueue(this);

        //String Request initialized
        //display the response on screen
        // private StringRequest request;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                //display the response on screen
                Toast.makeText(getApplicationContext(), "Response: " + response.toString(), Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "Error :" + error.toString());
            }
        });

        queue.add(request);

    }

    public void LaunchGame(View view) {
        Log.d(LOG_TAG, "button clicked");
        Intent idleIntent = new Intent(this, GameActivity.class);
        startActivity(idleIntent);
    }

    /* public void LaunchHiScore(View view) {
        Log.d(LOG_TAG, "HiScore");
        Intent hiscoreIntent = new Intent(this, HiscoreActivity.class);
        startActivity(hiscoreIntent);
    } */

}