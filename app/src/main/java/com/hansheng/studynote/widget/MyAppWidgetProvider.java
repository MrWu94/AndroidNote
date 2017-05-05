package com.hansheng.studynote.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/7/20.
 */
public class MyAppWidgetProvider extends AppWidgetProvider {


    public static final String TAG = "MyAppWidgetProvider";
    public static final String CLICK_ACTION = "com.example.action.CLICK";
    private static RemoteViews mRemoteViews; /**
     * 接收窗口小部件点击时发送的广播
     */

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG, "onReceive : action = " + intent.getAction());

        if(intent.getAction().equals(CLICK_ACTION)){
            Toast.makeText(context,"clicked id",Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcBitmap= BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                    for(int i=0;i<20;i++){
                        float degree=(i*90)%360;
                        mRemoteViews.setImageViewBitmap(R.id.imageView1,rotateBitmap(context,srcBitmap,degree));
                        Intent intentClick=new Intent();
                        intentClick.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,intentClick,0);
                        mRemoteViews.setOnClickPendingIntent(R.id.imageView1,pendingIntent);
                        AppWidgetManager appWidgetProvider=AppWidgetManager.getInstance(context);
                        appWidgetProvider.updateAppWidget(new ComponentName(context,MyAppWidgetProvider.class),mRemoteViews);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();
        }


    }
    /**
     * 每次窗口小部件被点击更新都调用一次该方法
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "onUpdate");
        final int counter=appWidgetIds.length;
        Log.i(TAG, "counter = " + counter);
        for(int i=0;i<counter;i++){
            int appWidgetId=appWidgetIds[i];
            onWidgetUpdate(context,appWidgetManager,appWidgetId);
        }

    }
    /**
     * 窗口小部件更新
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetId
     */
    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Log.i(TAG, "appWidgetId = " + appWidgetId);
        mRemoteViews=new RemoteViews(context.getPackageName(),R.layout.activity_widget);

        Intent intentClick=new Intent();
        intentClick.setAction(CLICK_ACTION);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,intentClick,0);
        mRemoteViews.setOnClickPendingIntent(R.id.imageView1,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId,mRemoteViews);
    }

    private Bitmap rotateBitmap(Context context, Bitmap srcbBitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap tmpBitmap = Bitmap.createBitmap(srcbBitmap, 0, 0, srcbBitmap.getWidth(),
                srcbBitmap.getHeight(), matrix, true);
        return tmpBitmap;
    }

    /**
     * 每删除一次窗口小部件就调用一次
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.i(TAG, "onDeleted");

    }
    /**
     * 当该窗口小部件第一次添加到桌面时调用该方法，可添加多次但只第一次调用
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.i(TAG, "onEnabled");
    }
    /**
     * 当最后一个该窗口小部件删除时调用该方法，注意是最后一个
     */
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.i(TAG, "onDisabled");
    }
}
