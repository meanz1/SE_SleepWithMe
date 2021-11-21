package com.example.lullaby;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() == "keep"){
            Toast.makeText(context, "킵", Toast.LENGTH_LONG).show();
        }
        else if(intent.getAction() == "stop"){
            Toast.makeText(context, "수면모드 종료", Toast.LENGTH_LONG).show();
            Intent sintent = new Intent(context,MyService.class);
            context.stopService(sintent);
        }
    }
}
