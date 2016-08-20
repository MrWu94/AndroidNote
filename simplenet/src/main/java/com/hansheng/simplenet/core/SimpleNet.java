package com.hansheng.simplenet.core;

import com.hansheng.simplenet.httpstacks.HttpStack;

/**
 * Created by hansheng on 2016/8/20.
 */
public final class SimpleNet {

    public static RequestQueue newRequestQueue(){
        return newRequestQueue(RequestQueue.DEFAULT_CORE_NUMS);
    }

    private static RequestQueue newRequestQueue(int defaultCoreNums) {
        return newRequestQueue(defaultCoreNums,null);
    }

    private static RequestQueue newRequestQueue(int defaultCoreNums, HttpStack httpStack) {
        RequestQueue queue=new RequestQueue(Math.max(0,defaultCoreNums),httpStack);
        queue.start();
        return queue;
    }
}
