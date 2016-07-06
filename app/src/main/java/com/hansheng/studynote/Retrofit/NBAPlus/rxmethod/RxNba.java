package com.hansheng.studynote.Retrofit.NBAPlus.rxmethod;

import com.hansheng.studynote.Retrofit.NBAPlus.Model.Games;
import com.hansheng.studynote.Retrofit.NBAPlus.Model.News;
import com.hansheng.studynote.Retrofit.NBAPlus.network.NbaplusFactory;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by hansheng on 2016/7/5.
 */
public class RxNba {

    public static void main(String... args){

            getTeams("2016-01-09");
        getNews("news");
    }

    public static void getTeams(String date){
        NbaplusFactory.getNbaplusInstance()
                .getGames(date)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
                .subscribe(new Action1<Games>() {
                    @Override
                    public void call(Games games) {
                        System.out.println(games.getGames());

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    public static void getNews(String type){
        NbaplusFactory.getNbaplusInstance()
                .updateNews(type)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<News>() {
                    @Override
                    public void call(News news) {
                        List<News.NewslistEntity> newslistEntities=news.getNewslist();
                        News.NewslistEntity newslistEntity=newslistEntities.get(0);
                        System.out.println(newslistEntity.getDescription());
                    }
                })
                .subscribe(new Action1<News>() {
                    @Override
                    public void call(News news) {
                        List<News.NewslistEntity> newslistEntities=news.getNewslist();
                        News.NewslistEntity newslistEntity=newslistEntities.get(0);
                        System.out.println(newslistEntity.getDescription());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }
}
