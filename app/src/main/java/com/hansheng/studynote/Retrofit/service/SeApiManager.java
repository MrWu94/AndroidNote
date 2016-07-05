package com.hansheng.studynote.Retrofit.service;

import com.hansheng.studynote.Retrofit.entity.User;
import com.hansheng.studynote.Retrofit.entity.UsersResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hansheng on 2016/7/5.
 */
public class SeApiManager {

//    private final StackExchangeService mStackExangeService;
//    public SeApiManager(){
//        Retrofit  retrofit=new Retrofit.Builder()
//                .baseUrl("https://api.stackexchange.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        mStackExangeService=retrofit.create(StackExchangeService.class);
//
//    }
//    public Observable<List<User>> getTenMostPopularSOusers() {
//        return  mStackExangeService.getTenMostPopularSOusers()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    public static final String BASE_URL = "https://api.stackexchange.com";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private final StackExchangeService mStackExangeService;


    public SeApiManager() {

        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpclientBuilder = new OkHttpClient.Builder();
        httpclientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpclientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mStackExangeService=retrofit.create(StackExchangeService.class);
    }

    private static final class SingletonHolder{
        private static final SeApiManager INSTANCE=new SeApiManager();
    }
    //获取单例
    public static SeApiManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getUser(Subscriber<User> subscriber,int howmany){
        mStackExangeService.getMostPopularSOusers(howmany)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    public void getUserResponse(Subscriber<UsersResponse> subscriber){
        mStackExangeService.getTenMostPopularSOusers()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
