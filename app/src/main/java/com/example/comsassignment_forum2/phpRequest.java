package com.example.comsassignment_forum2;

import android.app.Activity;
import android.content.ContentValues;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class phpRequest {

    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    String fileName;
    ContentValues param;


    public void doRequest(Activity a, String fileName, ContentValues param, RequestHandler rh){
        this.fileName = fileName;
        this.param = param;
        post("", new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                //e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    final String responseStr = response.body().string();
                    a.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rh.processResponse(responseStr);
                        }
                    });

                } else {
                    System.out.println("Request not successful");
                }
            }
        });
    }

    Call post(String json, Callback callback) {

        RequestBody body = RequestBody.create(JSON, json);

        HttpUrl.Builder httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("lamp.ms.wits.ac.za")
                .addPathSegment("home")
                .addPathSegment("s2305614")
                .addPathSegment(fileName);
        //.addQueryParameter("user_id", "1")
        //.build();

        for (String key: param.keySet()){
            httpUrl.addQueryParameter(key, param.getAsString(key));
        }

        System.out.println(httpUrl.toString());

        Request request = new Request.Builder()
                .url(httpUrl.build())
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
