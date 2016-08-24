package com.hanshenghanshengeventgbus.bus.demo;

import android.widget.BaseExpandableListAdapter;

import com.hanshenghanshengeventgbus.bus.Bus;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hansheng on 16-8-24.
 */

public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        new MainApp().demo();
    }

    private void demo() throws InterruptedException {

        final ExecutorService mExecutor= Executors.newCachedThreadPool();
        final Bus bus=Bus.getDefault();

        final EventDemo1 demo1=new EventDemo1();

        final BusEventDemo demo2=new EventDemo3();


        bus.register(demo1);
        demo2.start(bus);

        final CountDownLatch countDownLatch=new CountDownLatch(1);


        mExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    bus.post(new SomeEvent1());
                    Thread.sleep(500);
                    bus.post(new SomeEvent3());
                    Thread.sleep(500);
                    bus.post(new SomeEvent2());
                    Thread.sleep(200);
                    bus.post(new DemoEvent1());
                    demo2.stop(bus);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        countDownLatch.await();
        bus.unregister(demo1);
    }
}
