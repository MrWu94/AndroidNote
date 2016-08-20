package com.hansheng.simplenet.requests;

import com.hansheng.simplenet.base.Request;
import com.hansheng.simplenet.base.Response;

/**
 * Created by hansheng on 2016/8/20.
 */
public class StringRequest extends Request<String> {

    public StringRequest(HttpMethod method, String url, RequestListener<String> listener) {
        super(method, url, listener);
    }

    @Override
    public String parseResponse(Response response) {
        return new String(response.getRawData());
    }

}
