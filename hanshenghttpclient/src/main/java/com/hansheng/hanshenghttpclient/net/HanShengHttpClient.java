package com.hansheng.hanshenghttpclient.net;

import android.app.Activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hansheng on 2016/8/18.
 */
public class HanShengHttpClient {

    public static HanShengClientConfig config;

    public static Map<Activity,RequestManager> managerMap;


    public static void init(HanShengClientConfig config){
        HanShengHttpClient.config=config;
        managerMap=new HashMap<>();
        RequestThreadPool.init();
    }

    public static void invokeRequest(Activity activity,String apiKey,RequestCallback callback){
        invokeRequest(activity,apiKey,null,callback);
    }

     public static HttpRequest invokeRequest(Activity activity, String apiKey, List<RequestParameter> params, RequestCallback callback){
         URLEntity urlEntity=URLConfigManager.findURLByKey(apiKey);
         RequestManager manager=checkRequestManager(activity,true);
         HttpRequest request=manager.createRequest(urlEntity,params,callback);

         RequestThreadPool.execute(request);

         return request;

     }
    public static void cancelAllRequest(Activity activity){
        checkRequestManager(activity,false).cancelAllRequest();
    }

    public static void cancelAllRequest(){
        RequestThreadPool.removeAllTask();
    }

    public static void cancelBlockingRequest(Activity activity){
        checkRequestManager(activity,false).cancelBlockingRequest();
    }
    /**
     * 取消指定请求
     * @param activity
     * @param request
     */
    public static void cancelDesignatedRequest(Activity activity, HttpRequest request) {
        checkRequestManager(activity, false).cancelDesignatedRequest(request);
    }

    /**
     * 访问activity对应的RequestManager对象
     * @param activity
     * @param createNew 当RequestManager对象为null时是否创建新的RequestManager对象
     * @return
     */
    private static RequestManager checkRequestManager(Activity activity, boolean createNew) {
        RequestManager manager;
        if ((manager = managerMap.get(activity)) == null) {
            if (createNew) {
                manager = new RequestManager();
                managerMap.put(activity, manager);
            } else {
                throw new NullPointerException(activity.getClass().getSimpleName() + "'s RequestManager is null!");
            }
        }
        return manager;
    }
    /**
     * 关闭线程池，并等待任务执行完成，不接受新任务
     */
    public static void shutdown() {
        RequestThreadPool.shutdown();
    }

    /**
     * 关闭，立即关闭，并挂起所有正在执行的线程，不接受新任务
     */
    public static void shutdownRightnow() {
        RequestThreadPool.shutdownRightnow();
    }
}
