package com.example.comsassignment_forum2;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListQuestionsActivity extends AppCompatActivity {
    Intent i;
    phpRequest req = new phpRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);
        i = getIntent();
        ContentValues cv = new ContentValues();
        cv.put("attribute", "VOTES");
        req.doRequest(ListQuestionsActivity.this, "listQuestions.php", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                processList(response);
            }
        });
    }


    public void processList(String input){
        LinearLayout mainLayout = findViewById(R.id.questionListLayout);
        LinearLayout container = new LinearLayout(this);
        container.removeAllViews();

        try {
            JSONArray jsonArray = new JSONArray(input);
            for (int i = 0; i< jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                questionLayout ql = new questionLayout(this);
                ql.populate(jsonObject);
                //String field = jsonObject.getString("number" /*Name of column*/);
                //TextView t = new TextView(this);
                //t.setText(field);
                if (i%2==0){
                    ql.setBackgroundColor(Color.parseColor("#EEEEEE"));
                }
//                cl.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            Toast.makeText(MainActivity.this, "This car is " + jsonObject.getString("age") + " years old", Toast.LENGTH_LONG).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
                container.setOrientation(LinearLayout.VERTICAL);
                container.addView(ql);
            }
            mainLayout.addView(container);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}