package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity{

    Button btn_back;
    protected void onCreate(Bundle savedInstanceState) {

        btn_back=(Button)findViewById(R.id.btn_back);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
