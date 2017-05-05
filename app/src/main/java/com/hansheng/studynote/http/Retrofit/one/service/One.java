package com.hansheng.studynote.http.Retrofit.one.service;

import com.hansheng.studynote.http.Retrofit.one.model.hpid;
import com.hansheng.studynote.http.Retrofit.one.model.movie;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hansheng on 2016/7/7.
 */
public interface One {
    @GET("movie/list/0")
    Observable<movie> getMovie();

    @GET("{type}/idlist/0")
    Observable<hpid> getId(@Path("type")String type);


}
