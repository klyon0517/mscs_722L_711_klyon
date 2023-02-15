package com.example.tickleball;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HiscoreActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiscore);

        Intent msg_intent = getIntent();
        String str = msg_intent.getStringExtra("message_key");

        Log.d(LOG_TAG, "hiscore: " + str);

        TextView txtView = (TextView) findViewById(R.id.textView4);
        // txtView.setText(str);

        JSONArray jArr;
        try {
            jArr = new JSONArray(str);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        StringBuilder oneObjectsItem = new StringBuilder();

        for (int i = 0; i < jArr.length(); i++) {

            // oneObjectsItem = "";
            JSONObject oneObject;
            try {
                oneObject = jArr.getJSONObject(i);
                oneObjectsItem.append(oneObject.getString("usr_name")).append("\n");
                // txtView.setText(oneObjectsItem);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }

        txtView.setText(oneObjectsItem.toString());

    }

}