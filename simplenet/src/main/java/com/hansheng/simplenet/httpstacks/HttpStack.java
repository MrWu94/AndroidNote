package com.hansheng.simplenet.httpstacks;

import com.hansheng.simplenet.base.Request;
import com.hansheng.simplenet.base.Response;

/**
 * Created by hansheng on 2016/8/20.
 */
public interface HttpStack {
    public Response performRequest(Request<?> request) throws Exception;
}
