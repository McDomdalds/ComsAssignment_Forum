package com.example.comsassignment_forum2;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    phpRequest req = new phpRequest();
    public void login(View v) {
        ContentValues cv = new ContentValues();
        EditText uName = (EditText)(findViewById(R.id.editTextUName));
        EditText pWord = (EditText)(findViewById(R.id.editTextPWord));
        cv.put("username",uName.getText().toString());
        cv.put("password",pWord.getText().toString());
        req.doRequest(this, "login.php",cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                processJSON(response);
            }
        });
    }
    public void register(View v) {
        ContentValues cv = new ContentValues();
        EditText uName = (EditText)(findViewById(R.id.editTextUName));
        EditText pWord = (EditText)(findViewById(R.id.editTextPWord));
        cv.put("username",uName.getText().toString());
        cv.put("password",pWord.getText().toString());
        //check username and password
        req.doRequest(this, "register.php",cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                processJSON(response);
            }
        });
    }

    public void processJSON(String json) {
        TextView t = (TextView)(findViewById(R.id.txtDisplay));
        t.setText(json);
    }
}