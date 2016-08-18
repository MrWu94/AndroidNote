package com.hansheng.hanshenghttpclient.net;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hansheng on 2016/8/18.
 */
public class RequestManager {

    ArrayList<HttpRequest> requests;

    public RequestManager() {
        requests = new ArrayList<>();
    }

    public HttpRequest createRequest(URLEntity url, RequestCallback callback) {
        return createRequest(url, null, callback);
    }

    /**
     * 有参数调用
     */
    public HttpRequest createRequest(URLEntity url, List<RequestParameter> params, RequestCallback requestCallback) {
        HttpRequest request = new HttpRequest(this, url, params, requestCallback);
        addRequest(request);
        return request;
    }

    /**
     * 添加Request到列表
     */
    public void addRequest(final HttpRequest request) {
        requests.add(request);
    }

    public void cancelAllRequest() {
        BlockingQueue queue = RequestThreadPool.getQueue();
        for (int i = requests.size() - 1; i >= 0; i--) {
            HttpRequest request = requests.get(i);
            if (queue.contains(request)) {
                queue.remove(request);
            } else {
                request.disconnect();
            }

        }
        requests.clear();
    }

    public void cancelBlockingRequest() {
        List<HttpRequest> intersection = (List<HttpRequest>) requests.clone();
        intersection.retainAll(RequestThreadPool.getQueue());

        RequestThreadPool.getQueue().removeAll(intersection);
        requests.removeAll(intersection);
    }

    public void cancelDesignateRequest(HttpRequest request) {
        if (!RequestThreadPool.removeTaskFromQueue(request)) {
            request.disconnect();
        }
    }
    /**
     * 取消指定的网络请求
     */
    public void cancelDesignatedRequest(HttpRequest request) {
        if (!RequestThreadPool.removeTaskFromQueue(request)) {
            request.disconnect();
        }
    }
}
