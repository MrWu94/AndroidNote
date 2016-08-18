package com.hansheng.hanshenghttpclient.net;

/**
 * Created by hansheng on 2016/8/18.
 */
public class URLEntity {
    private String key; //apiKey
    private long expires; //缓存时间
    private HttpRequest.RequestType netType; //请求方式(GET or POST)
    private String url; //url

    public URLEntity() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public HttpRequest.RequestType getNetType() {
        return netType;
    }

    public void setNetType(HttpRequest.RequestType netType) {
        this.netType = netType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
