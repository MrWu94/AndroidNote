package com.hansheng.studynote.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by hansheng on 2016/7/21.
 */
public class BluetoothReceiver  extends BroadcastReceiver{

    public BluetoothReceiver(DeviceAdapter adapter) {
        this.adapter = adapter;
    }

    private DeviceAdapter adapter;

    @Override
    public void onReceive(Context context, Intent intent) {
        BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        adapter.add(device);
    }
}
