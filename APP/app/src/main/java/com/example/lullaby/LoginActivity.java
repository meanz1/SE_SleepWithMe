package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lullaby.videos.AsmrPagerActivity;

public class LoginActivity extends AppCompatActivity {

    public EditText et_id,et_pw;
    public Button btn_login, btn_register;
    public static String id,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("asdf","qwer");
        btn_login=(Button)findViewById(R.id.btn_login);
        et_id=(EditText)findViewById(R.id.et_id);
        et_pw=(EditText)findViewById(R.id.et_pw);
        btn_register=(Button)findViewById(R.id.btn_register);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("asdf","dddd");
                ContentValues values = new ContentValues();
                values.put("id", et_id.getText().toString());
                values.put("pw", et_pw.getText().toString());
                String url = "http://35.213.59.137/login";
                NetworkTask networkTask = new NetworkTask(url, values);
                networkTask.execute();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}