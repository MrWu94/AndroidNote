package com.hansheng.studynote.Http.volleydemo;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by hansheng on 2016/6/28.
 */
public class GsonRequest<T> extends Request<T> {

    private final Listener<T> mListener;

    private Class<T> mClass;
    private Gson mGson;

    public GsonRequest(String url, Class<T> clazz, Listener<T> mListener, ErrorListener listener) {
        this(Method.GET,url,clazz,mListener,listener);
    }

    public GsonRequest(int method, String url,  Class<T> clazz ,Listener<T> mListener, ErrorListener listener) {
        super(method, url, listener);
        mGson=new Gson();
        this.mClass=clazz;
        this.mListener=mListener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {

        try {
            String jsonString=new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(mGson.fromJson(jsonString,mClass),HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T t) {
        mListener.onResponse(t);
    }

    /**
     * Created by wfq on 2016/11/27.
     */

    public static class VolleryHttpsActivity {
    }
}
