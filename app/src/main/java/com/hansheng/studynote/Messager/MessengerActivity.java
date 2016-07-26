package com.hansheng.studynote.Messager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/7/26.
 */
public class MessengerActivity extends AppCompatActivity {

    private static final String TAG="MessengerActivity";

    private Messenger mService;
    private Messenger mGetReplyMessenger=new Messenger(new MessengerHandler());
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent=new Intent(this,MessagerService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }

    private static  class MessengerHandler extends Handler  {
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case MyConstants.MSG_FROM_SERVICE:
                   Log.i(TAG, "receive msg from Service:" + msg.getData().getString("reply"));
                   break;
               default:
                   super.handleMessage(msg);

           }
        }
    }

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService=new Messenger(service);
            Log.d(TAG, "bind service");
//            Message msg=Message.obtain(null,MyConstants.MSG_FROM_CLIENT);
            Message msg=new Message();
            msg.what=MyConstants.MSG_FROM_CLIENT;
            Bundle data=new Bundle();
            data.putString("msg", "hello, this is client.");
            msg.setData(data);
            msg.replyTo=mGetReplyMessenger;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
