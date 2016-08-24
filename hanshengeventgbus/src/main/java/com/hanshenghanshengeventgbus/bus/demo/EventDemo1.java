package com.hanshenghanshengeventgbus.bus.demo;

import com.hanshenghanshengeventgbus.bus.BusReceiver;

/**
 * Created by hansheng on 16-8-24.
 */

public class EventDemo1 {
    @BusReceiver
    public void onReceive(SomeEvent1 event) {
        System.out.println("EventDemo1.onReceive() event=" + event);
    }

    @BusReceiver
    public void onReceive2(SomeEvent2 event) {
        System.out.println("EventDemo1.onReceive2() event=" + event);
    }

    @BusReceiver
    public void onReceive3(SomeEvent3 event) {
        System.out.println("EventDemo1.onReceive3() event=" + event);
    }
}
