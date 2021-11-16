package com.example.lullaby;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("수면 알리미");
        builder.setMessage("주무시나요?");
        builder.setNeutralButton("알림 끌래요", null);
        builder.setPositiveButton("안 자요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                againAlert(5000, builder.create());
            }
        });
        AlertDialog sleepAlert;
        sleepAlert = builder.create();
        sleepAlert.show();
    }
    //n시간 뒤 알림 다시띄워주는 함수
    public void againAlert(long time, AlertDialog d){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                d.show();
            }
        }, time);
    }
    //알림창 지속시간 설정 함수
    public void delayTime(long time, final AlertDialog d){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                d.dismiss();
            }
        }, time);
    }
}