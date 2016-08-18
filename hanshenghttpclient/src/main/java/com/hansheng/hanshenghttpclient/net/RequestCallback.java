package com.hansheng.hanshenghttpclient.net;

/**
 * 请求回调接口
 */
public interface RequestCallback
{
    void onSuccess(String content);

    void onFail(String errorMessage);
}
