package com.hansheng.Thread.ThreadLocal;

/**
 * Created by hansheng on 2016/7/29.
 */
public class ClientThread extends Thread {
    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }

    private Sequence sequence;


    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.println(Thread.currentThread().getName()+"=>"+sequence.getNumber());
        }
    }
}
