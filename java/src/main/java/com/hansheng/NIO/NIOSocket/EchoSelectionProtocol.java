package com.hansheng.NIO.NIOSocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by hansheng on 2016/8/16.
 * <p>
 * 基于NIO的TCP连接的建立步骤
 * <p>
 * 服务端
 * 1、传建一个Selector实例；
 * <p>
 * 2、将其注册到各种信道，并指定每个信道上感兴趣的I/O操作；
 * <p>
 * 3、重复执行：
 * <p>
 * 1）调用一种select（）方法；
 * <p>
 * 2）获取选取的键列表；
 * <p>
 * 3）对于已选键集中的每个键：
 * <p>
 * a、获取信道，并从键中获取附件（如果为信道及其相关的key添加了附件的话）；
 * <p>
 * b、确定准备就绪的操纵并执行，如果是accept操作，将接收的信道设置为非阻塞模式，并注册到选择器；
 * <p>
 * c、如果需要，修改键的兴趣操作集；
 * <p>
 * d、从已选键集中移除键
 * <p>
 * <p>
 * <p>
 * 客户端
 * <p>
 * 与基于多线程的TCP客户端大致相同，只是这里是通过信道建立的连接，但在等待连接建立及读写时，我们可以异步地执行其他任务。
 * 1、对于非阻塞SocketChannel来说，一旦已经调用connect（）方法发起连接，底层套接字可能既不是已经连接，
 * 也不是没有连接，而是正在连接。由于底层协议的工作机制，套接字可能会在这个状态一直保持下去，这时候就需要循环地调用finishConnect（）方法来检查是否完成连接
 * ，在等待连接的同时，线程也可以做其他事情，这便实现了线程的异步操作。
 * <p>
 * 2、write（）方法的非阻塞调用哦只会写出其能够发送的数据，而不会阻塞等待所有数据，而后一起发送，因此在调用write（）方法将数据写入信道时，一般要用到while循环,
 * <p>
 * 3、任何对key（信道）所关联的兴趣操作集的改变，都只在下次调用了select（）方法后才会生效。
 * 4、selectedKeys（）方法返回的键集是可修改的，实际上在两次调用select（）方法之间，都必须手动将其清空，
 * 否则，它就会在下次调用select（）方法时仍然保留在集合中，而且可能会有无用的操作来调用它，换句话说，select（）方法只会在已有的所选键集上添加键，它们不会创建新的建集。
 * *对于ServerSocketChannel来说，accept是唯一的有效操作，而对于SocketChannel来说，有效操作包括读、写和连接，另外，对于DatagramChannle，只有读写操作是有效的。
 */
public class EchoSelectionProtocol implements TCPProtocol {

    private int bufSize; // 缓冲区的长度

    public EchoSelectionProtocol(int bufSize) {
        this.bufSize = bufSize;
    }


    @Override
    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel clntChan = ((ServerSocketChannel) key.channel()).accept();
        clntChan.configureBlocking(false);
        //将选择器注册到连接到的客户端信道，并指定该信道key值的属性为OP_READ，同时为该信道指定关联的附件
        clntChan.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufSize));
    }

    @Override
    public void handleRead(SelectionKey key) throws IOException {
        SocketChannel clntChan = (SocketChannel) key.channel();
        //获取该信道所关联的附件，这里为缓冲区
        ByteBuffer buf = (ByteBuffer) key.attachment();
        long bytesRead = clntChan.read(buf);
        //如果read（）方法返回-1，说明客户端关闭了连接，那么客户端已经接收到了与自己发送字节数相等的数据，可以安全地关闭
        if (bytesRead == -1) {
            clntChan.close();
        } else if (bytesRead > 0) {
            //如果缓冲区总读入了数据，则将该信道感兴趣的操作设置为为可读可写
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }

    @Override
    public void handleWrite(SelectionKey key) throws IOException {
//获取与该信道关联的缓冲区，里面有之前读取到的数据
        ByteBuffer buf = (ByteBuffer) key.attachment();
        //重置缓冲区，准备将数据写入信道
        buf.flip();
        SocketChannel clntChan = (SocketChannel) key.channel();
        //将数据写入到信道中
        clntChan.write(buf);
        if (!buf.hasRemaining()) {
            //如果缓冲区中的数据已经全部写入了信道，则将该信道感兴趣的操作设置为可读
            key.interestOps(SelectionKey.OP_READ);
        }
        //为读入更多的数据腾出空间
        buf.compact();
    }
}
