package com.hansheng.studynote.wifi.wifimanager;


import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
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
    public WifiInfo wifiInfo;
    public DhcpInfo dhcpInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceler_layout);
        WifiManager wifiManager= (WifiManager) getSystemService(WIFI_SERVICE);
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
        }
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
