package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MyDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        Spinner yrSpinner = (Spinner)findViewById(R.id.data_year);
        ArrayAdapter yrAdapter = ArrayAdapter.createFromResource(this,
                R.array.data_year, android.R.layout.simple_spinner_item);
        yrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yrSpinner.setAdapter(yrAdapter);

        Spinner mSpinner = (Spinner)findViewById(R.id.data_month);
        ArrayAdapter mAdapter = ArrayAdapter.createFromResource(this,
                R.array.data_month, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
    }
}