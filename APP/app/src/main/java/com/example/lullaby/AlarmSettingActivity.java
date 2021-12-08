package com.example.lullaby;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lullaby.data.AccountData;
import com.example.lullaby.network.ProfileNetworkTask;
import com.example.lullaby.network.SettingNetworkTask;

public class AlarmSettingActivity extends AppCompatActivity {

    private AlertDialog dialog;

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
                AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).setTargetSleep(Integer.parseInt(tstSpinner.getSelectedItem().toString()));
                AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).setIteration(Integer.parseInt(rpSpinner.getSelectedItem().toString()));
                AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).setFrequency(Integer.parseInt(scSpinner.getSelectedItem().toString()));
                AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).setMinWake(Integer.parseInt(mwSpinner.getSelectedItem().toString()));
                ContentValues values = new ContentValues();
                values.put("profile_idx", AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getIdx());
                values.put("target_sleep", Integer.parseInt(tstSpinner.getSelectedItem().toString()));
                values.put("iteration", Integer.parseInt(rpSpinner.getSelectedItem().toString()));
                values.put("frequency", Integer.parseInt(scSpinner.getSelectedItem().toString()));
                values.put("min_wake", Integer.parseInt(mwSpinner.getSelectedItem().toString()));
                String url = "http://35.213.59.137/profile/sleep/edit";
                SettingNetworkTask SettingNetworkTask = new SettingNetworkTask(url, values);
                SettingNetworkTask.execute();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() { if(SettingNetworkTask.success) {
                        finish();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AlarmSettingActivity.this);
                        dialog = builder.setMessage("설정에 실패했습니다.").setNegativeButton("확인", null).create();
                        dialog.show();}}
                }, 1000);
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
}