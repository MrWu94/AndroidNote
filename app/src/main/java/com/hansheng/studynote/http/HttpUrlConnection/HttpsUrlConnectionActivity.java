package com.hansheng.studynote.http.HttpUrlConnection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Created by wfq on 2016/11/27.
 */

public class HttpsUrlConnectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    SSLContext sc = SSLContext.getInstance("TLS");
                    sc.init(null, new TrustManager[]{new MyTrustManager()},
                            new SecureRandom());
                    HttpsURLConnection
                            .setDefaultSSLSocketFactory(sc.getSocketFactory());
                    HttpsURLConnection
                            .setDefaultHostnameVerifier(new MyHostnameVerifier());

                    URL url = new URL("https://kyfw.12306.cn/otn/");
                    HttpURLConnection urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();
                    // 取得输入流，并使用Reader读取
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in));
                    System.out.println("=============================");
                    System.out.println("Contents of get request");
                    System.out.println("=============================");
                    String lines;
                    while ((lines = reader.readLine()) != null) {
                        System.out.println(lines);
                    }
                    reader.close();
                    // 断开连接
                    urlConnection.disconnect();
                    System.out.println("=============================");
                    System.out.println("Contents of get request ends");
                    System.out.println("=============================");
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        }).start();

    }
}
