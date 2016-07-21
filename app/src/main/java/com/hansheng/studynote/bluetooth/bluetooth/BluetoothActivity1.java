package com.hansheng.studynote.bluetooth.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by hansheng on 2016/7/21.
 */
public class BluetoothActivity1 extends AppCompatActivity {

    public Button searchBtn;//搜索蓝牙设备
    public Button exitBtn;//退出应用
    public Button discoverBtn;//设置可被发现
    public ToggleButton openBtn;//开关蓝牙设备
    public Button serverbtn;
    public ListView listView;//蓝牙设备清单
    public ArrayAdapter<String> adapter;
    public ArrayList<String> list = new ArrayList<String>();
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> bondDevices;
    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blue_layoutmain);
        searchBtn = (Button) findViewById(R.id.btnSearch);
        exitBtn = (Button) findViewById(R.id.btnExit);
        discoverBtn = (Button) findViewById(R.id.btnDis);
        openBtn = (ToggleButton) findViewById(R.id.tbtnSwitch);
        serverbtn = (Button) findViewById(R.id.btnserver);
        listView = (ListView) findViewById(R.id.lvDevices);
        context = getApplicationContext();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        openBtn.setChecked(false);
        //注册广播接收信号
        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_FOUND);// 用BroadcastReceiver来取得搜索结果
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED); //每当扫描模式变化的时候，应用程序可以为通过ACTION_SCAN_MODE_CHANGED值来监听全局的消息通知。比如，当设备停止被搜寻以后，该消息可以被系统通知給应用程序。
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED); //每当蓝牙模块被打开或者关闭，应用程序可以为通过ACTION_STATE_CHANGED值来监听全局的消息通知。
        registerReceiver(searchReceiver, intent);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isDiscovering()) {
                    bluetoothAdapter.cancelDiscovery();
                }
                list.clear();
                bondDevices = bluetoothAdapter.getBondedDevices();

                for (BluetoothDevice device : bondDevices) {
                    String str = "	已配对完成	" + device.getName() + "	" + device.getAddress();
                    list.add(str);
                    adapter.notifyDataSetChanged();
                }
                bluetoothAdapter.startDiscovery();
            }
        });
        //退出应用
        exitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                BluetoothActivity1.this.finish();
            }
        });
        //设置蓝牙设备可发现
        discoverBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent discoverIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                discoverIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                startActivity(discoverIntent);
            }
        });
        //开关蓝牙设备
        openBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(openBtn.isChecked() == true){
                    bluetoothAdapter.disable();
                }
                else if(openBtn.isChecked() == false){
                    bluetoothAdapter.enable();
                }
            }
        });
        //作为服务端开启
        serverbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ServerThread serverThread = new ServerThread(bluetoothAdapter, context);
                Toast.makeText(context, "server 端启动", Toast.LENGTH_SHORT).show();
                serverThread.start();
            }
        });
        listView.setOnItemClickListener(new ItemClickListener());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (searchReceiver != null) {
            unregisterReceiver(searchReceiver);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // If BT is not on, request that it be enabled.
        if (bluetoothAdapter == null) {
            Toast.makeText(context, "蓝牙设备不可用", Toast.LENGTH_SHORT).show();
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, 3);
        }
    }

    private final BroadcastReceiver searchReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = null;
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                    Toast.makeText(context, device.getName() + "", Toast.LENGTH_SHORT).show();
                    String str = "	未配对完成	" + device.getName() + "	" + device.getAddress();
                    if (list.indexOf(str) == -1)// 防止重复添加
                        list.add(str);
                }
                adapter.notifyDataSetChanged();
            }
        }
    };

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(bluetoothAdapter.isDiscovering())
                bluetoothAdapter.cancelDiscovery();
            String str = list.get(position);
            String address = str.substring(str.length() - 17);
            BluetoothDevice btDev = bluetoothAdapter.getRemoteDevice(address);
            ClientThread clientThread = new ClientThread(btDev, context);
            clientThread.start();
        }
    }
}
