package com.hansheng.studynote.bluetooth.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by hansheng on 2016/7/21.
 * 通用唯一识别码（英语：Universally Unique Identifier，简称UUID）
 */
public class ClientThread extends Thread {

    private BluetoothSocket socket;
    private BluetoothDevice device;
    private Context context;
    public ClientThread(BluetoothDevice device, Context context) {
        this.socket = socket;
        this.context= context;
    }

    @Override
    public void run() {
        try {
            socket=device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            socket.connect();

            if(socket==null){
                Toast.makeText(context, "本设备没有蓝牙模块", Toast.LENGTH_SHORT).show();
                return;
            }
            System.out.println("hasnheng client");
            while (true){
                System.out.println("hansheng client is in");
                String msg = "hello everybody I am client";
                OutputStream os = socket.getOutputStream();
                os.write(msg.getBytes());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
