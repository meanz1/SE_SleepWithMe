package com.example.lullaby.network;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RequestHttpURLConnection extends Thread {
    public String request(String _url, ContentValues _params){

        HttpURLConnection urlConn = null;
        StringBuffer sbParams = new StringBuffer();

        // 보낼 데이터가 없으면 파라미터를 비운다.
        if (_params == null)
            sbParams.append("");
            // 보낼 데이터가 있으면 파라미터를 채운다.
        else {
            // 파라미터가 2개 이상이면 파라미터 연결에 &가 필요하므로 스위칭할 변수 생성.
            boolean isAnd = false;
            // 파라미터 키와 값.
            String key;
            String value;

            for(Map.Entry<String, Object> parameter : _params.valueSet()){
                key = parameter.getKey();
                value = parameter.getValue().toString();

                // 파라미터가 두개 이상일때, 파라미터 사이에 &를 붙인다.
                if (isAnd)
                    sbParams.append("&");

                sbParams.append(key).append("=").append(value);

                // 파라미터가 2개 이상이면 isAnd를 true로 바꾸고 다음 루프부터 &를 붙인다.
                if (true)
                    if (_params.size() >= 2)
                        isAnd = true;
            }
        }
        Log.d("asdf","parmas: " + sbParams.toString());

        try{
            URL url = new URL(_url + "?" + sbParams.toString());
            urlConn = (HttpURLConnection) url.openConnection();

            urlConn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
            urlConn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
            urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencode");

            // [2-2]. parameter 전달 및 데이터 읽어오기.
//            String strParams = sbParams.toString();
//            OutputStream os = urlConn.getOutputStream();
//            os.write(strParams.getBytes("UTF-8"));
//            os.flush();
//            os.close();

            // 실패 시 null을 리턴하고 메서드를 종료.
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            Log.d("asdf","ㄹㅈㄷ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));

            String line;
            String page = "";

            while ((line = reader.readLine()) != null){
                page += line;
            }

            return page;
        } catch (MalformedURLException e) { // for URL.
            e.printStackTrace();
        } catch (IOException e) { // for openConnection().
            e.printStackTrace();
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
        }

        return null;

    }
}
