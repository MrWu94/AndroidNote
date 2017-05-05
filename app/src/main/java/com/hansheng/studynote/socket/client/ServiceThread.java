package com.hansheng.studynote.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by hansheng on 2016/7/25.
 */
public class ServiceThread implements Runnable {

    // 定义当前线程处理的Socket
    Socket s = null;
    // 该线程所处理的Socket所对应的输入流
    BufferedReader br = null;

    public ServiceThread(Socket s) {
        this.s = s;
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        String content = null;
        //采用循环不断的从Socket中读取客户端发送过来的数据
        while((content=readFromClient())!=null){
            //遍历socketList中的每个Socket
            //将读取到的内容每个向Socket发送一次
            for(Socket s:MyService.socketList){
                OutputStream os;
                try {
                    os = s.getOutputStream();
                    os.write((content+"\n").getBytes("gbk"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

    }

    // 定义读取客户端的信息
    public String readFromClient() {
        try {
            return br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
