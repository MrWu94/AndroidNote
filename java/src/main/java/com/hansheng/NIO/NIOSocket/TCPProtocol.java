package com.hansheng.NIO.NIOSocket;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * Created by hansheng on 2016/8/16.
 */
public interface TCPProtocol {

    void handleAccept(SelectionKey key) throws IOException;

    void handleRead(SelectionKey key) throws IOException;

    void handleWrite(SelectionKey key) throws IOException;
}
