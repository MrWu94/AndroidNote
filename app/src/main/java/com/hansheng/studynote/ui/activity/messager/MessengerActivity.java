package com.hansheng.studynote.ui.activity.messager;

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
 * 大家看到Messenger可能会很轻易的联想到Message，然后很自然的进一步联想到Handler——没错，
 * Messenger的核心其实就是Message以及Handler来进行线程间的通信。下面讲一下通过这种方式实现IPC的步骤：
 * <p>
 * 服务端实现一个Handler，由其接受来自客户端的每个调用的回调
 * 使用实现的Handler创建Messenger对象
 * 通过Messenger得到一个IBinder对象，并将其通过onBind()返回给客户端
 * 客户端使用 IBinder 将 Messenger（引用服务的 Handler）实例化，然后使用后者将 Message 对象发送给服务
 * 服务端在其 Handler 中（具体地讲，是在 handleMessage() 方法中）接收每个 Message
 * <p>
 * 客户端就主要是发起与服务端的绑定，以及通过onServiceConnected()方法来过去服务端返回来的IBinder，
 * 借此构造Messenger，从而可以通过发送Message的方式与服务端进行交互。
 * <p>
 * 那么如何使用AIDL来通过bindService()进行线程间通信呢？基本上有下面这些步骤：
 * <p>
 * 服务端创建一个AIDL文件，将暴露给客户端的接口在里面声明
 * 在service中实现这些接口
 * 客户端绑定服务端，并将onServiceConnected()得到的IBinder转为AIDL生成的IInterface实例
 * 通过得到的实例调用其暴露的方法
 */
public class MessengerActivity extends AppCompatActivity {

    private static final String TAG = "MessengerActivity";

    private Messenger mService;
    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent = new Intent(this, MessagerService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstants.MSG_FROM_SERVICE:
                    Log.i(TAG, "receive msg from Service:" + msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);

            }
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            客户端就主要是发起与服务端的绑定，以及通过onServiceConnected()方法来过去服务端返回来的IBinder
//         ，借此构造Messenger，从而可以通过发送Message的方式与服务端进行交互。
            mService = new Messenger(service);
            Log.d(TAG, "bind service");
//            Message msg=Message.obtain(null,MyConstants.MSG_FROM_CLIENT);
            Message msg = new Message();
            msg.what = MyConstants.MSG_FROM_CLIENT;
            Bundle data = new Bundle();
            data.putString("msg", "hello, this is client.");
            msg.setData(data);
            msg.replyTo = mGetReplyMessenger;
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
