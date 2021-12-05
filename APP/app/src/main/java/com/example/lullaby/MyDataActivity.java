package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.lullaby.videos.SelectActivity;

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
        Button resetButton = findViewById(R.id.stamp_reset);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("asdf", "fwe");
                showData(yrSpinner, mSpinner);
            }
        });


    }
    public void showData(Spinner yrSpinner, Spinner mSpinner){
        String year = yrSpinner.getSelectedItem().toString();
        String month = mSpinner.getSelectedItem().toString();
        if(year.equals("2021") && month.equals("11월")){
            ImageView stamp = (ImageView) findViewById(R.id.stamp);
            stamp.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.stamp_11));
        }
    }
}