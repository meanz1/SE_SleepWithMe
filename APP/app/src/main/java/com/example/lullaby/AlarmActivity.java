package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmActivity extends AppCompatActivity {
    Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    //SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    //SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
    boolean achieve = false; // 기본값 - fail

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarm);
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build();
        ringtone.setAudioAttributes(audioAttributes);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ringtone.play();
        Button wakeupButton = findViewById(R.id.btn_wakeup);
        wakeupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ringtone.stop();
                achieve = true; // 일어났어요 -> 목표달성
            }
        });
        Button keep_sleep_Button = findViewById(R.id.btn_keep_sleep);
        keep_sleep_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ringtone.stop();
                achieve = false;
            }
        });
        TextView nowTime = findViewById(R.id.now_time);
        nowTime.setText(getTime());
        TextView nowDate = findViewById(R.id.now_date);
        nowDate.setText(getDate());
    }
    public String getTime(){
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String nowTime = timeFormat.format(mDate);
        return(nowTime);
    }

    public String getDate(){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH)+1; // 0이 1월임
        int date = cal.get(Calendar.DATE);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        String korDayOfWeek="";
        switch (dayOfWeek){
            case 1:
                korDayOfWeek = "일";
                break;
            case 2:
                korDayOfWeek = "월";
                break;
            case 3:
                korDayOfWeek = "화";
                break;
            case 4:
                korDayOfWeek = "수";
                break;
            case 5:
                korDayOfWeek = "목";
                break;
            case 6:
                korDayOfWeek = "금";
                break;
            case 7:
                korDayOfWeek = "토";
                break;
        }
        return(month+"월 "+date+"일 "+korDayOfWeek+"요일");
    }
}