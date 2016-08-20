package com.hansheng.simplenet.core;

import android.util.Log;

import com.hansheng.simplenet.base.Request;
import com.hansheng.simplenet.base.Response;
import com.hansheng.simplenet.cache.Cache;
import com.hansheng.simplenet.cache.LruMemCache;
import com.hansheng.simplenet.httpstacks.HttpStack;

import java.util.concurrent.BlockingQueue;

/**
 * Created by hansheng on 2016/8/20.
 */
public class NetworkExecutor extends Thread{


    private BlockingQueue<Request<?>> mRequestQueue;

    private HttpStack mHttpStack;

    private static ResponseDelivery mResponseDelivery=new ResponseDelivery();

    private static Cache<String,Response> mReqCache=new LruMemCache();

    private boolean isStop=false;

    public NetworkExecutor(BlockingQueue<Request<?>> mRequestQueue, HttpStack mHttpStack) {
        mRequestQueue=mRequestQueue;
        mHttpStack=mHttpStack;

    }

    @Override
    public void run() {
        while (!isStop){
            try {
                final Request<?> request=mRequestQueue.take();
                if(request.isCanceled()){
                    Log.d("### ", "### 取消执行了");
                    continue;
                }
                Response response=null;
                if(isUseCache(request)){
                    response=mReqCache.get(request.getUrl());
                }else{
                    response=mHttpStack.performRequest(request);

                    if(request.shouldCache()&&isSuccess(response)){
                        mReqCache.put(request.getUrl(),response);
                    }
                    mResponseDelivery.deliveryResponse(request,response);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isSuccess(Response response) {
        return response!=null&&response.getStatusCode()==200;
    }

    private boolean isUseCache(Request<?> request) {
        return request.shouldCache() && mReqCache.get(request.getUrl()) != null;
    }

    public void quit() {
        isStop=true;
        interrupt();
    }
}
