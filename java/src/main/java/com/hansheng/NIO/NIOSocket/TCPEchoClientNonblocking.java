package com.hansheng.NIO.NIOSocket;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by hansheng on 2016/8/16.
 */
public class TCPEchoClientNonblocking {
    public static void main(String... args) throws Exception {
        if ((args.length < 2) || (args.length > 3))
            throw new IllegalArgumentException("参数不正确");
        //第一个参数作为要连接的服务端的主机名或IP
        String server=args[0];
       //第二个参数为要发送到服务端的字符串
        byte[] argument=args[1].getBytes();
        //如果有第三个参数，则作为端口号，如果没有，则端口号设为7
        int servPort=(args.length == 3) ? Integer.parseInt(args[2]) : 7;

        SocketChannel clntChan=SocketChannel.open();

        clntChan.configureBlocking(false);

        if(!clntChan.connect(new InetSocketAddress(server,servPort))){
            while (!clntChan.finishConnect()){
                System.out.println(".");
            }
        }
        //为了与后面打印的"."区别开来，这里输出换行符
        System.out.print("\n");
        //分别实例化用来读写的缓冲区
        ByteBuffer writeBuf = ByteBuffer.wrap(argument);
        ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
        //接收到的总的字节数
        int totalBytesRcvd = 0;
        //每一次调用read（）方法接收到的字节数
        int bytesRcvd;
        //循环执行，直到接收到的字节数与发送的字符串的字节数相等
        while (totalBytesRcvd < argument.length) {
            //如果用来向通道中写数据的缓冲区中还有剩余的字节，则继续将数据写入信道
            if (writeBuf.hasRemaining()) {
                clntChan.write(writeBuf);
            }
            //如果read（）接收到-1，表明服务端关闭，抛出异常
            if ((bytesRcvd = clntChan.read(readBuf)) == -1) {
                throw new SocketException("Connection closed prematurely");
            }
            //计算接收到的总字节数
            totalBytesRcvd += bytesRcvd;
            //在等待通信完成的过程中，程序可以执行其他任务，以体现非阻塞IO的异步特性
            //这里为了演示该方法的使用，同样只是一直打印"."
            System.out.print(".");
        }
        //打印出接收到的数据
        System.out.println("Received: " +  new String(readBuf.array(), 0, totalBytesRcvd));
        //关闭信道
        clntChan.close();

    }
}
