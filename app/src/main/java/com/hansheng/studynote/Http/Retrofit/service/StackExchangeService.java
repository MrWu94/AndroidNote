package com.hansheng.studynote.Http.Retrofit.service;

import com.hansheng.studynote.Http.Retrofit.entity.User;
import com.hansheng.studynote.Http.Retrofit.entity.UsersResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hansheng on 2016/7/5.
 */
public interface StackExchangeService {

    @GET("/2.2/users?order=desc&pagesize=10&sort=reputation&site=stackoverflow")
    Observable<UsersResponse> getTenMostPopularSOusers();

    @GET("/2.2/users?order=desc&sort=reputation&site=stackoverflow")
    Observable<User> getMostPopularSOusers(@Query("pagesize") int howmany);
}
