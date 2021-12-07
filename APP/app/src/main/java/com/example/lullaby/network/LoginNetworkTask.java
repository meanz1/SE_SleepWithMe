package com.example.lullaby.network;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lullaby.MainActivity;
import com.example.lullaby.data.AccountData;
import com.example.lullaby.data.Profile;

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
        AccountData.getInstance().profiles.clear();
        Log.d("asdf", s);
        String[] arStr = s.split("\\s");
        int n = Integer.parseInt((arStr[0]));
        if(n < 1) return;
        for(int i = 0; i < n; i++) {
            int idx = Integer.parseInt(arStr[11*i + 1]);
            String userId = arStr[11*i + 2];
            String name = arStr[11*i + 3];
            String gender = arStr[11*i + 4];
            int age = Integer.parseInt(arStr[11*i + 5]);
            String category1 = arStr[11*i + 6];
            String category2 = arStr[11*i + 7];
            int targetSleep = Integer.parseInt(arStr[11*i + 8]);
            int iteration = Integer.parseInt(arStr[11*i + 9]);
            int frequency = Integer.parseInt(arStr[11*i + 10]);
            int minWake = Integer.parseInt(arStr[11*i + 11]);
            AccountData.getInstance().profiles.add(new Profile(idx, userId, name, gender, age, category1, category2, targetSleep, iteration, frequency ,minWake));
        }
        AccountData.getInstance().setUserSelected(0);
        success = true;
    }
}