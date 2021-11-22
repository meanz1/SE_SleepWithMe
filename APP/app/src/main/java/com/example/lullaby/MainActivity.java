package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.example.lullaby.MyService.MyBinder;
import com.example.lullaby.videos.VideoActivity;

public class MainActivity extends AppCompatActivity {
    boolean isService = false ; // 서비스중인지 확인하는 변수
    public final String DEFAULT = "DEFAULT";
    public static Context mContext;
    boolean yetSleep = false;

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
        //create NotiChannel
        createNotificationChannel(DEFAULT, "default channel", NotificationManager.IMPORTANCE_HIGH);
        setContentView(R.layout.activity_main);

        mContext = this;
        final ToggleButton toggleButton =
                (ToggleButton) this.findViewById(R.id.toggleButton);
        Button nextButton = findViewById(R.id.btn1);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                if(toggleButton.isChecked()){
                    Toast.makeText(getApplicationContext(),"수면모드 시작",Toast.LENGTH_SHORT).show();
                    bindService(intent, conn, Context.BIND_AUTO_CREATE); // 서비스 연결
                    Log.v("asdf","연결하는중");
                    // 서비스
                    if(isService){
                        GlobalVariable.getInstance().getMs().sleepAlert();
                        Log.d("asdf","잘됨");
                    }
                    toggleButton.setBackgroundDrawable(
                            getResources().
                                    getDrawable(R.drawable.on)
                    );
                }else{
                    Toast.makeText(getApplicationContext(),"수면모드 종료",Toast.LENGTH_SHORT).show();
                    unbindService(conn); // 서비스 종료
                    toggleButton.setBackgroundDrawable(
                            getResources().
                                    getDrawable(R.drawable.off)
                    );
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ServiceTestActivity.class);
                startActivity(intent);
            }
        });
        Button recommendButton = findViewById(R.id.recommend);
        recommendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SelectActivity.class);
                startActivity(intent);
            }
        });
        Button AsmrButton = findViewById(R.id.alarm);
        AsmrButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VideoActivity.class);
                startActivity(intent);
            }
        });
        String name = "은서";
        TextView openingWord = findViewById(R.id.opening_word);
        openingWord.setText(name + "님 안녕하세요");
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
                .setTimeoutAfter(10000) // 몇초후에 꺼질건지?
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

}