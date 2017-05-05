package com.hansheng.studynote.http.Retrofit;

import com.hansheng.studynote.http.Retrofit.entity.HttpResult;
import com.hansheng.studynote.http.Retrofit.entity.MoiveEntity;
import com.hansheng.studynote.http.Retrofit.entity.Subject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.hansheng.studynote.http.Retrofit.RxJavaAndRetrofit.*;

/**
 * Created by hansheng on 2016/7/1.
 */
public class HttpMethods {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MovieService movieService;


    public HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpclientBuilder = new OkHttpClient.Builder();
        httpclientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpclientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(MovieService.class);
    }

    //在访问HttpMethods时创建单例
    private static final class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getTopMovie(Subscriber<MoiveEntity> subscriber, int start, int count) {
        movieService.getTopMovie(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getTopMoive(Subscriber<HttpResult<List<Subject>>> subscriber, int start, int count) {
        movieService.getTopMoive(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getTopMovie1(Subscriber<List<Subject>> subscriber, int start, int count) {
        movieService.getTopMoive1(start, count)
                .flatMap(new Func1<HttpResult<List<Subject>>, Observable<List<Subject>>>() {
                    @Override
                    public Observable<List<Subject>> call(HttpResult<List<Subject>> moiveEntity) {
                        return flatResult(moiveEntity);
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private <T> Observable<T> flatResult(final HttpResult<T> subjects) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (subjects.getCount() == 0) {
                    subscriber.onError(new ApiException(ApiException.USER_NOT_EXIST));

                } else {
                    subscriber.onNext(subjects.getSubjects());
                }
                subscriber.onCompleted();
            }
        });
    }


}
