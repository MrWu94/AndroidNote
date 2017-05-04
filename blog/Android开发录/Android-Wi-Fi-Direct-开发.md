###Wi-Fi Direct 简介
  Wi-Fi Direct标准是指允许无线网络中的设备无需通过无线路由器即可相互连接。与蓝牙技术类似，这种标准允许无线设备以点对点形式互连，而且在传输速度与传输距离方面则比蓝牙有大幅提升。按照定义，**Wi-Fi Direct设备是支持对等连接的设备，这种设备既支持基础设施网络，也支持P2P连接。**Wi-Fi Direct设备能够作为典型的站点（STA）加入基础设施网络，而且必须支持Wi-Fi Protected Setup加入者功能。**Wi-Fi Direct设备通过组建小组（以一对一或一对多的拓扑形式）来建立连接，小组的工作形式与基础设施BSS类似。**由一部Wi-Fi Direct设备负责整个小组，包括控制哪部设备加入、小组何时启动和终止等。这种设备对于传统客户设备而言就是一部接入点，能够提供基础设施接入点所提供的部分服务。

关于Wi-Fi Direct的API函数的使用需要注意一下几个要点：
·用于探测（discover）对等设备（peers）、向对等设备发起请求（request）以及建立连接（connect）的方法定义在类[WifiP2pManager](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html)中。
·通过设置监听器（Listener）可以获知[WifiP2pManager](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html)中方法调用的成功与否。监听器以参数的形式传递给被调用的方法。
·当发现新对等设备或链接丢失的时候，Wi-Fi Direct系统（framework）以意向（Intent）的方式根据检测到的不同事件做出相应的通知。
开发中，以上三点的配合使用相当普遍。简单举个例子，定义一个监听器[WifiP2pManager.ActionListener](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.ActionListener.html)并调用函数[discoverPeers()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#discoverPeers(android.net.wifi.p2p.WifiP2pManager.Channel, android.net.wifi.p2p.WifiP2pManager.ActionListener))，当相应事件发生的时候就会在[ActionListener.onSuccess()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.ActionListener.html#onSuccess())和[ActionListener.onFailure()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.ActionListener.html#onFailure(int))两个方法中得到通知。当[discoverPeers()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#discoverPeers(android.net.wifi.p2p.WifiP2pManager.Channel, android.net.wifi.p2p.WifiP2pManager.ActionListener))方法检测到了对等设备列表变化的时候，可以收到由系统广播（broadcast）发出一个[WIFI_P2P_PEERS_CHANGED_ACTION](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#WIFI_P2P_PEERS_CHANGED_ACTION)意向。


每当有Wi-Fi Direct事件发生的时候（例如，发现新的对等设备、设备的Wi-Fi状态改变等），Wi-Fi Direct API会以广播的形式发出一个意向。而在应用程序中需要做的事情就是创建广播接收器（[creating a broadcast receiver](http://developer.android.com/guide/topics/connectivity/wifip2p.html#creating-br)）来处理这些意向：

有以下几种广播：
WIFI_P2P_STATE_CHANGED_ACTION 表明Wi-Fi对等网络（P2P）是否已经启用  
WIFI_P2P_PEERS_CHANGED_ACTION 表明可用的对等点的列表发生了改变 
WIFI_P2P_CONNECTION_CHANGED_ACTION 表示Wi-Fi对等网络的连接状态发生了改变 WIFI_P2P_THIS_DEVICE_CHANGED_ACTION 表示该设备的配置信息发生了改变


广播接收器可以让应用程序接收到Android系统所发出的广播意向。这样，应用程序就能对感兴趣的事件做出响应。创建广播接收器的基本步骤如下：
创建一个继承[BroadcastReceiver](http://developer.android.com/reference/android/content/BroadcastReceiver.html)类的新类。构造函数的参数分别传递[WifiP2pManager](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html),[WifiP2pManager.Channel](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.Channel.html)，以及在这个广播接收器中需要注册的活动（activity）。这是一种最常见的参数设置模式，它让广播接收器能够引起活动作出更新，同时又能在必要时使用Wi-Fi硬件和通信信道。
在广播接收器的[onReceive()](http://developer.android.com/reference/android/content/BroadcastReceiver.html#onReceive(android.content.Context, android.content.Intent))函数中，针对感兴趣的特定意向可以执行相应的动作（actions）。例如，当广播接收器收到了意向[WIFI_P2P_PEERS_CHANGED_ACTION](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#WIFI_P2P_PEERS_CHANGED_ACTION)，就可以调用[requestPeers()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#requestPeers(android.net.wifi.p2p.WifiP2pManager.Channel, android.net.wifi.p2p.WifiP2pManager.PeerListListener))方法来列举出当前探测到的对等设备。

下面的代码将展示了如何创建一个特定的广播接收器。例子中的广播接收器以[WifiP2pManager](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html)对象和一个活动（activity）作为参数，并使用它们针对收到的意向（intent）做出相应的动作（action）:
```
/** 
 * A BroadcastReceiver that notifies of important Wi-Fi p2p events. 
 */  
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {  
  
    private WifiP2pManager manager;  
    private Channel channel;  
    private MyWiFiActivity activity;  
  
    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, Channel channel,  
            MyWifiActivity activity) {  
        super();  
        this.manager = manager;  
        this.channel = channel;  
        this.activity = activity;  
    }  
  
    @Override  
    public void onReceive(Context context, Intent intent) {  
        String action = intent.getAction();  
  
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {  
            // Check to see if Wi-Fi is enabled and notify appropriate activity  
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {  
            // Call WifiP2pManager.requestPeers() to get a list of current peers  
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {  
            // Respond to new connection or disconnections  
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {  
            // Respond to this device's wifi state changing  
        }  
    }  
}  
```

**创建Wi-Fi Direct应用（Creating a Wi-Fi Direct Application）**

完整的Wi-Fi Direct应用包含创建并注册广播接收器、检测对等设备、连接对等设备以及在对等设备间传输数据几个方面的功能。下面将详细介绍如何实现。
**准备工作（Initial setup）**
在使用Wi-Fi Direct API之前，首先要确保应用程序能够访问硬件，并且设备支持Wi-Fi Direct协议。如果这些条件都满足，就可以获取一个[WifiP2pManager](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html)实例，创建并注册广播接收器，最后就是使用Wi-Fi Direct API了。
在Android manifest文件中加入以下内容，允许使用Wi-Fi设备上的硬件并声明应用程序正确支持了调用API所需的最低SDK版本
```
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />  
  
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />  
  
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />  
  
<uses-permission android:name="android.permission.INTERNET" />  
  
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />  
```
检查Wi-Fi Direct支持并已开启。推荐在广播接收器收到[WIFI_P2P_STATE_CHANGED_ACTION](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#WIFI_P2P_STATE_CHANGED_ACTION)意向的时候进行检测。检测结果需要通告相应的活动并做出处理：
```
public void onReceive(Context context, Intent intent) {  
  
    ...  
  
    String action = intent.getAction();  
  
    if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {  
  
        int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);  
  
        if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {  
  
            // Wifi Direct is enabled  
  
        } else {  
  
            // Wi-Fi Direct is not enabled  
  
        }  
  
    }  
  
    ...  
  
}
```
在活动的[onCreate()](http://developer.android.com/reference/android/app/Activity.html#onCreate(android.os.Bundle))方法中获取[WifiP2pManager](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html)对象的一个实例，通过该对象的[initialize()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#initialize(android.content.Context, android.os.Looper, android.net.wifi.p2p.WifiP2pManager.ChannelListener))方法向Wi-Fi Direct系统注册当前的应用程序。注册成功后，会返回一个[WifiP2pManager.Channel](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.Channel.html)，通过它，应用程序就能和Wi-Fi Direct系统交互。[WifiP2pManager](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html)和[WifiP2pManager.Channel](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.Channel.html)对象以及一个活动的引用最后都被作为参数传递给自定义的广播接收器。这样，该活动就能够响应广播接收器的通知并作出相应的更新。当然，这样做也使程序具备了操纵设备Wi-Fi状态的能力：
```
WifiP2pManager mManager;  
  
Channel mChannel;  
  
BroadcastReceiver mReceiver;  
  
...  
  
@Override  
  
protected void onCreate(Bundle savedInstanceState){  
  
    ...  
  
    mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);  
  
    mChannel = mManager.initialize(this, getMainLooper(), null);  
  
    mReceiver = new WiFiDirectBroadcastReceiver(manager, channel, this);  
  
    ...  
  
}  

```
创建一个意向过滤器（intent filter），其中添加的意向种类和广播接收器中的保持一致：
```
 public static IntentFilter getAction() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        return intentFilter;
    }
```
在活动的[onResume()](http://developer.android.com/reference/android/app/Activity.html#onResume())方法中注册广播接收器，并在活动的[onPause()](http://developer.android.com/reference/android/app/Activity.html#onPause())方法中注销它：
```
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
```
一旦成功获取[WifiP2pManager.Channel](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.Channel.html)并创建了广播接收器，应用程序就已经具备了使用Wi-Fi Direct相关函数和接收Wi-Fi Direct意向的能力。尽管放手使用[WifiP2pManager](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html)为你提供的方法，让程序也拥有Wi-Fi Direct的特殊能力吧！
下一节将讲述如何实现一些常见的动作，例如探测并连接到对等设备。

**探测对等设备（Discovering peers）**
调用[discoverPeers()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#discoverPeers(android.net.wifi.p2p.WifiP2pManager.Channel, android.net.wifi.p2p.WifiP2pManager.ActionListener))函数可以探测到有效距离内的对等设备。它是一个异步函数，调用成功与否会在程序所创建[WifiP2pManager.ActionListener](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.ActionListener.html)监听器的[onSuccess()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.ActionListener.html#onSuccess())和[onFailure()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.ActionListener.html#onFailure(int))中给出通知。值得注意的是，[onSuccess()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.ActionListener.html#onSuccess())方法只会对成功探测到对等设备这一事件做出通知，而并不会提供任何关于已发现的对等设备的具体信息：
```
manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {  
    @Override  
    public void onSuccess() {  
        ...  
    }  
  
    @Override  
    public void onFailure(int reasonCode) {  
        ...  
    }  
});  
```
当成功检测到对等设备存在的时候，系统会广播[WIFI_P2P_PEERS_CHANGED_ACTION](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#WIFI_P2P_PEERS_CHANGED_ACTION)意向。程序接收到该意向后，通过调用[requestPeers()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#requestPeers(android.net.wifi.p2p.WifiP2pManager.Channel, android.net.wifi.p2p.WifiP2pManager.PeerListListener))方法，就能获得已经探测到对等设备的清单。下面代码将展示如何实现这一过程：
```
PeerListListener myPeerListListener;  
...  
if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {  
  
    // request available peers from the wifi p2p manager. This is an  
    // asynchronous call and the calling activity is notified with a  
    // callback on PeerListListener.onPeersAvailable()  
    if (manager != null) {  
        manager.requestPeers(channel, myPeerListListener);  
    }  
}  
```
[requestPeers()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#requestPeers(android.net.wifi.p2p.WifiP2pManager.Channel, android.net.wifi.p2p.WifiP2pManager.PeerListListener))方法同样是一个异步函数，当它准备好一份对等设备列表的时候，就会通知监听器[WifiP2pManager.PeerListListener](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.PeerListListener.html)中定义的[onPeersAvailable()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.PeerListListener.html#onPeersAvailable(android.net.wifi.p2p.WifiP2pDeviceList))方法。而[onPeersAvailable()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.PeerListListener.html#onPeersAvailable(android.net.wifi.p2p.WifiP2pDeviceList))方法中所能获取到的对等设备列表以[WifiP2pDeviceList](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pDeviceList.html)形式存储，通过遍历这个列表可以选择出希望连接的设备。

**连接对等设备（Connecting to peers）**
确定了要连接的设备，还需调用[connect()](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.html#connect(android.net.wifi.p2p.WifiP2pManager.Channel, android.net.wifi.p2p.WifiP2pConfig, android.net.wifi.p2p.WifiP2pManager.ActionListener))方法建立连接。该方法的其中一个参数是[WifiP2pConfig](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pConfig.html)对象，它提供了要连接设备的相关信息。连接的成功与否需要通过监听器[WifiP2pManager.ActionListener](http://developer.android.com/reference/android/net/wifi/p2p/WifiP2pManager.ActionListener.html)获取通知。下面的代码将示范如何建立设备连接：
```
//obtain a peer from the WifiP2pDeviceList  
WifiP2pDevice device;  
WifiP2pConfig config = new WifiP2pConfig();  
config.deviceAddress = device.deviceAddress;  
manager.connect(channel, config, new ActionListener() {  
  
    @Override  
    public void onSuccess() {  
        //success logic  
    }  
  
    @Override  
    public void onFailure(int reason) {  
        //failure logic  
    }  
});  
```

**传输数据（Transferring data）**
连接一旦建立成功，数据传输也就是顺理成章的事情。以下是通过socket发送数据的基本步骤：
创建[ServerSocket](http://developer.android.com/reference/java/net/ServerSocket.html)。它将被用于监听特定端口，等待客户端发起的连接请求。该操作需要在后台线程中实现。
创建客户端[Socket](http://developer.android.com/reference/java/net/Socket.html)。客户端通过[ServerSocket](http://developer.android.com/reference/java/net/ServerSocket.html)对应的IP和端口连接到服务设备。
客户端向服务器发生数据。客户socket成功连接到服务socket后，就能以字节流的形式向服务器发生数据了。
服务器socket通过[accept()](http://developer.android.com/reference/java/net/ServerSocket.html#accept())方法等待客户端数据连接的到来。该方法在收到客户端数据之前一直处于阻塞状态。因此，需要在单独的线程中调用它。数据连接一旦建立，服务设备就能接收到客户端的数据。这时要做的就是施以相应的动作，例如将数据保存到文件，或者是直接显示到用户界面上，等等。

示例代码：
https://github.com/fuqinwu/StudyNote/tree/master/app/src/main/java/com/hansheng/studynote/wifi





