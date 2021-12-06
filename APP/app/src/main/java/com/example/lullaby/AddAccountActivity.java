package com.example.lullaby;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import com.example.lullaby.data.AccountData;
import com.example.lullaby.login.LoginActivity;
import com.example.lullaby.login.SignUpActivity;
import com.example.lullaby.network.ProfileNetworkTask;
import com.example.lullaby.network.SignUpNetworkTask;
import com.example.lullaby.videos.SelectActivity;

public class AddAccountActivity extends AppCompatActivity {

    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        EditText p_name=(EditText)findViewById(R.id.edittext);
        Spinner brSpinner = (Spinner)findViewById(R.id.birth);
        ArrayAdapter brAdapter = ArrayAdapter.createFromResource(this,
                R.array.birth, android.R.layout.simple_spinner_item);
        brAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brSpinner.setAdapter(brAdapter);

        Button saveButton = findViewById(R.id.add_save);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (p_name.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddAccountActivity.this);
                    dialog = builder.setMessage("이름을 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
                ContentValues values = new ContentValues();
                values.put("id", AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getUserId());
                values.put("name", p_name.getText().toString());
                //gender 하드코딩
                values.put("gender", "남");
                values.put("age", Integer.parseInt(brSpinner.getSelectedItem().toString()));
                //category 하드코딩
                values.put("category1", "동물");
                values.put("category2", "일상");
                String url = "http://35.213.59.137/profile/add";
                ProfileNetworkTask ProfileNetworkTask = new ProfileNetworkTask(url, values);
                ProfileNetworkTask.execute();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() { if(ProfileNetworkTask.success) {
                        finish();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddAccountActivity.this);
                        dialog = builder.setMessage("프로필 생성에 실패했습니다.").setNegativeButton("확인", null).create();
                        dialog.show();}}
                }, 300);
            }
        });
    }
}