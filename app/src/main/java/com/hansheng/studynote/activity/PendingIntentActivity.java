package com.hansheng.studynote.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by wfq on 2016/11/9.
 */

public class PendingIntentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }
    public void start(View view){
        Notification notification=new Notification();
        notification.icon=R.mipmap.ic_launcher;
        notification.tickerText="这是一条信息";
        notification.defaults= Notification.DEFAULT_SOUND;
        notification.when=10L;
        PendingIntent pendingIntent1=PendingIntent.getActivity(PendingIntentActivity.this,0,new Intent(this,ActivityOptionsActivity.class)
        ,0);
//        notification.setLatestEventInfo(this, "通知", "开会啦", PendingIntent.getActivity(this, 0, new Intent(this,ActivityOptionsActivity.class), 0));
        NotificationManager notificationManager= (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);

//        notificationManager.notify(0,);

        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setContentInfo("补充内容");
        builder.setContentText("主内容区");
        builder.setContentTitle("通知标题");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("新消息");
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        Intent intent = new Intent(getApplicationContext(), ActivityOptionsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification1= builder.build();
        notificationManager.notify(0,notification1);


    }

}
