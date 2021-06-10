package com.example.comsassignment_forum2;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class questionLayout extends LinearLayout {
    TextView title;
    TextView votes;
    TextView question;
    TextView username;

    public questionLayout(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);

        title = new TextView(context);
        votes = new TextView(context);
        question = new TextView(context);
        username = new TextView(context);

        title.setTextSize(22);
        title.setPadding(20,20,20,20);
        addView(title);

        LinearLayout details = new LinearLayout(context);
        details.setOrientation(LinearLayout.HORIZONTAL);
        username.setPadding(20,20,20,20);
        votes.setPadding(20,20,20,20);
        details.addView(username);
        details.addView(votes);
        addView(details);

        LinearLayout body = new LinearLayout(context);
        body.setOrientation(LinearLayout.HORIZONTAL);
        question.setPadding(20,20,20,20);
        body.addView(question);
        addView(body);

    }

    public void populate(JSONObject jsonObject){
        try {
            String titleStr = jsonObject.getString("TITLE" /*Name of column*/);
            int votesNum= jsonObject.getInt("VOTES" /*Name of column*/);
            String questionStr= jsonObject.getString("QUESTION" /*Name of column*/);
            String usernameStr= jsonObject.getString("USERNAME" /*Name of column*/);

            title.setText(titleStr);
            votes.setText("Votes: " + votesNum);
            question.setText(questionStr);
            username.setText(usernameStr+ "\t");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
