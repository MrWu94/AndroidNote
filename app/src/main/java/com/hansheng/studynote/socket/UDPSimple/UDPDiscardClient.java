package com.hansheng.studynote.socket.UDPSimple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by hansheng on 17-4-7.
 */

public class UDPDiscardClient {

    public static final int DEFAULT_PORT = 1301;

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = DEFAULT_PORT;
        try {
            InetAddress server = InetAddress.getByName(hostname);
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket client = new DatagramSocket();
            while (true) {
                String inline = userIn.readLine();
                if (inline.indexOf('.') != -1) break;
                byte[] data = inline.getBytes("UTF-8");
                DatagramPacket thePacket = new DatagramPacket(data, data.length, server, port);
                client.send(thePacket);
            }
            client.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }


    }
}
