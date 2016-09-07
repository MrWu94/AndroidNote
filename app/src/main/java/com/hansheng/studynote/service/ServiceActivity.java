package com.hansheng.studynote.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.hansheng.studynote.R;
import com.hansheng.studynote.service.MsgService.OnProgressListener;

import java.util.List;

/**
 * Created by hansheng on 2016/6/29.
 */
public class ServiceActivity extends AppCompatActivity {

    private MsgService msgService;
    private int progress = 0;
    private ProgressBar mProgressBar;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_layout);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        mButton = (Button) findViewById(R.id.button1);
        Intent intent = new Intent(this, MsgService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgService.startDownload();
//                listenProgress();
            }
        });


    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            /**
             *
             同过IBinder获取得到service中的MSGBinder class并可以通过这个类中返回得到此service的实例*/
            msgService = ((MsgService.MsgBinder) service).getService();
            //注册回调接口来接收下载进度的变化
            msgService.setOnProgressListener(new OnProgressListener() {

                @Override
                public void onProgress(int progress) {
                    mProgressBar.setProgress(progress);

                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {


        }
    };

    private void listenProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < MsgService.MAX_PROGRESS) {
                    progress = msgService.getProgress();
                    mProgressBar.setProgress(progress);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
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
