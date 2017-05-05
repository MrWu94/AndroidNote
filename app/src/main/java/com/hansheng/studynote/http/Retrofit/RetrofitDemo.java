package com.hansheng.studynote.http.Retrofit;

import com.hansheng.studynote.http.Retrofit.entity.MoiveEntity;
import com.hansheng.studynote.http.Retrofit.entity.MoiveEntity.SubjectsBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by hansheng on 2016/7/1.
 */
public class RetrofitDemo {

   private static String baseUrl="https://api.douban.com/v2/movie/";

    public static void main(String... args){
//        getMoive();
        getMoive1();
    }


    public interface MoviceService{
        @GET("top250")
        Call<MoiveEntity> getTopMoive(@Query("start")int start, @Query("count") int count);
    }

    private static void getMoive() {


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviceService movieService=retrofit.create(MoviceService.class);

        Call<MoiveEntity> call=movieService.getTopMoive(0,10);
        call.enqueue(new Callback<MoiveEntity>() {
            @Override
            public void onResponse(Call<MoiveEntity> call, Response<MoiveEntity> response) {
                MoiveEntity moive=response.body();
                List<SubjectsBean> subject=moive.getSubjects();
                SubjectsBean subjectbean=subject.get(0);
                System.out.println("getTopMoive"+moive.getTitle()+subjectbean.getAlt());
            }

            @Override
            public void onFailure(Call<MoiveEntity> call, Throwable t) {

            }
        });
    }

    public interface MoiveService{
        @GET("top250")
        Observable<MoiveEntity> getTopMoive(@Query("start") int start, @Query("count") int count);
    }

    public static void getMoive1() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        MoiveService moiveService=retrofit.create(MoiveService.class);

        moiveService.getTopMoive(0,10)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MoiveEntity>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("hh");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());

                    }

                    @Override
                    public void onNext(MoiveEntity moiveEntity) {
                        System.out.println("moiveentity="+moiveEntity.getTitle());

                    }
                });

    }



}
