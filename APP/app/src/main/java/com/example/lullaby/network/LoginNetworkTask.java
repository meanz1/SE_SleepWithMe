package com.example.lullaby.network;

import android.content.ContentValues;
import android.os.AsyncTask;
import com.example.lullaby.data.AccountData;

public class LoginNetworkTask extends AsyncTask<Void, Void, String> {

    private String url;
    private ContentValues values;

    public boolean success = false;

    public LoginNetworkTask(String url, ContentValues values) {

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
        AccountData.getInstance().setIdx(Integer.parseInt(arStr[0]));
        if(Integer.parseInt(arStr[0]) == -1) return;
        AccountData.getInstance().setUserId(arStr[1]);
        AccountData.getInstance().setName(arStr[2]);
        AccountData.getInstance().setGender(arStr[3]);
        AccountData.getInstance().setAge(Integer.parseInt(arStr[4]));
        AccountData.getInstance().setCategory1(arStr[5]);
        AccountData.getInstance().setCategory2(arStr[6]);
        success = true;
    }
}