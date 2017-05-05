package com.hansheng.studynote.http.Retrofit.service;

import com.hansheng.studynote.http.Retrofit.entity.Gank;
import com.hansheng.studynote.http.Retrofit.entity.GankMeiResult;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hansheng on 2016/7/5.
 */
public interface GankApi {

    @GET("data/{type}/{number}/{page}")
    Observable<Gank> getGankDatas(@Path("type") String type, @Path("number") int number, @Path("page") int page);

    @GET("data/福利/{number}/{page}")
    Observable<GankMeiResult> getGankMeizi(@Path("number") int number, @Path("page") int page);


    @POST("add2gank")
    Call<ResponseBody> postGank(@Body RequestBody requestBody);
}
