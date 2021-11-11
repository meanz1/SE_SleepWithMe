package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                Intent intent = new Intent(getApplicationContext(), ScreenSlidePagerActivity.class);
                startActivity(intent);
            }
        });
    }
}