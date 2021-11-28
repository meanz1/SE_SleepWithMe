package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AlarmActivity extends AppCompatActivity {
    Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarm);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ringtone.play();
        Button wakeupButton = findViewById(R.id.btn_wakeup);
        wakeupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });
        Button keep_sleep_Button = findViewById(R.id.btn_keep_sleep);
        keep_sleep_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });
    }
}