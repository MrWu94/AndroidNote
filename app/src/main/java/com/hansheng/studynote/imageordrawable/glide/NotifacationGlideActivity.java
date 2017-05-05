package com.hansheng.studynote.imageordrawable.glide;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.NotificationTarget;
import com.hansheng.studynote.R;

import static com.hansheng.studynote.imageordrawable.glide.GlideListViewActivity.eatFoodyImages;

/**
 * Created by hansheng on 16-12-28.
 */

public class NotifacationGlideActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;
    private NotificationTarget notificationTarget;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        context = this;

        final RemoteViews rv = new RemoteViews(getPackageName(), R.layout.notification_layout);

        rv.setImageViewResource(R.id.remoteview_notification_icon, R.mipmap.ic_launcher);

        rv.setTextViewText(R.id.remoteview_notification_headline, "Headline");
        rv.setTextViewText(R.id.remoteview_notification_short_message, "Short Message");


        // build notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Content Title")
                        .setContentText("Content Text")
                        .setContent(rv)
                        .setPriority(NotificationCompat.PRIORITY_MAX);

        final Notification notification = mBuilder.build();
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

//// set big content view for newer androids
//        if (android.os.Build.VERSION.SDK_INT >= 16) {
//            notification.bigContentView = rv;
//        }

        notificationTarget = new NotificationTarget(
                context,
                rv,
                R.id.remoteview_notification_icon,
                notification,
                NOTIFICATION_ID);
        Glide.with(this) // safer!
                .load(eatFoodyImages[3])
                .asBitmap()
                .centerCrop()
                .into(notificationTarget);
    }
}
