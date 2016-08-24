package com.hanshenghanshengeventgbus.bus.demo;

import com.hanshenghanshengeventgbus.bus.BusReceiver;

/**
 * Created by hansheng on 16-8-24.
 */

public class EventDemo3 extends BusEventDemo{
    @BusReceiver
    public void onReceive1(SomeEvent1 event) {
        System.out.println("EventDemo3.onReceive1() event=" + event);
    }

    @BusReceiver
    public void onReceive2(SomeEvent2 event) {
        System.out.println("EventDemo3.onReceive2() event=" + event);
    }



    @BusReceiver
    public void onDemo0(BaseDemoEvent event) {
        System.out.println("onDemo0() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }

    @BusReceiver
    public void onDemo1(BaseDemoEvent event) {
        System.out.println("onDemo1() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }

    @BusReceiver
    public void onDemo2(IDemoEvent event) {
        System.out.println("onDemo2() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }

    @BusReceiver
    public void onDemo3(IDemoEvent event) {
        System.out.println("onDemo3() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }
}
