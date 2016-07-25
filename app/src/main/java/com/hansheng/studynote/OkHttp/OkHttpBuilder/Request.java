package com.hansheng.studynote.OkHttp.OkHttpBuilder;


import retrofit2.http.Body;

/**
 * Created by hansheng on 2016/7/24.
 */
public class Request {
    String url;
    String method;
    String headers;
    Body requestBody;


    public Request(String url, String method, String headers, Body requestBody) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.requestBody = requestBody;
    }
}
