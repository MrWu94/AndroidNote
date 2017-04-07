package com.hansheng.studynote.Http.HttpSocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by mrwu on 2017/4/6.
 */

public class HttpSocketActivity extends AppCompatActivity implements View.OnClickListener {

    private Button getbt;
    private TextView resulttv;
    private Lihttpclent mclient;
    private static final String TAG = "wenfeng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_socket);
        getbt = (Button) findViewById(R.id.Getbt);
        resulttv = (TextView) findViewById(R.id.Result);
        getbt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mclient = new Lihttpclent("www.youku.com");
        String response = mclient.excute();
        resulttv.setText(response);
    }


    class Lihttpclent {
        private String innersite;
        private StringBuilder mbuild = new StringBuilder("");

        public Lihttpclent(String site) {
            this.innersite = site;
        }


        public String excute() {

            Thread getthread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.i(TAG, "start run");
                        Socket mSocket = new Socket(
                                InetAddress.getByName(innersite),
                                80);
                        BufferedReader br = new BufferedReader(new
                                InputStreamReader(
                                mSocket.getInputStream(), "utf-8"));
                        BufferedWriter bf = new BufferedWriter((new OutputStreamWriter(
                                mSocket.getOutputStream())));
                        StringBuffer requestHeader = new StringBuffer();
                        requestHeader
                                .append("GET " + "/"
                                        + " HTTP/1.1\n")
                                .append("HOST:" + innersite + "\n")
                                //.append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n")
                                .append("Accept-Encoding:gzip, deflate, sdch\n")
                                .append("Accept-Language:zh-CN,zh;q=0.8\n")
                                .append("Cache-Control:no-cache\n")
                                .append("User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36\n")
                                .append("Encoding:UTF-8\n")
                                .append("Connection:keep-alive" + "\n")
                                .append("\n");
                        bf.write(requestHeader.toString());
                        bf.flush();
                        String line = "";
                        Log.i(TAG, "start read response");
                        while ((line = br.readLine()) != null) {
                            mbuild.append(line);
                            mbuild.append("\r\n");
                            // Log.i(TAG,line);

                        }
                        Log.i(TAG, mbuild.toString());
                    } catch (Exception e) {
                        Log.i(TAG, "excetion = " + e.toString());
                    }

                }
            });
            getthread.start();
            try {
                getthread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "outbuilder=" + mbuild.toString());
            return mbuild.toString();
            //return null;
        }
    }

}
