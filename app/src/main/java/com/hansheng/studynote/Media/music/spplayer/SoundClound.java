package com.hansheng.studynote.Media.music.spplayer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hansheng on 2016/7/20.
 */
public class SoundClound {

//    Retrofit retrofit=new Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//
//    MoviceService movieService=retrofit.create(MoviceService.class);


    private static final Retrofit REST_ADAPTER=new Retrofit.Builder()
            .baseUrl(Config.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final SCService SERVICE=REST_ADAPTER.create(SCService.class);

//    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder().setEndpoint(Config.API_URL).build();
//    private static final SCService SERVICE = REST_ADAPTER.create(SCService.class);

    public static SCService getService() {
        return SERVICE;
    }
}
