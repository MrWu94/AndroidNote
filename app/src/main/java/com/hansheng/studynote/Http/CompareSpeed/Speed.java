package com.hansheng.studynote.Http.CompareSpeed;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hansheng on 17-4-6.
 * HttpUrlConnection2.2以下版本较多bug,在2.3增加了https的支持,框架小,体积小,支持压缩,失败重连,缓存等有点
 * 压缩和缓存机制可以有效地减少网络访问的流量
 */


public class Speed {

    private static String link = "http://www.baidu.com";

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        useHttpURlConnection();
        long b = System.currentTimeMillis();
        System.out.println("use httpurlconnection: " + (b - a));
        long c = System.currentTimeMillis();
        useHttpClient();
        long d = System.currentTimeMillis();
        System.out.println("use httpclient: " + (d - c));
    }

    public static void useHttpURlConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                URL url = null;
                String result = "";
                try {
                    url = new java.net.URL(link);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(10000);
                    conn.connect();

                    InputStream urlStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream));
                    String s = "";
                    while ((s = reader.readLine()) != null) {
                        result += s;
                    }
                    System.out.println(result);
                    reader.close();
                    urlStream.close();
                    conn.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }

    public static void useHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    //得到HttpClient对象
                    HttpClient getClient = new DefaultHttpClient();
                    //得到HttpGet对象
                    HttpGet request = new HttpGet(link);
                    //客户端使用GET方式执行请教，获得服务器端的回应response
                    HttpResponse response = getClient.execute(request);
                    System.out.println("======");
                    //判断请求是否成功
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                        //获得输入流
                        InputStream inStrem = response.getEntity().getContent();
                        int result = inStrem.read();
                        while (result != -1) {
                            System.out.print((char) result);
                            result = inStrem.read();
                        }
                        //关闭输入流
                        inStrem.close();
                    } else {

                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();

    }
}