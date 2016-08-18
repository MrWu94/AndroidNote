package com.hansheng.hanshenghttpclient.net;

import android.os.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * Created by hansheng on 2016/8/18.
 */
public class HttpRequest implements Runnable {


    enum RequestType {
        GET, POST
    }

    private RequestManager hostManager;

    private URLEntity urlnfo;

    private List<RequestParameter> params;

    private RequestCallback callback;

    private Handler handler;
    private URL mURL;

    private HttpURLConnection  mConnection;
    private Boolean interrupted = false;

    public HttpRequest(RequestManager hostManager, URLEntity urlnfo, List<RequestParameter> params, RequestCallback callback) {
        this.hostManager = hostManager;
        this.urlnfo = urlnfo;
        this.params = params;
        this.callback = callback;
        handler = new Handler();
    }

    @Override
    public void run() {
        switch (urlnfo.getNetType()) {
            case GET:
                String trulyURL;
                if (params != null && !params.isEmpty()) {
                    StringBuilder urlBuilder = new StringBuilder(urlnfo.getUrl());
                    urlBuilder.append("?").append(convertParam2String());
                    trulyURL = urlBuilder.toString();
                } else {
                    trulyURL = urlnfo.getUrl();
                }
                // 正式发送GET请求到服务器
                sendHttpGetToServer(trulyURL);
                break;
            case POST:
                // 发送POST请求到服务器
                sendHttpPostToServer(urlnfo.getUrl());
                break;
            default:
                break;
        }
    }


    /**
     * 发起GET请求
     *
     * @param url
     */
    private void sendHttpGetToServer(String url) {
        try {
            mURL = new URL(url);
            mConnection = (HttpURLConnection) mURL.openConnection();
            // 连接服务器的超时时长
            mConnection.setConnectTimeout(5000);
            // 从服务器读取数据的超时时长
            mConnection.setReadTimeout(8000);

            if (mConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 如果未设置请求中断，则进行读取数据的工作
                if (!interrupted) {
                    // read content from response..
                    final String result = readFromResponse(mConnection.getInputStream());
                    // call back
                    if (callback != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(result);
                            }
                        });
                    }
                } else { // 中断请求
                    return;
                }
            } else {
                handleNetworkError("网络异常");
            }
        } catch (MalformedURLException e) {
            handleNetworkError("网络异常");
        } catch (IOException e) {
            handleNetworkError("网络异常");
        } finally {
            hostManager.requests.remove(this);
        }
    }

    /**
     * 发起POST请求
     *
     * @param url
     */
    private void sendHttpPostToServer(String url) {
        try {
            mURL = new URL(url);
            mConnection = (HttpURLConnection) mURL.openConnection();
            // 连接服务器的超时时长
            mConnection.setConnectTimeout(5000);
            // 从服务器读取数据的超时时长
            mConnection.setReadTimeout(8000);
            // 允许输入输出
            mConnection.setDoOutput(true);
            mConnection.setDoInput(true);
            // 向请求体中写入参数
            if (params != null && !params.isEmpty()) {
                String paramString = convertParam2String();
                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(mConnection.getOutputStream()));
                br.write(paramString);
                br.close();
            }

            if (mConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if (!interrupted) {
                    final String result = readFromResponse(mConnection.getInputStream());
                    if (callback != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(result);
                            }
                        });
                    }
                } else {
                    return;
                }
            } else {
                handleNetworkError("网络异常");
            }
        } catch (MalformedURLException e) {
            handleNetworkError("网络异常");
        } catch (IOException e) {
            handleNetworkError("网络异常");
        } finally {
            hostManager.requests.remove(this);
        }
    }

    /**
     * 将请求参数转换为String
     */
    private String convertParam2String() {
        StringBuilder paramsBuilder = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            RequestParameter param = params.get(i);
            paramsBuilder.append(param.getName()).append("=").append(param.getValue());
            if (i < params.size() - 1)
                paramsBuilder.append("&");
        }

        return paramsBuilder.toString();
    }


    /**
     * 从http response中读取响应数据
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private String readFromResponse(InputStream inputStream) throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    /**
     * 异常回调
     *
     * @param errorMsg
     */
    private void handleNetworkError(final String errorMsg) {
        if (callback != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onFail(errorMsg);
                }
            });
        }
    }

    /**
     * 中断请求
     */
    void disconnect() {
        // 设置标志位
        interrupted = true;
        // 如果当前请求正处于与服务器连接状态下，则断开连接
        if (mConnection != null)
            mConnection.disconnect();
    }


}
