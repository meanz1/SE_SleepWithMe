package com.example.lullaby;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.lullaby.data.GlobalVariable;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    Handler delay_handler = new Handler();
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() == "keep"){
            Toast.makeText(context, "20분뒤 다시 알림이 뜹니다.", Toast.LENGTH_LONG).show();
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
            ((MainActivity)MainActivity.mContext).toggleButton.setChecked(false);
            ((MainActivity)MainActivity.mContext).toggleButton.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.off));
            GlobalVariable.getInstance().setYetSleep(true);
            GlobalVariable.getInstance().getMs().onDestroy();
        }
    }
}
