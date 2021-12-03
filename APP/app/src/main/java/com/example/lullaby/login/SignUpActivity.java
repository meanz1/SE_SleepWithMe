package com.example.lullaby.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lullaby.R;
import com.example.lullaby.network.LoginNetworkTask;
import com.example.lullaby.network.SignUpNetworkTask;

public class SignUpActivity extends AppCompatActivity{

    public EditText sn_id, sn_pw, sn_pwcheck;
    public Button btn_signup, btn_back;
    public static String id, pw, pwcheck;
    private AlertDialog dialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sn_id=(EditText)findViewById(R.id.sn_id);
        sn_pw=(EditText)findViewById(R.id.sn_pw);
        sn_pwcheck=(EditText)findViewById(R.id.sn_pwcheck);
        btn_signup=(Button)findViewById(R.id.btn_signup);
        btn_back=(Button)findViewById(R.id.btn_back);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sn_id.getText().toString().equals("") || sn_pw.getText().toString().equals("") || sn_pwcheck.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
                if(!sn_pw.getText().toString().equals(sn_pwcheck.getText().toString())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    dialog = builder.setMessage("동일한 비밀번호를 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
                ContentValues values = new ContentValues();
                values.put("id", sn_id.getText().toString());
                values.put("pw", sn_pw.getText().toString());
                String url = "http://35.213.59.137/signup";
                SignUpNetworkTask SignUpNetworkTask = new SignUpNetworkTask(url, values);
                SignUpNetworkTask.execute();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() { if(SignUpNetworkTask.success) SignUpActivity.this.finish();
                    else {AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                        dialog = builder.setMessage("동일한 아이디가 존재합니다.").setNegativeButton("확인", null).create();
                        dialog.show();}}
                }, 200);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}