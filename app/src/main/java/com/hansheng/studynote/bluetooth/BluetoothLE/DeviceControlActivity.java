package com.hansheng.studynote.bluetooth.BluetoothLE;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hansheng on 2016/7/24.
 * - 角色支持 : Android 手机只能作为 主设备 (central role), 开发者开发的 APP 可以使用其提供的 API 接口, 用于 发现设备, 遍历服务 (services),
 * 读写服务中的特性 (characteristics).
 * <p/>
 * -- 传统蓝牙对比 : 与传统的蓝牙对比, 蓝牙低功耗方案 (Bluetooth Low Energy) 是出于更低的电量消耗考虑而设计的. 这可以使 Android 应用可以与 BLE 设备进行交流,
 * 这些设备需要很低的电量,如 近距离传感器, 心率测量设备, 健康设备 等等.
 * Android 设备 与 BLE 设备互动时, 设备的角色 和 职责 :
 * <p/>
 * -- 中心设备 和 外围设备 : 这个角色体系适用于 BLE 连接. 中心设备角色 可以扫描, 查找广播. 外围设备角色 发送广播.
 * <p/>
 * -- GATT 服务器 和 GATT 客户端 : 这个决定了两个设备之间, 一旦建议连接后, 如何进行互相通信.
 * <p/>
 * (2) 中心设备 和 外围设备
 * BLE 连接需要两种设备都存在 : 为了理解其中的区别, 想象一下 你有一个 Android 设备 和 一个激活的 智能腕表蓝牙设备. 手机支持作为 中心设备 角色,
 * 智能腕表 蓝牙设备支持作为外围设备角色, 为了建立 BLE 连接, 只有外围设备 或者 只有 中心设备 都不能建立 BLE连接.
 * GATT 服务器 和 GATT 客户端 简介 :
 * <p/>
 * -- GATT 服务器 和 GATT 客户端 角色不是固定的 : 一旦手机 和 智能腕表 设备建立了 BLE 连接, 它们开始互相交换 GATT 元数据. 根据它们之间传输的数据类型,
 * 其中的一个会扮演 GATT 服务器的角色.
 * <p/>
 * -- 角色改变示例 : 如果 智能腕表 设备想要向手机报告传感器数据, 那么智能腕表必须当做 GATT 服务器. 如果智能腕表 想要从手机上接受更新数据,
 * 那么 Android 手机就是 GATT 服务器.
 * <p/>
 * -- 手机 和 设备 都可以作为 GATT 服务器 和 客户端 : 在本文档中使用的示例代码, 在 Android 设备上运行的 Android APP 就是 GATT 客户端,
 * BLE 外围设备就是 GATT 服务器. Android APP 从 GATT 服务器上获取数据,
 * 服务器的 BLE "heart rate monitor (心率监测)" 支持 "Heart Rate Profile (心率规范 - 一种 BLE 蓝牙标准规范)". Android APP 也可以作为 GATT 服务器;
 * <p/>
 * 查找 BLE 设备 :
 * <p/>
 * -- 查找方法参数 : 为了搜索到BLE 设备, 调用 BluetoothAdapter 的 startLeScan() 方法,
 * 该方法需要一个 BluetoothAdapter.LeScanCallback 类型的参数. 你必须实现这个 LeScanCallback 接口, 因为 BLE 蓝牙设备扫描结果在这个接口中返回.
 * <p/>
 * -- 查找策略 : 蓝牙搜索是非常耗电的, 你需要遵守以下的 中断策略 和 不循环策略.
 * <p/>
 * -- 中断策略 : 只要一发现蓝牙设备, 马上中断扫描.
 * <p/>
 * -- 不循环策略 : 不要循环扫描, 设置一个扫描的最大时间限制. 一个设备在之前可用, 继续扫描可能会使设备不可用, 此外继续扫描会持续浪费电池电量.
 * 查找特定 BLE 设备 :
 * <p/>
 * -- 方法调用 : 查找特定类型的外围设备, 可以调用下面的方法, 这个方法需要提供一个 UUID 对象数组, 这个 UUID 数组是 APP 支持的 GATT 服务的特殊标识.
 * 扫描回调接口 :
 * -- 接口作用 : BluetoothAdapter.LeScanCallback 实现类, 在这个实现类的接口中返回 BLE 设备扫描结果;
 * 设备扫描类型
 * 设备扫描类型 : 蓝牙设备扫描 在同一个时间扫描时, 只能扫描 BLE 设备 或者 SPP 设备中的一种, 不能同时扫描两种设备.
 * 连接指定 BluetoothDevice 蓝牙设备
 * <p/>
 * <p/>
 * 连接指定设备 :
 * <p/>
 * -- 连接到 GATT 服务 : 与 BLE 设备交互的第一步是 连接到 BLE 设备中的 GATT 服务.
 * <p/>
 * -- 实现方法 : 调用 BluetoothDevice 的 connectGatt() 方法可以连接到 BLE 设备的 GATT 服务.
 * <p/>
 * -- 参数解析 : connectGatt() 方法需要三个参数, 参数一 Context 上下文对象,
 * 参数二 boolean autoConnect 是否自动连接扫描到的蓝牙设备, 参数三 BluetoothGattCallback 接口实现类.
 * 获取 BluetoothGatt 对象 : 调用 connectGatt() 方法可以连接到 BLE 设备上的 GATT 服务, 返回一个 BluetoothGatt 实例对象,
 * 你可以使用这个对象去 管理 GATT 客户端操作.
 * <p/>
 * -- GATT 客户端操作 : Android APP 可以调用 GATT Client (客户端). BluetoothGattCallback 可以用于传递结果到 GATT 客户端,
 * 如 连接状态 和 更进一步的 GATT Client 操作.
 * BLE 蓝牙数据交互 :
 * <p/>
 * -- 界面 : 在下面的示例中, BLE 应用提供了一个 Activity 界面, 该 Activity 界面用于 连接, 展示数据, 展示 GATT 服务 和 设备支持的特性.
 * <p/>
 * -- BLE 蓝牙服务类 : 基于用户的输入, 这个 Activity 界面可以与一个 BluetoothLeService 的服务进行交流, 该交流的本质就是
 * BLE 设备的 GATT 服务 与 Android 的 BLE API 进行交流.
 * - 广播发送 : 当一个特定的回调被触发, 它调用适当的broadcastUpdate() 帮助方法, 将其当做一个 Action 操作传递出去.
 * <p/>
 * -- 注意蓝牙心率: 这部分的数据解析 与 蓝牙心率测量 是一起被执行的.
 * <p/>
 * 读取 BLE 属性
 * 读写属性简介 :
 * <p/>
 * -- 读写属性前提 : Android 应用连接到了 设备中的GATT 服务, 并且发现了 各种服务 (特性集合), 可以读写其中的属性.
 * <p/>
 * -- 读写属性代码示例: 遍历服务 (特性集合) 和 特性, 将其展示在 UI 界面中.
 */
