package com.example.lullaby;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.example.lullaby.MyService.MyBinder;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    Handler delay_handler = new Handler();
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() == "keep"){
            Toast.makeText(context, "킵", Toast.LENGTH_LONG).show();
            GlobalVariable.getInstance().setYetSleep(true);
            Log.d("asdf", "MyBroadcastReceiver " + GlobalVariable.getInstance().getYetSleep());
            delay_handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    GlobalVariable.getInstance().getMs().sleepAlert();
                }
            }, 10000);
        }
        else if(intent.getAction() == "stop"){
            Toast.makeText(context, "수면모드 종료", Toast.LENGTH_LONG).show();
            GlobalVariable.getInstance().setYetSleep(true);
            GlobalVariable.getInstance().getMs().onDestroy();
        }
    }
}
