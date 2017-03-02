package com.hansheng.Tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hansheng on 2016/8/19.
 */
public class SimpleHttpServer extends Thread {

    public static void main(String[] args) {
        new SimpleHttpServer().start();
    }

    public static final int HTTP_PORT = 8000;
    ServerSocket mSocket = null;

    public SimpleHttpServer() {
        try {
            mSocket = new ServerSocket(HTTP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mSocket == null) {
            throw new RuntimeException("������Socket��ʼ��ʧ��");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("�ȴ�������");
                new DeliverThread(mSocket.accept()).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class DeliverThread extends Thread {
        Socket mClientSocket;
        BufferedReader mInputStream;
        PrintStream mOutputStream;
        /**
         * ���󷽷�,GET��POST��
         */
        String httpMethod;
        /**
         * ��·��
         */
        String subPath;
        /**
         * �ָ���
         */
        String boundary;

        /**
         * �������
         */
        Map<String, String> mParams = new HashMap<String, String>();
        // ����headers
        Map<String, String> mHeaders = new HashMap<String, String>();
        /**
         * �Ƿ��Ѿ�������Header
         */
        boolean isParseHeader = false;

        public DeliverThread(Socket socket) {
            mClientSocket = socket;
        }

        @Override
        public void run() {
            try {
                mInputStream = new BufferedReader(new InputStreamReader(
                        mClientSocket.getInputStream()));
                mOutputStream = new PrintStream(mClientSocket.getOutputStream());
                parseRequest();
                handleResponse();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IoUtils.closeQuitly(mInputStream);
                IoUtils.closeQuitly(mOutputStream);
                IoUtils.closeSocket(mClientSocket);
            }
        }

        private void parseRequest() {
            String line;
            try {
                int lineNum = 0;
                while ((line = mInputStream.readLine()) != null) {
                    // ���մӿͻ��˷��͹���������
                    if (lineNum == 0) {
                        parseRequestLine(line);
                    }
                    if (isEnd(line)) {
                        break;
                    }

                    if (lineNum != 0 && !isParseHeader) {
                        parseHeaders(line);
                    }
                    if (isParseHeader) {
                        parseRequestParams(line);
                    }

                    lineNum++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // �Ƿ��ǽ�����
        private boolean isEnd(String line) {
            return line.equals("--" + boundary + "--");
        }

        // ����������
        private void parseRequestLine(String lineOne) {
            String[] tempStrings = lineOne.split(" ");
            httpMethod = tempStrings[0];
            subPath = tempStrings[1];
            System.out.println("������,����ʽ : " + tempStrings[0] + ", ��·�� : " + tempStrings[1]
                    + ",HTTP�汾 : " + tempStrings[2]);
            System.out.println();
        }

        // ����header,����Ϊÿ��header���ַ���
        private void parseHeaders(String headerLine) {

            if (headerLine.equals("")) {
                isParseHeader = true;
                System.out.println("-----------> header�������\n");
                return;
            } else if (headerLine.contains("boundary")) {
                boundary = parseSecondField(headerLine);
                System.out.println("�ָ��� : " + boundary);
            } else {
                parseHeaderParam(headerLine);
            }
        }

        private void parseHeaderParam(String headerLine) {
            String[] keyvalue = headerLine.split(":");
            mHeaders.put(keyvalue[0].trim(), keyvalue[1].trim());
            System.out.println("header������ : " + keyvalue[0].trim() + ", ����ֵ : "
                    + keyvalue[1].trim());
        }

        // ����header�еĵڶ�������
        private String parseSecondField(String line) {
            String[] headerArray = line.split(";");
            parseHeaderParam(headerArray[0]);
            if (headerArray.length > 1) {
                return headerArray[1].split("=")[1];
            }
            return "";
        }

        // �����������
        private void parseRequestParams(String paramLine) throws IOException {
            if (paramLine.equals("--" + boundary)) {
                String ContentDisposition = mInputStream.readLine();
                String paramName = parseSecondField(ContentDisposition);
                mInputStream.readLine();
                String paramValue = mInputStream.readLine();
                mParams.put(paramName, paramValue);
                System.out.println("������ : " + paramName + ", ����ֵ : " + paramValue);
            }
        }

        // ���ؽ��
        private void handleResponse() {
            sleep();
            mOutputStream.println("HTTP/1.1 200 OK");
            mOutputStream.println("Content-Type: application/json");
            mOutputStream.println();
            mOutputStream.println("{\"stCode\":\"success\"}");
        }

        private void sleep() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

   
}
