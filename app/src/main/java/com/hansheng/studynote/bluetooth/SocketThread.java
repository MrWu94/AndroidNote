package com.hansheng.studynote.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hansheng on 2016/7/21.
 * 蓝牙数据传输与Socket类是。蓝牙客户端运用BluetoothSocket，服务端运用BluetoothServerSocket。
 * 同样的，要实现蓝牙设备间的数据传输，需要得到设备的IP和端口号。BluetoothSocket和BluetoothServerSocket都需要一个UUID，
 * 这时，UUID将作为Socket的端口，蓝牙地址将作为IP，标准的UUID格式为：xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx (8-4-4-4-12)。
 */
public class SocketThread extends Thread {
    private BluetoothServerSocket serverSocket;
    public static UUID uuid = UUID.fromString("2562ca85-59cf-4fb4-b10c-262a52723a43");
    private static Map<BluetoothDevice, ChatClient> map = new HashMap<>();
    private static Handler handler;

    public static ChatClient getClient(BluetoothDevice device) {
        ChatClient client = map.get(device);
        if (client == null) {
            try {
                BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuid);
                socket.connect();
                client = new ChatClient(socket);
                map.put(device, client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public SocketThread(Handler handler) {
        this.handler = handler;
        try {
            serverSocket = BluetoothAdapter.getDefaultAdapter().listenUsingInsecureRfcommWithServiceRecord("", uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (serverSocket != null) {
            BluetoothSocket socket;
            try {
                while ((socket = serverSocket.accept()) != null) {
                    BluetoothDevice device = socket.getRemoteDevice();
                    ChatClient client = new ChatClient(socket);
                    client.register(handler);
                    map.put(device, client);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
