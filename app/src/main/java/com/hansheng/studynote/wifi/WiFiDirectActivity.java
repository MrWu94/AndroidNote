package com.hansheng.studynote.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/7/26.
 * Wi-Fi 点对点（P2P）API 允许应用程序在无需连接到网络和热点的情况下连接到附近的设备。（Android Wi-Fi P2P 使用 Wi-Fi Direct™ 验证程序进行编译）
 * 。Wi-Fi P2P 技术使得应用程序可以快速发现附近的设备并与之交互。相比于蓝牙技术，Wi-Fi P2P 的优势是具有较大的连接范围。
 * 使用 Wi-Fi P2P 的时候，需要侦听当某个事件出现时发出的broadcast intent。在应用中，实例化一个 IntentFilter，并将其设置为侦听下列事件：
 * <p/>
 * WIFI_P2P_STATE_CHANGED_ACTION
 * <p/>
 * 　　指示　Wi-Fi P2P　是否开启
 * <p/>
 * WIFI_P2P_PEERS_CHANGED_ACTION
 * <p/>
 * 　　代表对等节点（peer）列表发生了变化
 * <p/>
 * WIFI_P2P_CONNECTION_CHANGED_ACTION
 * <p/>
 * 　　表明Wi-Fi P2P的连接状态发生了改变
 * <p/>
 * WIFI_P2P_THIS_DEVICE_CHANGED_ACTION
 * 初始化对等节点发现（Peer Discovery）
 * <p/>
 * 调用 discoverPeers() 开始搜寻附近带有 Wi-Fi P2P 的设备。该方法需要以下参数：
 * <p/>
 * 上节中调用 WifiP2pManager 的 initialize() 函数获得的 WifiP2pManager.Channel 对象
 * 一个对 WifiP2pManager.ActionListener 接口的实现，包括了当系统成功和失败发现所调用的方法。
 * WifiP2pManager.ActionListener 实现仅能通知我们初始化的成功或失败。想要监听连接状态的变化，
 * 需要实现 WifiP2pManager.ConnectionInfoListener 接口。接口中的 onConnectionInfoAvailable()
 * 回调函数会在连接状态发生改变时通知应用程序。当有多个设备同时试图连接到一台设备时（例如多人游戏或者聊天群），
 * 这一台设备将被指定为“群主”（group owner）。
 * <p/>
 * 2、P2P：对等计算（Peer to Peer，简称p2p）可以简单的定义成通过直接交换来共享计算机资源和服务，而对等计算模型应用层形成的网络通常称为对等网络。在P2P网络环境中，
 * 成千上万台彼此连接的计算机都处于对等的地位，整个网络一般来说不依赖专用的集中服务器。网络中的每一台计算机既能充当网络服务的请求者，
 * 又对其它计算机 的请求作出响应，提供资源和服务。通常这些资源和服务包括：信息的共享和交换、计算资源（如CPU的共享）、存储共享（如缓存和磁盘空间的使用）等。
 * 1、关键类名称及功能
 * 1)	WiFiDirectActivity类
 * 手机WiFi直连和传输文件最主要的activity，主要控制连接和传输界面的状态。
 * 2)	WiFiDirectBroadcastReceiver类
 * 收到WiFi直连状态改变的广播时，改变连接和传输界面界面和数据设置。
 * 3)	DeviceListFragment类
 * 主要显示和控制参与WiFi直连的手机的连接信息。
 * 4)	DeviceDetailFragment类
 * 主要显示和控制文件的传输，也可以控制WiFi直连的连接状态。
 * 5)	FileTransferService类
 * 主要在后台运行传送文件的服务，也可以传输信号
 * <p/>
 * 从欢迎界面进入WiFiDirectActivity的界面。该界面主要由DeviceListFragment和DeviceDetailFragment两个Fragment组成。
 * DeviceListFragment中主要是可参与WiFi直连设备的信息。DeviceDetailFragment中主要是部分连接细节的显示以及对进行连接、断开连接等内容的操控。
 */
public class WiFiDirectActivity extends AppCompatActivity implements WifiP2pManager.ChannelListener, DeviceListFragment.DeviceActionListener {
    public static final String TAG = "wifiDirectActivity";
    private WifiP2pManager manager;
    private boolean isWifiP2pEnabled = false;

