package com.hansheng.studynote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.simplenet.base.Request;
import com.hansheng.simplenet.core.RequestQueue;
import com.hansheng.simplenet.core.SimpleNet;
import com.hansheng.simplenet.requests.StringRequest;

/**
 * Created by hansheng on 2016/8/20.
 */
public class SimpetNetTest extends AppCompatActivity {

    RequestQueue mQueue = SimpleNet.newRequestQueue();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l);
        testMultiRequests();
    }
    public void testMultiRequests() {

        for (int i = 0; i < 10; i++) {
            StringRequest request = new StringRequest(Request.HttpMethod.GET, "http://nbaplus.sinaapp.com/api/v1.0/blogs/update", new Request.RequestListener<String>() {
                @Override
                public void onComplete(int stCode, String response, String errMsg) {
                    System.out.println("........"+response);
                }
            });
            mQueue.addRequest(request);
        }


    }

}
