package com.example.lullaby;

import android.app.Notification;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

public class MyService extends Service {
    ServiceThread thread;
    /*public void ShowYesNoDialog() {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes Button Clicked
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Did the dialog display?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();
        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업

    public void onDestroy() {
        thread.stopForever();
        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    class myServiceHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {
            Intent intent = new Intent(getApplicationContext(), ServiceTestActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
            //ShowYesNoDialog();
            //토스트 띄우기
            Toast.makeText(MyService.this, "주무시나요?", Toast.LENGTH_LONG).show();

        }
    };
}
