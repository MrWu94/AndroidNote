package com.hansheng.mina;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by hansheng on 2016/8/17.
 */
public class SocketClient {
    public static void main(String... args) {
        SocketClient client = new SocketClient();
        client.start();
    }

    private void start() {
        BufferedReader inputReader = null;
        BufferedWriter writer = null;
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 9123);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            inputReader = new BufferedReader(new InputStreamReader(System.in));
            String inputContent;

            while (!(inputContent = inputReader.readLine()).equals("bye")) {
                System.out.println(inputContent);
                writer.write(inputContent+"\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                inputReader.close();
                socket.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
