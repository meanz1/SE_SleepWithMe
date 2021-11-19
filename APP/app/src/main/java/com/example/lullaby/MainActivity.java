package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.lullaby.videos.VideoActivity;

public class MainActivity extends AppCompatActivity {
    public final String DEFAULT = "DEFAULT";
    public static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create NotiChannel
        createNotificationChannel(DEFAULT, "default channel", NotificationManager.IMPORTANCE_HIGH);
        //createNotification(DEFAULT, 1, "title", "text");
        setContentView(R.layout.activity_main);

        mContext = this;
        final ToggleButton toggleButton =
                (ToggleButton) this.findViewById(R.id.toggleButton);
        Button nextButton = findViewById(R.id.btn1);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked()){
                    toggleButton.setBackgroundDrawable(
                            getResources().
                                    getDrawable(R.drawable.on)
                    );
                }else{
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
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(text)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setTimeoutAfter(10000) // 몇초후에 꺼질건지?
                .addAction(makeButtonInNotification("Keep"))
                .addAction(makeButtonInNotification("Pause"));
        switch (getIntent().getAction()){
            case "Keep":
                Toast.makeText(this, "안 자요", Toast.LENGTH_SHORT).show();
                break;
            case "Pause":
                Toast.makeText(this, "알람 중지", Toast.LENGTH_SHORT).show();
                break;
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }
    void destroyNotification(int id){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
    }
    private NotificationCompat.Action makeButtonInNotification(String action){
        Intent intent = new Intent(getBaseContext(), MyService.class);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(getBaseContext(),1, intent, 0);
        int iconId = android.R.drawable.ic_media_pause;
        String btnTitle = action;

        NotificationCompat.Action notifAction = new NotificationCompat.Action.Builder(iconId, btnTitle, pendingIntent).build();
        return  notifAction;
    }

}