package com.hansheng.studynote.Retrofit;

import com.hansheng.studynote.Retrofit.entity.HttpResult;
import com.hansheng.studynote.Retrofit.entity.MoiveEntity;
import com.hansheng.studynote.Retrofit.entity.Subject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.hansheng.studynote.Retrofit.RxJavaAndRetrofit.*;

/**
 * Created by hansheng on 2016/7/1.
 */
public class HttpMethods {
    public static final String BASE_URL="https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT=5;

    private Retrofit retrofit;
    private MovieService movieService;


    public HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpclientBuilder=new OkHttpClient.Builder();
        httpclientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit=new Retrofit.Builder()
                .client(httpclientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService=retrofit.create(MovieService.class);
    }
    //在访问HttpMethods时创建单例
    private static final class  SingletonHolder{
        private static final HttpMethods INSTANCE=new HttpMethods();
    }
    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void getTopMovie(Subscriber<MoiveEntity> subscriber,int start,int count){
        movieService.getTopMovie(start,count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getTopMoive(Subscriber<HttpResult<List<Subject>>> subscriber,int start,int count){
        movieService.getTopMoive(start,count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
