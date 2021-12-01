package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.example.lullaby.MyService.MyBinder;
import com.example.lullaby.data.GlobalVariable;
import com.example.lullaby.login.LoginActivity;
import com.example.lullaby.videos.SelectActivity;
import com.example.lullaby.videos.VideoActivity;

public class MainActivity extends AppCompatActivity {
    boolean isService = false ; // 서비스중인지 확인하는 변수
    public final String DEFAULT = "DEFAULT";
    public static Context mContext;
    public ToggleButton toggleButton;
    AlarmManager alarmManager;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBinder mb = (MyBinder) service;
            GlobalVariable.getInstance().setMs(mb.getService());
            isService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {// 서비스와 연결 끊겼을때
            isService = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //create NotiChannel
        createNotificationChannel(DEFAULT, "default channel", NotificationManager.IMPORTANCE_HIGH);
        setContentView(R.layout.activity_main);
        mContext = this;
        toggleButton = (ToggleButton) this.findViewById(R.id.toggleButton);
        ImageButton accountButton = findViewById(R.id.account);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this,MyService.class);
                if(toggleButton.isChecked()){
                    showDialog();
                    //Intent intent = new Intent(MainActivity.this,MyService.class);
                    //bindService(intent, conn, Context.BIND_AUTO_CREATE); // 서비스 연결
                    Log.v("asdf","연결하는중");
                    toggleButton.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.on)
                    );
                }else{
                    Toast.makeText(getApplicationContext(),"수면모드 종료",Toast.LENGTH_SHORT).show();
                    GlobalVariable.getInstance().setYetSleep(true); // 화면꺼짐 실행안되게
                    unbindService(conn); // 서비스 종료
                    GlobalVariable.getInstance().getMs().onDestroy();
                    toggleButton.setBackgroundDrawable(
                            getResources().
                                    getDrawable(R.drawable.off)
                    );
                }
            }
        });
        accountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AccountActivity.class);
                startActivity(intent);
            }
        });
        ImageButton recommendButton = findViewById(R.id.recommend);
        recommendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SelectActivity.class);
                startActivity(intent);
            }
        });
        ImageButton AsmrButton = findViewById(R.id.alarm);
        AsmrButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AlarmSettingActivity.class);
                startActivity(intent);
            }
        });
        ImageButton myDataButton = findViewById(R.id.myData);
        myDataButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MyDataActivity.class);
                startActivity(intent);
            }
        });


        String name = "은서";
        TextView openingWord = findViewById(R.id.opening_word);
        openingWord.setText(name + "님\n오늘도 좋은 밤 되세요.");

        Log.d("asdf", "ㅈ1");
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        Log.d("asdf", "ㅈ2");
    }
    void createNotificationChannel(String channelId, String channelName,int importance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelName, importance));
        }
    }

    void createNotification(String channelId, int id, String title, String text) {
        //작업버튼 + 브로드캐스트
        Intent keepIntent = new Intent(this, MyBroadcastReceiver.class);
        keepIntent.setAction("keep"); // 브로드캐스트에 보낼 액션
        PendingIntent keepPendingIntent = PendingIntent.getBroadcast(this, 0 , keepIntent, 0);

        Intent stopIntent = new Intent(this, MyBroadcastReceiver.class);
        stopIntent.setAction("stop");
        PendingIntent stopPendingIntent = PendingIntent.getBroadcast(this, 0 , stopIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(text)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setTimeoutAfter(8000) // 몇초후에 꺼질건지?
                .addAction(android.R.drawable.ic_menu_close_clear_cancel, "안 자요", keepPendingIntent) // 알림창 버튼 등록
                .addAction(android.R.drawable.ic_menu_close_clear_cancel, "수면모드 끄기", stopPendingIntent)
                .setAutoCancel(true); // 알림 탭하면 알림 자동 삭제


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }

    void destroyNotification(int id){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
    }
    void sleepScreen(){ // 수면중 화면
        Intent intent = new Intent(getApplicationContext(), SleepActivity.class);
        startActivity(intent);
        showAlarm();
    }
    void showDialog() {
        Dialog sleepDialog = new Dialog(MainActivity.this);
        sleepDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sleepDialog.setContentView(R.layout.sleep_dialog);
        sleepDialog.show();
        Button yesButton = sleepDialog.findViewById(R.id.yesBtn);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "수면모드 시작", Toast.LENGTH_SHORT).show();
                serviceOn();
                sleepDialog.dismiss();
            }
        });
        Button noButton = sleepDialog.findViewById(R.id.noBtn);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "no", Toast.LENGTH_SHORT).show();
                toggleButton.setChecked(false);
                toggleButton.setBackgroundDrawable(
                        getResources().getDrawable(R.drawable.off));
                sleepDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
    void serviceOn(){
        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE); // 서비스 연결
    }
    void showAlarm(){
        int time = 5000;
        Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+time, pendingIntent);//10초후알람
        }else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+time,pendingIntent);
        }
    }
    
}