package com.hansheng.simplenet.httpstacks;

import com.hansheng.simplenet.base.Request;
import com.hansheng.simplenet.base.Response;
import com.hansheng.simplenet.config.HttpUrlConnConfig;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicStatusLine;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by hansheng on 2016/8/20.
 */
public class HttpUrlConnStack implements HttpStack {

    HttpUrlConnConfig mConfig=HttpUrlConnConfig.getConfig();


    @Override
    public Response performRequest(Request<?> request) throws Exception {
        HttpURLConnection urlConnection=null;
        // 构建HttpURLConnection
        urlConnection = createUrlConnection(request.getUrl());
        // 设置headers
        setRequestHeaders(urlConnection, request);
        // 设置Body参数
        setRequestParams(urlConnection, request);
        // https 配置
        configHttps(request);
        return fetchResponse(urlConnection);
    }

    private Response fetchResponse(HttpURLConnection connection) throws IOException {
        // Initialize HttpResponse with data from the HttpURLConnection.
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        int responseCode = connection.getResponseCode();
        if (responseCode == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        // 状态行数据
        StatusLine responseStatus = new BasicStatusLine(protocolVersion,
                connection.getResponseCode(), connection.getResponseMessage());
        // 构建response
        Response response = new Response(responseStatus);
        // 设置response数据
        response.setEntity(entityFromURLConnwction(connection));
        addHeadersToResponse(response, connection);
        return response;

    }

    private void addHeadersToResponse(Response response, HttpURLConnection connection) {
        for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
            if (header.getKey() != null) {
                Header h = new BasicHeader(header.getKey(), header.getValue().get(0));
                response.addHeader(h);
            }
        }

    }

    private HttpEntity entityFromURLConnwction(HttpURLConnection connection) {
        BasicHttpEntity entity = new BasicHttpEntity();
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            inputStream = connection.getErrorStream();
        }

        // TODO : GZIP 
        entity.setContent(inputStream);
        entity.setContentLength(connection.getContentLength());
        entity.setContentEncoding(connection.getContentEncoding());
        entity.setContentType(connection.getContentType());

        return entity;
    }

    private void configHttps(Request<?> request) {
        if (request.isHttps()) {
            SSLSocketFactory sslFactory = mConfig.getSslSocketFactory();
            // 配置https
            if (sslFactory != null) {
                HttpsURLConnection.setDefaultSSLSocketFactory(sslFactory);
                HttpsURLConnection.setDefaultHostnameVerifier(mConfig.getHostnameVerifier());
            }

        }
    }

    private void setRequestParams(HttpURLConnection urlConnection, Request<?> request) throws Exception {
        Request.HttpMethod method=request.getHttpMethod();
        urlConnection.setRequestMethod(method.toString());
        byte[] body=request.getBody();
        if(body!=null){
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty(Request.HEADER_CONTENT_TYPE,request.getBodyContentType());
            // write params data to connection
            DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
            dataOutputStream.write(body);
            dataOutputStream.close();
        }
    }

    private void setRequestHeaders(HttpURLConnection urlConnection, Request<?> request) {
        Set<String> headerKeys=request.getHeaders().keySet();
        for(String headerName:headerKeys){
            urlConnection.addRequestProperty(headerName,request.getHeaders().get(headerName));
        }
    }

    private HttpURLConnection createUrlConnection(String url) throws Exception {


            URL newURL=new URL(url);
            URLConnection urlConnection=newURL.openConnection();
            urlConnection.setConnectTimeout(mConfig.connTimeOut);
            urlConnection.setReadTimeout(mConfig.soTimeOut);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            return (HttpURLConnection) urlConnection;

    }
}