    private boolean retryChannel = false;
    private WifiP2pManager.Channel channel;
    private BroadcastReceiver receiver = null;

    public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) {
        this.isWifiP2pEnabled = isWifiP2pEnabled;
    }


    @Override
    public void onChannelDisconnected() {
        // we will try once more
        if (manager != null && !retryChannel) {
            Toast.makeText(this, "Channel lost. Trying again", Toast.LENGTH_LONG).show();
            resetData();
            retryChannel = true;
            manager.initialize(this, getMainLooper(), this);
        } else {
            Toast.makeText(this,
                    "Severe! Channel is probably lost premanently. Try Disable/Re-Enable P2P.",
                    Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_main);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new WiFiDirectBroadcastReceiver(manager, channel, this);
        registerReceiver(receiver, getAction());
    }

    public static IntentFilter getAction() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        return intentFilter;
    }

    public void resetData() {
        DeviceListFragment fragmentList = (DeviceListFragment) getFragmentManager().findFragmentById(R.id.frag_list);
        DeviceDetailFragment fragmentDetails = (DeviceDetailFragment) getFragmentManager().findFragmentById(R.id.frag_detail);
        if (fragmentList != null) {
            fragmentList.clearPeers();

        }
        if (fragmentDetails != null) {
            fragmentDetails.resetViews();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_items, menu);
        return true;
    }

    /*
   * (non-Javadoc)
   * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
   */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.atn_direct_enable:
                if (manager != null && channel != null) {

                    // Since this is the system wireless settings activity, it's
                    // not going to send us a result. We will be notified by
                    // WiFiDeviceBroadcastReceiver instead.

                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                } else {
                    Log.e(TAG, "channel or manager is null");
                }
                return true;

            case R.id.atn_direct_discover:
                if (!isWifiP2pEnabled) {
                    Toast.makeText(WiFiDirectActivity.this, R.string.p2p_off_warning,
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
                final DeviceListFragment fragment = (DeviceListFragment) getFragmentManager()
                        .findFragmentById(R.id.frag_list);
                fragment.onInitiateDiscovery();
                manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {

                    @Override
                    public void onSuccess() {
                        Toast.makeText(WiFiDirectActivity.this, "Discovery Initiated",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int reasonCode) {
                        Toast.makeText(WiFiDirectActivity.this, "Discovery Failed : " + reasonCode,
                                Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showDetails(WifiP2pDevice device) {
        DeviceDetailFragment fragment = (DeviceDetailFragment) getFragmentManager().findFragmentById(R.id.frag_detail);
        fragment.showDetails(device);
    }

    @Override
    public void cancelDisconnect() {
         /*
         * A cancel abort request by user. Disconnect i.e. removeGroup if
         * already connected. Else, request WifiP2pManager to abort the ongoing
         * request
         */
        if (manager != null) {
            final DeviceListFragment fragment = (DeviceListFragment) getFragmentManager().findFragmentById(R.id.frag_list);
            if (fragment.getDevice() == null || fragment.getDevice().status == WifiP2pDevice.CONNECTED) {
                disconnect();
            } else if (fragment.getDevice().status == WifiP2pDevice.AVAILABLE
                    || fragment.getDevice().status == WifiP2pDevice.INVITED) {

                manager.cancelConnect(channel, new WifiP2pManager.ActionListener() {

                    @Override
                    public void onSuccess() {
                        Toast.makeText(WiFiDirectActivity.this, "Aborting connection",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int reasonCode) {
                        Toast.makeText(WiFiDirectActivity.this,
                                "Connect abort request failed. Reason Code: " + reasonCode,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    @Override
    public void connect(WifiP2pConfig config) {
        manager.connect(channel, config, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(WiFiDirectActivity.this, "Connect failed. Retry.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void disconnect() {
        final DeviceDetailFragment fragment = (DeviceDetailFragment) getFragmentManager().findFragmentById(R.id.frag_detail);
        fragment.resetViews();
        manager.removeGroup(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                fragment.getView().setVisibility(View.GONE);
            }

            @Override
            public void onFailure(int reason) {
                Log.d(TAG, "Disconnect failed. Reason :" + reason);
            }
        });

    }
}
