package com.hansheng.tcp;

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
            throw new RuntimeException("服务器Socket初始化失败");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("等待连接中");
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
         * 请求方法,GET、POST等
         */
        String httpMethod;
        /**
         * 子路径
         */
        String subPath;
        /**
         * 分隔符
         */
        String boundary;

        /**
         * 请求参数
         */
        Map<String, String> mParams = new HashMap<String, String>();
        // 请求headers
        Map<String, String> mHeaders = new HashMap<String, String>();
        /**
         * 是否已经解析完Header
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
                    // 接收从客户端发送过来的数据
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

        // 是否是结束行
        private boolean isEnd(String line) {
            return line.equals("--" + boundary + "--");
        }

        // 解析请求行
        private void parseRequestLine(String lineOne) {
            String[] tempStrings = lineOne.split(" ");
            httpMethod = tempStrings[0];
            subPath = tempStrings[1];
            System.out.println("请求行,请求方式 : " + tempStrings[0] + ", 子路径 : " + tempStrings[1]
                    + ",HTTP版本 : " + tempStrings[2]);
            System.out.println();
        }

        // 解析header,参数为每个header的字符串
        private void parseHeaders(String headerLine) {

            if (headerLine.equals("")) {
                isParseHeader = true;
                System.out.println("-----------> header解析完成\n");
                return;
            } else if (headerLine.contains("boundary")) {
                boundary = parseSecondField(headerLine);
                System.out.println("分隔符 : " + boundary);
            } else {
                parseHeaderParam(headerLine);
            }
        }

        private void parseHeaderParam(String headerLine) {
            String[] keyvalue = headerLine.split(":");
            mHeaders.put(keyvalue[0].trim(), keyvalue[1].trim());
            System.out.println("header参数名 : " + keyvalue[0].trim() + ", 参数值 : "
                    + keyvalue[1].trim());
        }

        // 解析header中的第二个参数
        private String parseSecondField(String line) {
            String[] headerArray = line.split(";");
            parseHeaderParam(headerArray[0]);
            if (headerArray.length > 1) {
                return headerArray[1].split("=")[1];
            }
            return "";
        }

        // 解析请求参数
        private void parseRequestParams(String paramLine) throws IOException {
            if (paramLine.equals("--" + boundary)) {
                String ContentDisposition = mInputStream.readLine();
                String paramName = parseSecondField(ContentDisposition);
                mInputStream.readLine();
                String paramValue = mInputStream.readLine();
                mParams.put(paramName, paramValue);
                System.out.println("参数名 : " + paramName + ", 参数值 : " + paramValue);
            }
        }

        // 返回结果
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
