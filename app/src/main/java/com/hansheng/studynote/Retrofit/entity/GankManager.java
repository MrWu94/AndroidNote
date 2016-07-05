package com.hansheng.studynote.Retrofit.entity;

import com.hansheng.studynote.Retrofit.service.GankApi;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hansheng on 2016/7/5.
 */
public class GankManager {

    public static final String BASE_GANK_URL = "http://gank.io/api/";

    public static final String BASE_POST_GANK_URL = "https://gank.io/api/add2gank";

    private static final int DEFAULT_TIMEOUT = 5;

    private static Retrofit retrofitGet;

    private static GankApi gankApi;


    public static GankApi GankManager() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpclientBuilder = new OkHttpClient.Builder();
        httpclientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofitGet=new Retrofit.Builder()
                .client(httpclientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_GANK_URL)
                .build();
      return retrofitGet.create(GankApi.class);

    }

//    private static final class SingletonHolder{
//        private static final GankManager INSTANCE=new GankManager();
//    }
//    public static GankManager getInstance(){
//        return SingletonHolder.INSTANCE;
//    }
//     private List<String> types = Arrays.asList("Android", "iOS", "休息视频", "福利", "拓展资源", "前端", "瞎推荐", "App");
//    private int pageNum = 30;
//    private int page = 1;


    public void getGank(Func1<Gank,Boolean> fun, Func1<Gank,Gank.GankInfo> fun1, Action1<List<Gank.GankInfo>> action, Action1<Throwable> throwable, String type, int number, int page){
        gankApi.getGankDatas(type,number,page)
                .filter(fun)
                .map(fun1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;




    }

    public void getGankMeizi(Subscriber<GankMeiResult> subscriber,int number,int page){
        gankApi.getGankMeizi(number,page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
