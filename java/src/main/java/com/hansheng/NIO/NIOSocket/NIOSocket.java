package com.hansheng.NIO.NIOSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by hansheng on 2016/8/16.NIO Socket编程中有一个主要的类Selector,这个类似一个观察者，
 * 只要我们把需要探知的套接字通道socketchannel注册到Selector,程序不用阻塞等待，可以并行做别的事情，当有事件发生时，
 * Selector会通知程序，传回一组SelectionKey,程序读取这些Key,就会获得注册过的socketchannel,然后，从这个Channel中读取和处理数据。
 Selector内部原理实际是在做一个对所注册的channel的轮询访问，不断的轮询(目前就这一个算法)，一旦轮询到一个channel有所注册的事情发生，
 比如数据来了，他就会站起来报告，交出一把钥匙，让我们通过这把钥匙来读取这个channel的内容
 *
 */
public class NIOSocket {
    private static final int CLINET_PORT = 10200;
    private static final int SEVER_PORT = 10201;

    //面向流的连接套接字的可选择通道
    private static SocketChannel ch;
    //通道选择器
    private static Selector sel;

    public static void main(String... args) {

        try {
            //打开套接字通道
            ch=SocketChannel.open();
            //打开一个选择器
            sel=Selector.open();
            //获取与套接字通道关联的套接字，并将该套接字绑定到本机指定端口
            ch.socket().bind(new InetSocketAddress(CLINET_PORT));

            ch.configureBlocking(false);

            ch.register(sel, SelectionKey.OP_READ|SelectionKey.OP_WRITE|SelectionKey.OP_CONNECT);
            sel.select();
            Iterator it=sel.selectedKeys().iterator();
            while (it.hasNext()){
                //获取通道的选择器的键
                SelectionKey key = (SelectionKey)it.next();
                it.remove();
                //如果该通道已经准备好套接字连接
                if(key.isConnectable()){
                    InetAddress addr = InetAddress.getLocalHost();
                    System.out.println("Connect will not block");
                  //调用此方法发起一个非阻塞的连接操作，如果立即建立连接，则此方法//返回true.否则返回false,且必须在以后使用finishConnect()完成连接操作
                  //此处建立和服务端的Socket连接
                    if(!ch.connect(new InetSocketAddress(addr, SEVER_PORT))){
                        //完成非立即连接操作
                        ch.finishConnect();
                    }


                }
                //此通道已准备好进行读取
                if(key.isReadable()){
                    System.out.println("Read will not block");
                }
//此通道已准备好进行写入
                if(key.isWritable()){
                    System.out.println("Write will not bloc");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                ch.close();
                sel.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
    }



