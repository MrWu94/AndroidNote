package com.hansheng.studynote.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hansheng on 2016/7/25.
 */
public class AndroidService {
    public static void main(String... args) throws IOException {
        ServerSocket service = new ServerSocket(30000);
        while (true) {
            Socket socket = service.accept();
            new Thread(new AndroidRunnable(socket)).start();

        }
    }
}
