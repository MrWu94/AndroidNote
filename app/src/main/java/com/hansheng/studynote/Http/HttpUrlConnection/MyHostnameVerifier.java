package com.hansheng.studynote.Http.HttpUrlConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by wfq on 2016/11/27.
 */
public  class MyHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        // TODO Auto-generated method stub
        return true;
    }

}