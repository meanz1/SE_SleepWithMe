package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lullaby.videos.AsmrPagerActivity;
import com.example.lullaby.videos.MeditPagerActivity;
import com.example.lullaby.videos.MusicPagerActivity;

public class SelectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        //ASMR 버튼 클릭시 액티비티 전환
        Button ASMR_btn = (Button) findViewById(R.id.btn1);
        ASMR_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AsmrPagerActivity.class);
                startActivity(intent);
            }
        });
        //Medit 버튼 클릭시 액티비티 전환
        Button Medit_btn = (Button) findViewById(R.id.btn2);
        Medit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MeditPagerActivity.class);
                startActivity(intent);
            }
        });
        //Music 버튼 클릭시 액티비티 전환
        Button Music_btn = (Button) findViewById(R.id.btn3);
        Music_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MusicPagerActivity.class);
                startActivity(intent);
            }
        });
    }
}