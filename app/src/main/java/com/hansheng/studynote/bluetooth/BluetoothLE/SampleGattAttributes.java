package com.hansheng.studynote.bluetooth.BluetoothLE;

import java.util.HashMap;

/**
 * Created by hansheng on 2016/7/24.
 * 从 UUID_HUMIDITY_DATA  特征中读取数据一件简单的事，只需调用gatt.readCharacteristic(humidityCharacteristic) 方法，
 * 并在BluetoothGattCallback  的实现类上复写onReadCharacteristic  方法(再次说明，这是一个异步执行的操作，所以可以在UI线程上调用
 * readCharacteristic() 方法）。这样一次性的操作是没什么问题的，但我们应用程序要不断监测温度和湿度值，并将其报告给用户。相比注册监
 * 听特征值的发生，通过调用readCharacteristic() 定期轮询会更有效
 */
public class SampleGattAttributes {

    private static HashMap<String, String> attributes = new HashMap();
    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";

    static {
        // Sample Services.
        attributes.put("0000180d-0000-1000-8000-00805f9b34fb", "Heart Rate Service");
        attributes.put("0000180a-0000-1000-8000-00805f9b34fb", "Device Information Service");
        // Sample Characteristics.
        attributes.put(HEART_RATE_MEASUREMENT, "Heart Rate Measurement");
        attributes.put("00002a29-0000-1000-8000-00805f9b34fb", "Manufacturer Name String");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}
