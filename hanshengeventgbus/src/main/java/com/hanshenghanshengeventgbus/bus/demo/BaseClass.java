package com.hanshenghanshengeventgbus.bus.demo;

import com.hanshenghanshengeventgbus.bus.Bus;

/**
 * Created by hansheng on 16-8-24.
 */

public class BaseClass {
    void onCreate(){
        Bus.getDefault().register(this);
    }
    void onDestrory(){
        Bus.getDefault().unregister(this);
    }
}
