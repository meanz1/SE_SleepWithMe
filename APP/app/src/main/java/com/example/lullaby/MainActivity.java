package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lullaby.videos.Asmr1Activity;

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
                Intent intent = new Intent(v.getContext(), Asmr1Activity.class);
                startActivity(intent);
            }
        });
        String name = "은서";
        TextView openingWord = findViewById(R.id.opening_word);
        openingWord.setText(name + "님 안녕하세요");
    }
}