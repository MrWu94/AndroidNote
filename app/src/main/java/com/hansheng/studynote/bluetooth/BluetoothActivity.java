package com.hansheng.studynote.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 2016/7/21.
 */
public class BluetoothActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private DeviceAdapter adapter;
    private BluetoothReceiver receiver;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(BluetoothActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK:
                startBluetooth();
                Toast.makeText(this, "开启成功", Toast.LENGTH_SHORT).show();
                break;
            case RESULT_CANCELED:
                Toast.makeText(this, "开启失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_main);

        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            Toast.makeText(this, "本设备没有蓝牙模块", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            ListView listView = (ListView) findViewById(R.id.list);
            List<BluetoothDevice> list = new ArrayList<>(adapter.getBondedDevices());
            this.adapter = new DeviceAdapter(this, list);
            listView.setAdapter(this.adapter);
            if (!adapter.isEnabled()) {
                adapter.enable();
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, 0);
            } else {
                startBluetooth();
            }
            listView.setOnItemClickListener(this);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }

    }

    private void startBluetooth() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        adapter.startDiscovery();
        receiver = new BluetoothReceiver(this.adapter);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);
        new SocketThread(handler).start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BluetoothDevice device = (BluetoothDevice) parent.getAdapter().getItem(position);
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("device", device);
        startActivity(intent);
    }
}
