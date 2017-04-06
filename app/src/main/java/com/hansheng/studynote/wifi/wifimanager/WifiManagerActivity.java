package com.hansheng.studynote.wifi.wifimanager;


import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 17-4-5.
 */

public class WifiManagerActivity extends AppCompatActivity {
    public static final String TAG="WifiManagerActivity";
    public static final String NO_PASSWORD = "[ESS]";
    public static final String NO_PASSWORD_WPS = "[WPS][ESS]";
    public static final int WIFICIPHER_NOPASS = 1;
    public static final int WIFICIPHER_WEP = 2;
    public static final int WIFICIPHER_WPA = 3;
    public WifiInfo wifiInfo;
    public DhcpInfo dhcpInfo;
    public WifiManager wifiManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceler_layout);
         wifiManager= (WifiManager) getSystemService(WIFI_SERVICE);
//        wifiManager.setWifiEnabled(true);
        wifiInfo = wifiManager.getConnectionInfo();
        dhcpInfo = wifiManager.getDhcpInfo();
        String wifiProperty = "当前连接Wifi信息如下："+wifiInfo.getSSID()+'\n'+
                "ip:"     +     FormatString(dhcpInfo.ipAddress)   +'\n'+
                "mask:"   +     FormatString(dhcpInfo.netmask)     +'\n'+
                "netgate:"+     FormatString(dhcpInfo.gateway)     +'\n'+
                "dns:"    +     FormatString(dhcpInfo.dns1)  ;
        WifiInfo wifiInfo=wifiManager.getConnectionInfo();  //当前wifi连接信息

        Log.d(TAG, "onCreate: "+wifiProperty);
        List<ScanResult> scanResults=wifiManager.getScanResults();//搜索到的设备列表
        List<ScanResult> scanResult1=filterWithNoPassword(scanResults);
        for (ScanResult scanResult : scanResult1) {
            Log.d(TAG, "onCreate: "+"\n设备名："+scanResult.SSID
                    +" 信号强度："+scanResult.level+"/n :"+wifiManager.calculateSignalLevel(scanResult.level,4));
            addNetwork(createWifiCfg(scanResult.SSID, null,1));
        }


    }


    /**
     * 设备连接Wifi之后， 设备获取Wifi热点的IP地址
     * @return
     */
    public String getIpAddressFromHotspot(){
        // WifiAP ip address is hardcoded in Android.
        /* IP/netmask: 192.168.43.1/255.255.255.0 */
        String ipAddress = "192.168.43.1";
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        int address = dhcpInfo.gateway;
        ipAddress = ((address & 0xFF)
                + "." + ((address >> 8) & 0xFF)
                + "." + ((address >> 16) & 0xFF)
                + "." + ((address >> 24) & 0xFF));
        return ipAddress;
    }
    /**
     * 过滤有密码的Wifi扫描结果集合
     * @param scanResultList
     * @return
     */

    public static List<ScanResult> filterWithNoPassword(List<ScanResult> scanResultList){
        if(scanResultList == null || scanResultList.size() == 0){
            return scanResultList;
        }

        List<ScanResult> resultList = new ArrayList<>();
        for(ScanResult scanResult : scanResultList){
            if(scanResult.capabilities != null && scanResult.capabilities.equals(NO_PASSWORD) || scanResult.capabilities != null && scanResult.capabilities.equals(NO_PASSWORD_WPS)){
                resultList.add(scanResult);
            }
        }

        return resultList;
    }

    /**
     * 添加到指定Wifi网络 /切换到指定Wifi网络
     * @param wf
     * @return
     */
    public boolean addNetwork(WifiConfiguration wf){
        //断开当前的连接
        disconnectCurrentNetwork();

        //连接新的连接
        int netId = wifiManager.addNetwork(wf);
        boolean enable = wifiManager.enableNetwork(netId, true);
        return enable;
    }

    /**
     * 关闭当前的Wifi网络
     * @return
     */
    public boolean disconnectCurrentNetwork(){
        if(wifiManager != null && wifiManager.isWifiEnabled()){
            int netId = wifiManager.getConnectionInfo().getNetworkId();
            wifiManager.disableNetwork(netId);
            return wifiManager.disconnect();
        }
        return false;
    }



    /**
     * 创建WifiConfiguration
     *
     * @param ssid
     * @param password
     * @param type
     * @return
     */
    public static WifiConfiguration createWifiCfg(String ssid, String password, int type){
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();

        config.SSID = "\"" + ssid + "\"";

        if(type == WIFICIPHER_NOPASS){
//            config.wepKeys[0] = "";
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
//            config.wepTxKeyIndex = 0;

//            无密码连接WIFI时，连接不上wifi，需要注释两行代码
//            config.wepKeys[0] = "";
//            config.wepTxKeyIndex = 0;
        }else if(type == WIFICIPHER_WEP){
            config.hiddenSSID = true;
            config.wepKeys[0]= "\""+password+"\"";
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }else if(type == WIFICIPHER_WPA){
            config.preSharedKey = "\""+password+"\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.status = WifiConfiguration.Status.ENABLED;
        }

        return config;
    }


    /**
     *
     * @param value
     * @return
     */
    public String FormatString(int value){
        String strValue="";
        byte[] ary = intToByteArray(value);
        for(int i=ary.length-1;i>=0;i--){
            strValue += (ary[i] & 0xFF);
            if(i>0){
                strValue+=".";
            }
        }
        return strValue;
    }
    public  byte[] intToByteArray(int value){
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++){
            int offset = (b.length - 1 - i) * 8;
            b[i] = (byte) ((value >>> offset) & 0xFF);
        }
        return b;
    }
}
