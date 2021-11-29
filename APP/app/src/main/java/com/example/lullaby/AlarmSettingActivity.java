package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AlarmSettingActivity extends AppCompatActivity {

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
    }
}