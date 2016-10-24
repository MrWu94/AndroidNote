package com.hansheng.studynote.AIDL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.hansheng.studynote.service.IMyInterface;

/**
 * Created by hansheng on 16-10-24.
 */

public class MyService extends Service {

    public final static String TAG = "MyService";

    private IBinder binder = new IMyInterface.Stub() {
        @Override
        public String getInfor(String s) throws RemoteException {
            Log.i(TAG, s);
            return "我是 Service 返回的字符串";
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreat");
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}