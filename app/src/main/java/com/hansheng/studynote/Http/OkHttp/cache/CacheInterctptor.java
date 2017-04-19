package com.hansheng.studynote.Http.OkHttp.cache;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hansheng on 16-12-28.
 * 服务器不支持缓存
 * <p>
 * 如果服务器不支持缓存就可能没有指定这个头部，或者指定的值是如no-store等，但是我们还想在本地使用缓存的话要怎么办呢？
 * 这种情况下我们就需要使用Interceptor来重写Respose的头部信息，从而让okhttp支持缓存。
 * 如下所示，我们重写的Response的Cache-Control字段
 */

public class CacheInterctptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        Response response1 = response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache_Control")
                .header("Cache-Control", "max-age" + 3600 * 24 * 30)
                .build();

        return response1;
    }
}
