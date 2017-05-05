package com.hansheng.studynote.media.music.spplayer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hansheng on 2016/7/20.
 */
public interface SCService {

    @GET("/tracks?client_id=" + Config.CLIENT_ID)
    Call<List<Track>> getRecentTracks(@Query("created_at[from]") String date);

//    @GET("/tracks?client_id=" + Config.CLIENT_ID)
//    public void searchTracks(@Query("q") String query, Callback<List<Track>> cb);

}
