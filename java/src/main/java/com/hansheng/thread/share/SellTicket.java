package com.hansheng.thread.share;

/**
 * Created by hansheng on 2016/7/29.
 */
public class SellTicket {
    public static void main(String[] args){
        Ticket t=new Ticket();
        new Thread(t).start();
        new Thread(t).start();
    }
}
