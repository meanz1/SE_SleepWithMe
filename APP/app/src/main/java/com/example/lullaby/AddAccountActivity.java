package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.lullaby.videos.SelectActivity;

public class AddAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        Spinner brSpinner = (Spinner)findViewById(R.id.birth);
        ArrayAdapter brAdapter = ArrayAdapter.createFromResource(this,
                R.array.birth, android.R.layout.simple_spinner_item);
        brAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brSpinner.setAdapter(brAdapter);

        Button saveButton = findViewById(R.id.add_save);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }
}