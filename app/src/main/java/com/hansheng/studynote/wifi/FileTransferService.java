package com.hansheng.studynote.wifi;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by hansheng on 2016/7/26.
 * 2P架构介绍
 * <p/>
 * P2P架构中定义了三个组件，一个设备，两种角色。这三个组件分别是：
 * <p/>
 * P2P Device：它是P2P架构中角色的实体，读者可把它当做一个Wi-Fi设备。
 * P2P Group Owner(GO)：P2P网络建立时会产生一个Group。
 * P2P Group Client(GC)：
 * 在组建P2P Group（即P2P Network）之前，智能终端都是一个一个的P2P Device。
 * 当这些P2P Device设备之间完成P2P协商后，那么其中将有一个并且只能有一个Device来扮演GO的角色，而其他Device来扮演GC的角色。
 * Wifi_Direct的大致配对流程如下：
 * <p/>
 * a. WifiP2pManager.discoverPeers()开始扫描设备
 * b. 获取扫描到的设备，选择其中一个设备进行连接配对WifiP2pManager.connect
 * c. 配对成功后，根据WifiP2pInfo.isGroupOwner和WifiP2pInfo.groupOwnerAddress进行连接。
 */
public class FileTransferService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    private static final int SOCKET_TIMEOUT = 5000;
    public static final String ACTION_SEND_FILE = "com.hansheng.android.wifidirect.SEND_FILE";
    public static final String EXTRAS_FILE_PATH = "file_url";
    public static final String EXTRAS_GROUP_OWNER_ADDRESS = "go_host";
    public static final String EXTRAS_GROUP_OWNER_PORT = "go_port";


    public FileTransferService(String name) {
        super(name);
    }

    public FileTransferService() {
        super("FileTransferService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Context context = getApplicationContext();
        if (intent.getAction().equals(ACTION_SEND_FILE)) {
            String fileUri = intent.getExtras().getString(EXTRAS_FILE_PATH);
            String host = intent.getExtras().getString(EXTRAS_GROUP_OWNER_ADDRESS);
            Socket socket = new Socket();
            int port = intent.getExtras().getInt(EXTRAS_GROUP_OWNER_PORT);


            try {
                Log.d(WiFiDirectActivity.TAG, "Opening client socket - ");
                socket.bind(null);
                socket.connect(new InetSocketAddress(host, port), SOCKET_TIMEOUT);
                Log.d(WiFiDirectActivity.TAG, "Client socket - " + socket.isConnected());
                OutputStream stream = socket.getOutputStream();
                ContentResolver cr = context.getContentResolver();
                InputStream is = null;
                try {
                    is = cr.openInputStream(Uri.parse(fileUri));
                } catch (FileNotFoundException e) {
                    Log.d(WiFiDirectActivity.TAG, e.toString());
                }
                DeviceDetailFragment.copyFile(is, stream);
                Log.d(WiFiDirectActivity.TAG, "Client: Data written");
            } catch (IOException e) {
                Log.e(WiFiDirectActivity.TAG, e.getMessage());
            } finally {
                if (socket != null) {
                    if (socket.isConnected()) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            // Give up
                            e.printStackTrace();
                        }
                    }
                }
            }


        }
    }
}
