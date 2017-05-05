package com.hansheng.studynote.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hansheng.studynote.R;
import com.hansheng.studynote.service.IMyInterface;

/**
 * Created by hansheng on 16-10-24.
 */

public class AIDLActivity extends AppCompatActivity{

    public final static String TAG = "MainActivity";
    private IMyInterface myInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myInterface = IMyInterface.Stub.asInterface(service);
            Log.i(TAG, "连接Service 成功");
            try {
                String s = myInterface.getInfor("我是Activity传来的字符串");
                Log.i(TAG, "从Service得到的字符串：" + s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "连接Service失败");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item);
        startAndBindService();
    }
    private void startAndBindService() {
        Intent service = new Intent(AIDLActivity.this, MyService.class);
        //startService(service);
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
