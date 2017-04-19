package com.hansheng.studynote.Http.Retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;
import com.hansheng.studynote.Http.Retrofit.entity.Gank;
import com.hansheng.studynote.Http.Retrofit.entity.GankManager;
import com.hansheng.studynote.Http.Retrofit.entity.HttpResult;
import com.hansheng.studynote.Http.Retrofit.entity.MoiveEntity;
import com.hansheng.studynote.Http.Retrofit.entity.Subject;
import com.hansheng.studynote.Http.Retrofit.entity.User;
import com.hansheng.studynote.Http.Retrofit.entity.User.ItemsBean;
import com.hansheng.studynote.Http.Retrofit.service.SeApiManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hansheng on 2016/7/1.
 */
public class RxJavaAndRetrofit extends AppCompatActivity {


    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result_TV)
    TextView resultTV;

    private SubscriberOnNextListener getTopMoiveOnNext;
    String baseUrl="https://api.douban.com/v2/movie/";
    Subscriber<MoiveEntity> subscriber;
    Subscriber<HttpResult<List<Subject>>> subscriber1;
    Subscriber<List<Subject>> subscriber2;

    Subscriber<User> subscriber3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_retrofit_layout);
        ButterKnife.bind(this);

        getTopMoiveOnNext=new SubscriberOnNextListener<List<Subject>>() {

            @Override
            public void onNext(List<Subject> subjects) {
                resultTV.setText(subjects.toString());
            }
        };
    }

    @OnClick(R.id.click_me_BN)
    public void onClick(){
//        getMoive3();
//        HttpMethods.getInstance().getTopMovie1(new ProgressSubscriber<List<Subject>>(getTopMoiveOnNext,RxJavaAndRetrofit.this),0,10);
       getGank();
    }
    public interface MovieService {
        @GET("top250")
        Observable<MoiveEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
        @GET("top250")
        Observable<HttpResult<List<Subject>>> getTopMoive(@Query("start") int start, @Query("count") int count);
        @GET("top250")
        Observable<HttpResult<List<Subject>>> getTopMoive1(@Query("start") int start, @Query("count") int count);
    }


    //进行网络请求
    private void getMovie(){
        String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        movieService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoiveEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RxJavaAndRetrofit.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        resultTV.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(MoiveEntity movieEntity) {
                        resultTV.setText(movieEntity.toString());
                    }
                });

    }

    public void getMovie1(){
        subscriber=new Subscriber<MoiveEntity>() {
            @Override
            public void onCompleted() {
                Toast.makeText(RxJavaAndRetrofit.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(MoiveEntity moiveEntity) {
                resultTV.setText(moiveEntity.toString());
            }
        };
        HttpMethods.getInstance().getTopMovie(subscriber,0,10);

    }
    public void getMovie2(){
        subscriber1=new Subscriber<HttpResult<List<Subject>>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(RxJavaAndRetrofit.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(HttpResult<List<Subject>> listHttpResult) {
                resultTV.setText(listHttpResult.toString());
            }
        };
        HttpMethods.getInstance().getTopMoive(subscriber1,0,10);

    }

    public void getMoive3(){
        subscriber2=new Subscriber<List<Subject>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(RxJavaAndRetrofit.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(List<Subject> subjects) {
                resultTV.setText(subjects.toString());
            }
        };
        HttpMethods.getInstance().getTopMovie1(subscriber2,0,10);

    }

    public void getUser(){
        subscriber3=new Subscriber<User>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(User user) {
                List<ItemsBean> item=user.getItems();
                ItemsBean itemsBean=item.get(0);
                resultTV.setText(itemsBean.getAge());
            }
        };
        SeApiManager.getInstance().getUser(subscriber3,2);

    }

    public void getGank(){

        GankManager.GankManager().getGankDatas("Android",30,1)
                .filter(new Func1<Gank, Boolean>() {
                    @Override
                    public Boolean call(Gank gank) {
                        return !gank.error;
                    }
                })
                .map(new Func1<Gank, List<Gank.GankInfo>>() {
                    @Override
                    public List<Gank.GankInfo> call(Gank gank) {
                        return gank.results;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Action1<List<Gank.GankInfo>>() {
                    @Override
                    public void call(List<Gank.GankInfo> gankInfos) {
                        Gank.GankInfo gank = gankInfos.get(0);
                        resultTV.setText(gank.getUrl());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

}
