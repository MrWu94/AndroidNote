package com.hansheng.studynote.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/7/26.
 * A BroadcastReceiver that notifies of important wifi p2p events.
 * Wi-Fi Direct™的API允许应用程序不通过网络或热点，直接与周围的设备进行连接。应用程序可以迅速地查找附近的设备，交换信息。并且与蓝牙相比，Wi-Fi Direct的通讯范围更大。
 * 通过Wi-Fi Direct查找附近的设备，并与之连接一般包括如下几个步骤：
 * 设置应用程序权限
 * 创建一个广播接收器和对等网络管理器
 * 初始化对等点的搜索
 * 获取对等点列表
 * 连接一个对等点
 * <p/>
 * 创建一个新的 BroadcastReceiver 类，用来监听系统的Wi-Fi P2P状态的改变。在 onReceive() 方法中，添加一个条件来处理上面列出的各种P2P状态的变更
 */
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {
    private WifiP2pManager manager;
    private WifiP2pManager.Channel channel;
    private WiFiDirectActivity activity;

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel, WiFiDirectActivity activity) {
        this.manager = manager;
        this.channel = channel;
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // UI update to indicate wifi p2p status.
            //确定Wi-Fi Direct模式是否已经启用，并提醒Activity。
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                activity.setIsWifiP2pEnabled(true);
            } else {
                activity.setIsWifiP2pEnabled(false);
                activity.resetData();
            }
            Log.d(WiFiDirectActivity.TAG, "P2P state changed - " + state);
            //对等点列表已经改变！我们可能需要对此做出处理。
            //带有 WIFI_P2P_PEERS_CHANGED_ACTION 动作的意图(Intent)将会发起一个更新对等点列表的请求。

        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            // request available peers from the wifi p2p manager. This is an
            // asynchronous call and the calling activity is notified with a
            // callback on PeerListListener.onPeersAvailable()

            //从Wi-Fi P2P管理器中请求可用的对等点。
            //这是个异步的调用，
            //并且，调用行为是通过PeerListListener.onPeersAvailable()上的一个回调函数来通知的。
            if (manager != null) {
                manager.requestPeers(channel, (WifiP2pManager.PeerListListener)
                        activity.getFragmentManager().findFragmentById(R.id.frag_list));
            }
            Log.d(WiFiDirectActivity.TAG, "P2P peers changed");
            //连接状态已经改变！我们可能需要对此做出处理。
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            if (manager == null) {
                return;
            }
            NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
            if (networkInfo.isConnected()) {
                // we are connected with the other device, request connection
                // info to find group owner IP
                /**现在回到广播接收器的 onReceive() 方法中，修改监听 WIFI_P2P_CONNECTION_CHANGED_ACTION 意图(Intent)的部分。
                 收到该意图(Intent)时，调用 requestConnectionInfo()。这是一个异步的调用，所以结果会传给作为参数的连接信息监听器。*/
                DeviceDetailFragment fragment =
                        (DeviceDetailFragment) activity.getFragmentManager().findFragmentById(R.id.frag_detail);
                manager.requestConnectionInfo(channel, fragment);
            } else {
                activity.resetData();
            }
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            DeviceListFragment fragment = (DeviceListFragment) activity.getFragmentManager().findFragmentById(R.id.frag_list);
            fragment.updateThisDevice((WifiP2pDevice) intent.getParcelableExtra(
                    WifiP2pManager.EXTRA_WIFI_P2P_DEVICE));
        }


    }
}
