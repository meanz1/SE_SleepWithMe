package com.example.lullaby.network;

import android.content.ContentValues;
import android.os.AsyncTask;
import com.example.lullaby.data.AccountData;

public class SignUpNetworkTask extends AsyncTask<Void, Void, String>{
    private String url;
    private ContentValues values;

    public boolean success = false;

    public SignUpNetworkTask(String url, ContentValues values) {

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
        if(Integer.parseInt(s) == -1) return;
        success = true;
    }
}
