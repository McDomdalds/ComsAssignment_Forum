package com.example.comsassignment_forum2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void postQuestion(View v){
        EditText user_id = findViewById(R.id.userIDEnter);
        Intent i = new Intent(MainActivity.this, PostQuestionActivity.class);
        i.putExtra("user_id", user_id.getText());
        startActivity(i);
    }

    public void listQuestions(View v){
        EditText user_id = findViewById(R.id.userIDEnter);
        Intent i = new Intent(MainActivity.this, ListQuestionsActivity.class);
        i.putExtra("user_id", user_id.getText());
        startActivity(i);
    }
}