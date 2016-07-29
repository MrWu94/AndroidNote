package com.hansheng.thread.share;

/**
 * Created by hansheng on 2016/7/29.
 */
public class MutilThread {
    /**
     * @param args
     */
    public static void main(String[] args) {
        final MyData data = new MyData();
        for(int i=0;i<2;i++){
            new Thread(new Runnable(){

                public void run() {
                    data.add();

                }

            }).start();
            new Thread(new Runnable(){

                public void run() {
                    data.dec();

                }

            }).start();
        }
    }
}
