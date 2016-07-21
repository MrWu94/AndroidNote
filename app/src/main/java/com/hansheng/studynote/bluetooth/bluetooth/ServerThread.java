package com.hansheng.studynote.bluetooth.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by hansheng on 2016/7/21.
 */
public class ServerThread extends Thread {


    public BluetoothServerSocket serverSocket;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket socket;
    private Context context;
    public ServerThread(BluetoothAdapter bluetoothAdapter, Context context) {
        this.bluetoothAdapter = bluetoothAdapter;
        this.context = context;
    }

    @Override
    public void run() {
        try {
            serverSocket=bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(Bluetoothprotocol.PROTOCOL_SCHEME_RFCOMM,
                    UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            socket=serverSocket.accept();
            byte[] buffer=new byte[1024];
            int bytes=1;

            InputStream inputStream=null;
            inputStream=socket.getInputStream();

            while (true){
                if((bytes=inputStream.read(buffer))>0){
                    byte[] buf=new byte[bytes];
                    for(int i=0;i<bytes;i++){
                        buf[i]=buffer[i];
                    }
                    String s = new String(buf);
                    System.out.println(s+"hansheng server is in");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
