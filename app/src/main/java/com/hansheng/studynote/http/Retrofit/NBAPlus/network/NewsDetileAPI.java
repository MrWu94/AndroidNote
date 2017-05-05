package com.hansheng.studynote.http.Retrofit.NBAPlus.network;

import com.hansheng.studynote.http.Retrofit.NBAPlus.Model.NewsDetile;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hansheng on 2016/7/5.
 */
public interface NewsDetileAPI {
    @GET("{date}/{detileId}")
    Observable<NewsDetile> getNewsDetile(@Path("date") String type, @Path("detileId") String newsId);
}
