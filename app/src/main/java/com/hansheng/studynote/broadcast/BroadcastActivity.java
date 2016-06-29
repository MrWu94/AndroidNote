package com.hansheng.studynote.broadcast;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.hansheng.studynote.R;
import com.hansheng.studynote.service.*;
import com.hansheng.studynote.service.MsgService;

import java.util.List;

/**
 * Created by hansheng on 2016/6/29.
 */
public class BroadcastActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private MsgReceiver msgReceiver;
    private Intent mIntent;
    private Button mbutton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_layout);
        msgReceiver=new MsgReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.hansheng.studynote.RECEIVER");
        registerReceiver(msgReceiver,intentFilter);
        mIntent=new Intent(this, MsgService.class);

        mProgressBar= (ProgressBar) findViewById(R.id.progressBar1);
        mbutton= (Button) findViewById(R.id.button1);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(mIntent);
            }
        });


    }
    @Override
    protected void onDestroy() {
        //停止服务
        stopService(mIntent);
        //注销广播
        unregisterReceiver(msgReceiver);
        super.onDestroy();
    }



    public class MsgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            int progress=intent.getIntExtra("progress",0);
            mProgressBar.setProgress(progress);

        }
    }

    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
