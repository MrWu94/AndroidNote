package com.hansheng.studynote.http.Retrofit.NBAPlus.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hansheng on 2016/7/5.
 */
public class NbaplusClient {
    final NbaplusAPI nbaplus;
    final NewsDetileAPI newsDetileAPI;
    public NbaplusClient(){
        Retrofit retrofit0=new Retrofit.Builder()
                .baseUrl("http://nbaplus.sinaapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        nbaplus=retrofit0.create(NbaplusAPI.class);

        Retrofit retrofit1=new Retrofit.Builder()
                .baseUrl("http://reader.res.meizu.com/reader/articlecontent/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        newsDetileAPI=retrofit1.create(NewsDetileAPI.class);
    }

    public NbaplusAPI getClient(){
        return nbaplus;
    }

    public NewsDetileAPI getNewsDetileClient(){
        return newsDetileAPI;

    }
}
