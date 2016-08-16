package com.hansheng.NIO.NIOSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * Created by hansheng on 2016/8/16.
 */
public class NIOSocketServer {
    public static final int PORT = 8080;
    public static void main(String[] args)throws IOException {
        //NIO的通道channel中内容读取到字节缓冲区ByteBuffer时是字节方式存储的，
        //对于以字符方式读取和处理的数据必须要进行字符集编码和解码
        String encoding = System.getProperty("file.encoding");
        //加载字节编码集
        Charset cs = Charset.forName(encoding);
        //分配两个字节大小的字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(16);
        SocketChannel ch = null;
        //打开服务端的套接字通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //打开通道选择器
        Selector sel = Selector.open();
        try{
            //将服务端套接字通道连接方式调整为非阻塞模式
            ssc.configureBlocking(false);
            //将服务端套接字通道绑定到本机服务端端口
            ssc.socket().bind(new InetSocketAddress(PORT));
            //将服务端套接字通道OP_ACCEP事件注册到通道选择器上
            SelectionKey key = ssc.register(sel, SelectionKey.OP_ACCEPT);
            System.out.println("Server on port:" + PORT);
            while(true){
                //通道选择器开始轮询通道事件
                sel.select();
                Iterator it = sel.selectedKeys().iterator();
                while(it.hasNext()){
                    //获取通道选择器事件键
                    SelectionKey skey = (SelectionKey)it.next();
                    it.remove();
//服务端套接字通道发送客户端连接事件，客户端套接字通道尚未连接
                    if(skey.isAcceptable()){
                        //获取服务端套接字通道上连接的客户端套接字通道
                        ch = ssc.accept();
                        System.out.println("Accepted connection from:" + ch.socket());
                        //将客户端套接字通过连接模式调整为非阻塞模式
                        ch.configureBlocking(false);
                        //将客户端套接字通道OP_READ事件注册到通道选择器上
                        ch.register(sel, SelectionKey.OP_READ);
                    }
//客户端套接字通道已经连接
                    else{
                        //获取创建此通道选择器事件键的套接字通道
                        ch = (SocketChannel)skey.channel();
                        //将客户端套接字通道数据读取到字节缓冲区中
                        ch.read(buffer);
                        //使用字符集解码字节缓冲区数据
                        CharBuffer cb = cs.decode((ByteBuffer)buffer.flip());
                        String response = cb.toString();
                        System.out.println("Echoing:" + response) ;
                        //重绕字节缓冲区，继续读取客户端套接字通道数据
                        ch.write((ByteBuffer)buffer.rewind());
                        if(response.indexOf("END") != -1) ch.close();
                        buffer.clear();
                    }
                }
            }
        }finally{
            if(ch != null) ch.close();
            ssc.close();
            sel.close();
        }
    }
}
