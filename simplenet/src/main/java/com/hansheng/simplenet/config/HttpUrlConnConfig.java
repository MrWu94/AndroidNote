package com.hansheng.simplenet.config;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by hansheng on 2016/8/20.
 */
public class HttpUrlConnConfig extends HttpConfig {

    private static HttpUrlConnConfig sConfig=new HttpUrlConnConfig();

    private SSLSocketFactory mSslSocketFactory=null;
    private HostnameVerifier mHostnameVerifer=null;

    private HttpUrlConnConfig(){}

    public static HttpUrlConnConfig getConfig(){
        return sConfig;
    }

    public void setHttpsConfig(SSLSocketFactory sslSocketFactory,HostnameVerifier hostnameVerifier){
        mSslSocketFactory=sslSocketFactory;
        mHostnameVerifer=hostnameVerifier;
    }

    public HostnameVerifier getHostnameVerifier() {
        return mHostnameVerifer;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return mSslSocketFactory;
    }


}
