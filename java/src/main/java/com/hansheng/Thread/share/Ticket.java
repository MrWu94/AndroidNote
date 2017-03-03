package com.hansheng.Thread.share;

/**
 * Created by hansheng on 2016/7/29.
 */
public class Ticket implements Runnable {

    private int ticket=10;

    @Override
    public void run() {
        while(ticket>0){
            ticket--;
            System.out.println("��ǰƱ��Ϊ��"+ticket);
        }
    }
}
