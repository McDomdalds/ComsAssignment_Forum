package com.example.comsassignment_forum2;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.*;

public class PostQuestionActivity extends AppCompatActivity {
    phpRequest req = new phpRequest();
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);
        i = getIntent();

    }



    public void postQuestion(View v){
        EditText title = (EditText) findViewById(R.id.editTextTitle);
        EditText question = (EditText) findViewById(R.id.editTextBody);
        ContentValues cv = new ContentValues();
        String user_id = i.getStringExtra("user_id");
        cv.put("user_id", user_id);
        cv.put("title", title.getText().toString());
        cv.put("question", question.getText().toString());
        req.doRequest(PostQuestionActivity.this, "postQuestion.php", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                title.setText("");
                question.setText(response);
                Toast.makeText(PostQuestionActivity.this, response, Toast.LENGTH_LONG).show();
            }
        });
    }


}