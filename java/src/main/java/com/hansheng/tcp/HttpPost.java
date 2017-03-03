package com.hansheng.Tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hansheng on 2016/8/19.
 */
public class HttpPost {

    public String url;
    private Map<String,String> mParamsMap=new HashMap<String,String>();

    Socket mSocket;

    public HttpPost(String url) {
        this.url=url;
    }

    public static void main(String... args){
        HttpPost httpPost=new HttpPost("127.0.0.1");
        httpPost.addParam("username","hansheng");
        httpPost.addParam("pwd","123456");
        httpPost.execute();
    }

    private void execute() {
        try {
            mSocket=new Socket(this.url,SimpleHttpServer.HTTP_PORT);
            PrintStream outputStream = new PrintStream(mSocket.getOutputStream());
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(
                    mSocket.getInputStream()));
            final String boundary = "my_boundary_123";
            // д��header
            writeHeader(boundary, outputStream);
            // д�����
            writeParams(boundary, outputStream);
            // �ȴ���������
            waitResponse(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHeader(String boundary, PrintStream outputStream) {
        outputStream.println("POST /api/login/ HTTP/1.1");
        outputStream.println("content-length:123");
        outputStream.println("Host:" + this.url + ":" + SimpleHttpServer.HTTP_PORT);
        outputStream.println("Content-Type: multipart/form-data; boundary=" + boundary);
        outputStream.println("User-Agent:android");
        outputStream.println();

    }
    private void writeParams(String boundary, PrintStream outputStream) {
        Iterator<String> paramsKeySet = mParamsMap.keySet().iterator();
        while (paramsKeySet.hasNext()) {
            String paramName = paramsKeySet.next();
            outputStream.println("--" + boundary);
            outputStream.println("Content-Disposition: form-data; name=" + paramName);
            outputStream.println();
            outputStream.println(mParamsMap.get(paramName));
        }
        // ������
        outputStream.println("--" + boundary + "--");
    }

    private void waitResponse(BufferedReader inputStream) throws IOException {
        System.out.println("������: ");
        String responseLine = inputStream.readLine();
        while (responseLine == null || !responseLine.contains("HTTP")) {
            responseLine = inputStream.readLine();
        }

        while ((responseLine = inputStream.readLine()) != null) {
            System.out.println(responseLine);
        }
    }

    public void addParam(String key,String value){
        mParamsMap.put(key,value);
    }
}
