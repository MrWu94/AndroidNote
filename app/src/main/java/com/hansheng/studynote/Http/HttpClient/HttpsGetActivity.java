package com.hansheng.studynote.Http.HttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.hansheng.studynote.R;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.InputStream;

/**
 * Created by hansheng on 16-11-26.
 */

public class HttpsGetActivity extends Activity {
    String uri = "https://kyfw.12306.cn/otn/";
    final String TAG_STRING = "TAG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //得到HttpClient对象
                    HttpClient getClient =HttpClientHelper.getHttpClient();
                    //得到HttpGet对象
                    HttpGet request = new HttpGet(uri);
                    //客户端使用GET方式执行请教，获得服务器端的回应response
                    HttpResponse response = getClient.execute(request);
                    //判断请求是否成功
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        Log.i(TAG_STRING, "请求服务器端成功");
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
                        Log.i(TAG_STRING, "请求服务器端失败");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
