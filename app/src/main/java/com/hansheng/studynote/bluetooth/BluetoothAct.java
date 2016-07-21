package com.hansheng.studynote.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hansheng.studynote.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by hansheng on 2016/7/21.
 */
public class BluetoothAct extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private BluetoothAdapter bluetoothAdapter;
    private List<String> bluetoothDevices = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;

    private final UUID MY_UUID = UUID.fromString("db764ac8-4b08-7f25-aafe-59d03c27bae3");

    private final String NAME = "Bluetooth_Socket";

    private BluetoothSocket clientSocket;
    private BluetoothDevice device;
    private AcceptThread acceptThread;

    private OutputStream os;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.blue_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listView = (ListView) findViewById(R.id.lvDevices);
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                bluetoothDevices.add(device.getName() + ":" + device.getAddress() + "\n");
            }
        }
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                bluetoothDevices);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(receiver, filter);

        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(receiver, filter);
        acceptThread = new AcceptThread();
        acceptThread.start();


    }

    public void onClick_Search(View view) {
        setTitle("正在扫描...");

        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }
        bluetoothAdapter.startDiscovery();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = arrayAdapter.getItem(position);
        String address = s.substring(s.indexOf(":") + 1).trim();

        try {
            if (bluetoothAdapter.isDiscovering()) {
                bluetoothAdapter.cancelDiscovery();
            }
            try {
                if (device == null) {
                    device = bluetoothAdapter.getRemoteDevice(address);

                }
                if (clientSocket == null) {
                    clientSocket = device
                            .createRfcommSocketToServiceRecord(MY_UUID);
                    clientSocket.connect();

                    os = clientSocket.getOutputStream();

                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (os != null) {
                os.write("发送信息到其他蓝牙设备".getBytes("utf-8"));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_BOND_STATE);
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                        bluetoothDevices.add(device.getName() + ":"
                                + device.getAddress() + "\n");
                        arrayAdapter.notifyDataSetChanged();
                    } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                            .equals(action)) {
                        setProgressBarIndeterminateVisibility(false);
                        setTitle("连接蓝牙设备");

                    }
                }
            }
        }
    };


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(BluetoothAct.this, String.valueOf(msg.obj), Toast.LENGTH_LONG).show();

        }
    };

    private class AcceptThread extends Thread {

        private BluetoothServerSocket serverSocket;
        private BluetoothSocket socket;
        private InputStream is;
        private OutputStream os;

        public AcceptThread() {
            try {
                serverSocket = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(NAME, MY_UUID);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void run() {
            try {
                socket = serverSocket.accept();
                is = socket.getInputStream();
                os = socket.getOutputStream();

                while (true) {
                    byte[] buffer = new byte[128];
                    int count = is.read(buffer);
                    Message msg = new Message();
                    msg.obj = new String(buffer, 0, count, "utf-8");
                    handler.sendMessage(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
