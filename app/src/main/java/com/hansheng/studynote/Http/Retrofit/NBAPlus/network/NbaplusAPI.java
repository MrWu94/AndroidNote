package com.hansheng.studynote.Http.Retrofit.NBAPlus.network;

import com.hansheng.studynote.Http.Retrofit.NBAPlus.Model.Games;
import com.hansheng.studynote.Http.Retrofit.NBAPlus.Model.News;
import com.hansheng.studynote.Http.Retrofit.NBAPlus.Model.Statistics;
import com.hansheng.studynote.Http.Retrofit.NBAPlus.Model.Teams;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hansheng on 2016/7/5.
 */
public interface NbaplusAPI {
    @GET("api/v1.0/{type}/update")
    Observable<News> updateNews(@Path("type") String type);

    @GET("api/v1.0/{type}/loadmore/{newsId}")
    Observable<News> loadMoreNews(@Path("type") String type, @Path("newsId") String newsId );
    @GET("api/v1.0/nbastat/perstat/{statKind}")
    Observable<Statistics> getPerStats(@Path("statKind") String statKind);
    @GET("api/v1.0/teamsort/sort")
    Observable<Teams> getTeamSort();
    @GET("api/v1.0/gamesdate/{date}")
    Observable<Games> getGames(@Path("date") String date);

}
