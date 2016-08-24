package com.hanshenghanshengeventgbus.bus.demo;

import com.hanshenghanshengeventgbus.bus.Bus;
import com.hanshenghanshengeventgbus.bus.BusReceiver;

import java.util.Objects;
import java.util.concurrent.RunnableFuture;

/**
 * Created by hansheng on 16-8-24.
 */

public class BusDemo {
    public static void main(String... args){
        final BusDemo demo=new BusDemo();
        Bus bus=Bus.getDefault();
        bus.register(demo);
        bus.post(new Object());
        bus.post("SomeEvent");
        bus.post(12345);
        bus.post(new RuntimeException("Error"));
    }

    @BusReceiver
    public void onReceiveRunnableNotPost(Runnable event){
        System.out.println("onREceiveRunnableNotPost()event="+event);
    }
    @BusReceiver
    public void onObjectEvent(Object event){
        System.out.println("onObjectReceiver()ecent="+event);
    }
    @BusReceiver
    public void onExceptionEvent(Exception event){
        System.out.println("onexceptionEvent="+event);
    }
    @BusReceiver
    public void onStringReceive(String event) {
        System.out.println("onStringReceive() event=" + event);
    }

    @BusReceiver
    public void onInteger(Integer event) {
        System.out.println("onInteger() event=" + event);
    }
}
