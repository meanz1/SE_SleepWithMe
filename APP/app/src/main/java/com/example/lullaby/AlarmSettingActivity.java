package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AlarmSettingActivity extends AppCompatActivity {
    int sleep_time = 10000; // 기본 10초
    int sleep_check_term = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);
        //Spinner
        Spinner tstSpinner = (Spinner)findViewById(R.id.target_sleep_time);
        ArrayAdapter tstAdapter = ArrayAdapter.createFromResource(this,
                R.array.target_sleep_time, android.R.layout.simple_spinner_item);
        tstAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tstSpinner.setAdapter(tstAdapter);

        Spinner mwSpinner = (Spinner)findViewById(R.id.min_wakeup);
        ArrayAdapter mwAdapter = ArrayAdapter.createFromResource(this,
                R.array.min_wakeup, android.R.layout.simple_spinner_item);
        mwAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mwSpinner.setAdapter(mwAdapter);

        Spinner rpSpinner = (Spinner)findViewById(R.id.repeat_term);
        ArrayAdapter rpAdapter = ArrayAdapter.createFromResource(this,
                R.array.repeat_term, android.R.layout.simple_spinner_item);
        rpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rpSpinner.setAdapter(rpAdapter);

        Spinner scSpinner = (Spinner)findViewById(R.id.sleep_check_term);
        ArrayAdapter scAdapter = ArrayAdapter.createFromResource(this,
                R.array.sleep_check_term, android.R.layout.simple_spinner_item);
        scAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scSpinner.setAdapter(scAdapter);

        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // 완료 눌렀을때 정보 저장
                Log.d("asdf", "ㄹㅈㄷ알람");
                Toast.makeText(AlarmSettingActivity.this, "설정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        Button cancelButton = findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 취소 눌렀을때
                finish();
            }
        });

    }
    public int getSleepTime(Spinner tstSpinner){
        String sleepTime = tstSpinner.getSelectedItem().toString();
        switch (sleepTime){
            case "5시간":
                sleep_time = 5000;
                break;
            case "6시간":
                sleep_time = 6000;
                break;
            case "7시간":
                sleep_time = 7000;
                break;
            case "8시간":
                sleep_time = 8000;
                break;
        }
        return sleep_time;
    }
    public int getSleepCheckTerm(Spinner scSpinner){
        String sleepCheckTerm = scSpinner.getSelectedItem().toString();
        switch (sleepCheckTerm){
            case "20분":
                sleep_check_term = 10000;
                break;
            case "30분":
                sleep_check_term = 15000;
                break;
            case "40분":
                sleep_check_term = 20000;
                break;
            case "50분":
                sleep_check_term = 25000;
                break;
        }
        return sleep_check_term;
    }
}