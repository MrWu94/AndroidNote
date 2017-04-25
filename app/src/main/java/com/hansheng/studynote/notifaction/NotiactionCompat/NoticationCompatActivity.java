package com.hansheng.studynote.notifaction.NotiactionCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.widget.Button;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-4-25.
 */

public class NoticationCompatActivity extends AppCompatActivity {
    private Button button;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifaction);
        button = (Button) findViewById(R.id.notifaction);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        context = this;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder
                .setContentTitle("这是通知标题")
                .setContentText("这是通知内容")
                .setWhen(System.currentTimeMillis())
                .setColor(Color.parseColor("#EAA935"))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .build();
        manager.notify(1, notification);
    }
}
