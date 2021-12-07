package com.example.lullaby.network;

import android.content.ContentValues;
import android.os.AsyncTask;

import com.example.lullaby.data.AccountData;
import com.example.lullaby.data.Profile;

public class SettingNetworkTask extends AsyncTask<Void, Void, String> {
    private String url;
    private ContentValues values;

    public boolean success = false;

    public SettingNetworkTask(String url, ContentValues values) {

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
        success = true;
    }
}
