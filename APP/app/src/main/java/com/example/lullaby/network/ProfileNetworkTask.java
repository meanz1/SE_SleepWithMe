package com.example.lullaby.network;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lullaby.data.AccountData;
import com.example.lullaby.data.Profile;

public class ProfileNetworkTask extends AsyncTask<Void, Void, String> {
    private String url;
    private ContentValues values;

    public boolean success = false;

    public ProfileNetworkTask(String url, ContentValues values) {

        this.url = url;
        this.values = values;
    }

    @Override
    protected String doInBackground(Void... params) {

        String result; // 요청 결과를 저장할 변수.
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url, values);

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        String[] arStr = s.split("\\s");
        int n = Integer.parseInt((arStr[0]));
        if(n < 1) return;
        int idx = Integer.parseInt(arStr[0]);
        String userId = arStr[1];
        String name = arStr[2];
        String gender = arStr[3];
        int age = Integer.parseInt(arStr[4]);
        String category1 = arStr[5];
        String category2 = arStr[6];
        AccountData.getInstance().profiles.add(new Profile(idx, userId, name, gender, age, category1, category2));
        success = true;
    }

}
