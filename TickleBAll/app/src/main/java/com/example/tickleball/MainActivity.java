package com.example.tickleball;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // private static final String TAG = MainActivity.class.getName();
    // private StringRequest stringRequest;

    TextView dbTest;
    String url = "http://localhost/rest_api/query_test.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbTest = findViewById(R.id.textView);

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
        }); /*{

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<String, String>();

                param.put("name", tempTxt);
                return param;

            }
        }; */

        // RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }

    public void LaunchGame(View view) {
        Log.d(LOG_TAG, "button clicked");
        Intent idleIntent = new Intent(this, GameActivity.class);
        startActivity(idleIntent);
    }

    public void LaunchHiScore(View view) {
        Log.d(LOG_TAG, "HiScore");
        Intent hiscoreIntent = new Intent(this, HiscoreActivity.class);
        startActivity(hiscoreIntent);
    }

}