package com.hanshenghanshengeventgbus.bus.demo;

import com.hanshenghanshengeventgbus.bus.Bus;
import com.hanshenghanshengeventgbus.bus.BusReceiver;

/**
 * Created by hansheng on 16-8-24.
 */

public class BusEventDemo {

    public void start(Bus bus) {
        bus.register(this);
    }

    public void stop(Bus bus) {
        bus.unregister(this);
    }

    @BusReceiver
    public void onBaseReceive1(SomeEvent1 event) {
        System.out.println("onBaseReceive1() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }

    @BusReceiver
    public void onBaseReceive2(SomeEvent2 event) {
        System.out.println("onBaseReceive2() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }

    @BusReceiver
    public void onBaseReceive3(SomeEvent3 event) {
        System.out.println("onBaseReceive3() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }

    @BusReceiver
    public void onDemoReceive0(BaseDemoEvent event) {
        System.out.println("onDemoReceive0() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }

    @BusReceiver
    public void onDemoReceive1(BaseDemoEvent event) {
        System.out.println("onDemoReceive1() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }

    @BusReceiver
    public void onDemoReceive2(IDemoEvent event) {
        System.out.println("onDemoReceive2() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }

    @BusReceiver
    public void onDemoReceive3(IDemoEvent event) {
        System.out.println("onDemoReceive3() event=" + event
                + " class=" + this.getClass().getSimpleName());
    }
}
