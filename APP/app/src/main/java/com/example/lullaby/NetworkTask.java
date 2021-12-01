package com.example.lullaby;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

public class NetworkTask extends AsyncTask<Void, Void, String> {

    private String url;
    private ContentValues values;

    public NetworkTask(String url, ContentValues values) {

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
        AccountData.getInstance().setIdx(arStr[0]);
        AccountData.getInstance().setUserId(arStr[1]);
        AccountData.getInstance().setName(arStr[2]);
        AccountData.getInstance().setGender(arStr[3]);
    }
}