public class DeviceControlActivity extends AppCompatActivity {


    private final static String TAG = DeviceControlActivity.class.getSimpleName();

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    private TextView mConnectionState;
    private TextView mDataField;
    private String mDeviceName;
    private String mDeviceAddress;
    private ExpandableListView mGattServicesList;
    private BluetoothLeService mBluetoothLeService;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics =
            new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
    private boolean mConnected = false;
    private BluetoothGattCharacteristic mNotifyCharacteristic;

    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";

    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    // Handles various events fired by the Service.
    // ACTION_GATT_CONNECTED: connected to a GATT server.
    // ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
    // ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
    // ACTION_DATA_AVAILABLE: received data from the device.  This can be a result of read
    //                        or notification operations.
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                updateConnectionState(R.string.connected);
                invalidateOptionsMenu();
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                updateConnectionState(R.string.disconnected);
                invalidateOptionsMenu();
                clearUI();
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the user interface.
                displayGattServices(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };

    // If a given GATT characteristic is selected, check for supported features.  This sample
    // demonstrates 'Read' and 'Notify' features.  See
    // http://d.android.com/reference/android/bluetooth/BluetoothGatt.html for the complete
    // list of supported characteristic features.
    private final ExpandableListView.OnChildClickListener servicesListClickListner =
            new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                            int childPosition, long id) {
                    if (mGattCharacteristics != null) {
                        final BluetoothGattCharacteristic characteristic =
                                mGattCharacteristics.get(groupPosition).get(childPosition);
                        final int charaProp = characteristic.getProperties();
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                            // If there is an active notification on a characteristic, clear
                            // it first so it doesn't update the data field on the user interface.
                            if (mNotifyCharacteristic != null) {
                                mBluetoothLeService.setCharacteristicNotification(
                                        mNotifyCharacteristic, false);
                                mNotifyCharacteristic = null;
                            }
                            mBluetoothLeService.readCharacteristic(characteristic);
                        }
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                            mNotifyCharacteristic = characteristic;
                            mBluetoothLeService.setCharacteristicNotification(
                                    characteristic, true);
                        }
                        return true;
                    }
                    return false;
                }
            };

    private void clearUI() {
        mGattServicesList.setAdapter((SimpleExpandableListAdapter) null);
        mDataField.setText(R.string.no_data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gatt_services_characteristics);

        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        // Sets up UI references.
        ((TextView) findViewById(R.id.device_address)).setText(mDeviceAddress);
        mGattServicesList = (ExpandableListView) findViewById(R.id.gatt_services_list);
        mGattServicesList.setOnChildClickListener(servicesListClickListner);
        mConnectionState = (TextView) findViewById(R.id.connection_state);
        mDataField = (TextView) findViewById(R.id.data_value);

//        getActionBar().setTitle(mDeviceName);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gatt_services, menu);
        if (mConnected) {
            menu.findItem(R.id.menu_connect).setVisible(false);
            menu.findItem(R.id.menu_disconnect).setVisible(true);
        } else {
            menu.findItem(R.id.menu_connect).setVisible(true);
            menu.findItem(R.id.menu_disconnect).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_connect:
                mBluetoothLeService.connect(mDeviceAddress);
                return true;
            case R.id.menu_disconnect:
                mBluetoothLeService.disconnect();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateConnectionState(final int resourceId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mConnectionState.setText(resourceId);
            }
        });
    }

    private void displayData(String data) {
        if (data != null) {
            mDataField.setText(data);
        }
    }

    // Demonstrates how to iterate through the supported GATT Services/Characteristics.
    // In this sample, we populate the data structure that is bound to the ExpandableListView
    // on the UI.
    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        String uuid = null;
        String unknownServiceString = getResources().getString(R.string.unknown_service);
        String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData
                = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(
                    LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData =
                    new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics =
                    gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas =
                    new ArrayList<BluetoothGattCharacteristic>();

            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(
                        LIST_NAME, SampleGattAttributes.lookup(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }

        SimpleExpandableListAdapter gattServiceAdapter = new SimpleExpandableListAdapter(
                this,
                gattServiceData,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{LIST_NAME, LIST_UUID},
                new int[]{android.R.id.text1, android.R.id.text2},
                gattCharacteristicData,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{LIST_NAME, LIST_UUID},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        mGattServicesList.setAdapter(gattServiceAdapter);
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }
}
