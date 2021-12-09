package com.example.lullaby;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.lullaby.data.AccountData;
import com.example.lullaby.data.GlobalVariable;

public class MyService extends Service {
    IBinder mBinder = new MyBinder();
    private int frequency = AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getFrequency() * 1000; // 시연시 10분 -> 10초로 나오게
    private int targetTime = AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getTargetSleep() * 1000; // 시연시 1시간 -> 1초로 나오게
    @Override
    public void onCreate(){
        super.onCreate();
        Handler delay_handler = new Handler();
        delay_handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!GlobalVariable.getInstance().getYetSleep()){
                    sleepAlert();
                    Log.d("몇초뒤에뜨" , Integer.toString(targetTime));
                }

            }
        }, frequency);
    }
    @Override
    public IBinder onBind(Intent intent) {
        //액티비티에서 bindService() 실행하면 호출됨
        // 리턴한 IBinder 은 서비스와 클라이언트 사이의 인터페이스 역할
        return  mBinder;
    }

    public class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }
    public void sleepAlert(){
        ((MainActivity)MainActivity.mContext).createNotification(((MainActivity)MainActivity.mContext).DEFAULT, 1, "sleep alert", "주무시나요?");
        Handler delay_handler = new Handler();
        Handler inner_delay_handler = new Handler();
        Log.d("asdf", "MyService in if " + GlobalVariable.getInstance().getYetSleep());
        delay_handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!GlobalVariable.getInstance().getYetSleep()){
                    Toast.makeText(getApplicationContext(),"화면이 곧 꺼집니다...",Toast.LENGTH_SHORT).show();
                    inner_delay_handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent sintent = new Intent(((MainActivity)MainActivity.mContext),MyService.class);
                            ((MainActivity)MainActivity.mContext).stopService(sintent);
                            ((MainActivity)MainActivity.mContext).sleepScreen(targetTime);// 수면중 화면으로 넘어감
                        }
                    },5000); // 화면이곧꺼집니다 알림후 5초뒤
                }
                GlobalVariable.getInstance().setYetSleep(false);
            }}, frequency); // n초 지연 후 알림 뜨게
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        return START_STICKY;
    }

}

