package com.example.lullaby.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lullaby.R;
import com.example.lullaby.SignUpActivity;
import com.example.lullaby.network.NetworkTask;

public class LoginActivity extends AppCompatActivity {

    public EditText et_id,et_pw;
    public Button btn_login, btn_register;
    public static String id,pw;
    private AlertDialog dialog;

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
                if (et_id.getText().toString().equals("") || et_pw.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
                Log.d("asdf","dddd");
                ContentValues values = new ContentValues();
                values.put("id", et_id.getText().toString());
                values.put("pw", et_pw.getText().toString());
                String url = "http://35.213.59.137/login";
                NetworkTask networkTask = new NetworkTask(url, values);
                networkTask.execute();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() { if(networkTask.success) finish();
                    else {AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        dialog = builder.setMessage("로그인에 실패했습니다.").setNegativeButton("확인", null).create();
                        dialog.show();}}
                }, 200);
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