package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.lullaby.videos.AsmrActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button nextButton = findViewById(R.id.btn1);
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TestActivity.class);
                startActivity(intent);
            }
        });
        Button recommendButton = findViewById(R.id.recommend);
        recommendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SelectActivity.class);
                startActivity(intent);
            }
        });
        Button AsmrButton = findViewById(R.id.alarm);
        AsmrButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AsmrActivity.class);
                startActivity(intent);
            }
        });
    }
}