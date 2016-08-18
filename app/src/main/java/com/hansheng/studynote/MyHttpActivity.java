package com.hansheng.studynote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.hanshenghttpclient.net.HanShengHttpClient;
import com.hansheng.hanshenghttpclient.net.RequestCallback;

/**
 * Created by hansheng on 2016/8/18.
 */
public class MyHttpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l);
        HanShengHttpClient.invokeRequest(this, "testGet", null, new RequestCallback() {
            @Override
            public void onSuccess(String content) {
                System.out.println("............"+content);
            }

            @Override
            public void onFail(String errorMessage) {

            }
        });
    }
}
