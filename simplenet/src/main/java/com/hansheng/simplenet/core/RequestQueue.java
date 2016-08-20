package com.hansheng.simplenet.core;

import com.hansheng.simplenet.base.Request;
import com.hansheng.simplenet.httpstacks.HttpStack;
import com.hansheng.simplenet.httpstacks.HttpStackFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hansheng on 2016/8/20.
 */
public class RequestQueue {

    /**
     * 请求队列 [ Thread-safe ]
     */
    private BlockingQueue<Request<?>> mRequestQueue = new PriorityBlockingQueue<Request<?>>();
    /**
     * 请求的序列化生成器
     */
    private AtomicInteger mSerialNumGenerator = new AtomicInteger(0);

    /**
     * 默认的核心数
     */
    public static int DEFAULT_CORE_NUMS = Runtime.getRuntime().availableProcessors() + 1;
    /**
     * CPU核心数 + 1个分发线程数
     */
    private int mDispatcherNums = DEFAULT_CORE_NUMS;
    /**
     * NetworkExecutor,执行网络请求的线程
     */
    private NetworkExecutor[] mDispatchers = null;
    /**
     * Http请求的真正执行者
     */
    private HttpStack mHttpStack;

    protected RequestQueue(int coreNums,HttpStack httpStack){

        mDispatcherNums=coreNums;

        mHttpStack=httpStack!=null?httpStack: HttpStackFactory.createHttpStack();
    }



    /**
     * 启动NetworkExecutor
     */
    private final void startNetworkExecutors(){
        mDispatchers=new NetworkExecutor[mDispatcherNums];

        for(int i=0;i<mDispatcherNums;i++){
            mDispatchers[i]=new NetworkExecutor(mRequestQueue,mHttpStack);
            mDispatchers[i].start();
        }
    }

    public void start(){
        stop();
        startNetworkExecutors();
    }

    private void stop() {
        if(mDispatchers!=null&&mDispatchers.length>0){
            for(int i=0;i<mDispatchers.length;i++){
                mDispatchers[i].quit();
            }
        }
    }

    public void addRequest(Request request){
        if(!mRequestQueue.contains(request)){
            request.setSerialNumber(this.generateSerialNumber());
            mRequestQueue.add(request);
        }
    }

    public void clear(){
        mRequestQueue.clear();
    }

    public BlockingQueue<Request<?>> getAllRequests(){
        return mRequestQueue;
    }

    private int generateSerialNumber() {
        return mSerialNumGenerator.incrementAndGet();
    }


}
