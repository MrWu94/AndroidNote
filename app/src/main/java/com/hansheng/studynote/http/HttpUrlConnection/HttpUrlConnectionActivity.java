package com.hansheng.studynote.http.HttpUrlConnection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hansheng on 16-11-26.
 */

public class HttpUrlConnectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 请求地址
                    String parm = "https://www.baidu.com/";
                    // 创建url
                    URL url = new URL(parm);
                    // 使用httpurlconnection 进行请求的发送
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    // 设置相关属性,如果是post请求 请求参数在正文中所以允许输出流，如果是get        请求不用输出了因为参数是在url之后
                    con.setDoOutput(false);
                    // 响应数据的接受，所以我们要允许输入流
                    con.setDoInput(true);
                    // 设置是否要缓存
                    con.setUseCaches(false);
                    //Java对象的序列化，进行流的传递
                    //con.setRequestProperty(URLEncoder.encode("Content-type","UTF-8"),URLEncoder.encode("application/x-java-serialized-object","UTF-8"));
                    // 设置请求方式
                    con.setRequestMethod("GET");
                    //上面这些都是请求的一些属性的配置，也就是规则的定制
                    //建立一个tcp连接
                    con.connect();
                    // 接收请求响应数据
                    InputStream inStrm = con.getInputStream();
                    byte[] buffer = new byte[1024];
                    inStrm.read(buffer);
                    // 编码设置
                    String body = new String(buffer, "UTF-8");
                    System.out.println("返回内容" + body.toString());
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }).start();


    }
}
