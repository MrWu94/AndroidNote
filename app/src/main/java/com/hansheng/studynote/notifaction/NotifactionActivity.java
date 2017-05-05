package com.hansheng.studynote.notifaction;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.hansheng.studynote.R;


/**
 * Created by hansheng on 16-9-27.
 */

public class NotifactionActivity extends AppCompatActivity {
    private Button button;
    private Button button1;
    private Button button2;
    //通知管理器
    private NotificationManager nm;

    //通知显示内容
    private PendingIntent pd;

    Notification baseNF;
    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifaction);
        button = (Button) findViewById(R.id.notifaction);
        button1 = (Button) findViewById(R.id.button_notifaction);
        button2 = (Button) findViewById(R.id.button_lock);
        id = 1;

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, NotifactionActivity.class);

        pd = PendingIntent.getActivity(this, 0, intent, 0);


        //通知时在状态栏显示的内容
//        baseNF.tickerText = "You clicked BaseNF!";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自定义下拉视图，比如下载软件时，显示的进度条。
                Notification notification = new Notification();

                notification.icon = R.mipmap.ic_launcher;
                notification.tickerText = "Custom!";

                RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notifaction_layout);
                contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
                contentView.setTextViewText(R.id.text, "Hello, this message is in a custom expanded view");
                notification.contentView = contentView;

                //使用自定义下拉视图时，不需要再调用setLatestEventInfo()方法
                //但是必须定义 contentIntent
                notification.contentIntent = pd;

                nm.notify(3, notification);

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());

                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("My notification");
                builder.setContentText("Hello World!");
// 设置通知的优先级
                builder.setPriority(NotificationCompat.PRIORITY_MAX);
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
// 设置通知的提示音
                builder.setSound(alarmSound);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(id, builder.build());

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplication());

                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("My notification");
                builder.setContentText("Hello World!");

                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                inboxStyle.setBigContentTitle("邮件标题：");
                for (int i = 0; i < 5; i++) {
                    inboxStyle.addLine("邮件内容" + i);
                }
                builder.setStyle(inboxStyle);

            }
        });
    }
}
