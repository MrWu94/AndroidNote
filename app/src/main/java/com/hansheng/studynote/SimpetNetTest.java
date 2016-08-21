package com.hansheng.studynote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.simplenet.core.RequestQueue;
import com.hansheng.simplenet.core.SimpleNet;
import com.hansheng.simplenet.requests.StringRequest;

import static com.hansheng.simplenet.base.Request.HttpMethod;
import static com.hansheng.simplenet.base.Request.RequestListener;

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

            StringRequest request = new StringRequest(HttpMethod.GET, "http://nbaplus.sinaapp.com/api/v1.0/blogs/update", new RequestListener<String>() {
                @Override
                public void onComplete(int stCode, String response, String errMsg) {
                    System.out.println("........"+response);
                }
            });
            mQueue.addRequest(request);

    }

}
