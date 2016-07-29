package com.hansheng.thread.share;

/**
 * Created by hansheng on 2016/7/29.
 */
public class MutilShare {
    public static void main(String... args){
        MyData data=new MyData();
        Runnable add=new AddRunnable(data);
        Runnable dec=new DecRunnable(data);
        for(int i=0;i<2;i++){
            new Thread(add).start();
            new Thread(dec).start();
        }
    }
}
