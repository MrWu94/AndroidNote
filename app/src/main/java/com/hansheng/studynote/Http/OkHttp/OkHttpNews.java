package com.hansheng.studynote.Http.OkHttp;

import com.hansheng.studynote.Http.OkHttp.entity.NewsBean;
import com.hansheng.studynote.Http.OkHttp.utils.NewsJsonUtils;
import com.hansheng.studynote.Http.OkHttp.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by hansheng on 2016/7/6.
 */
public class OkHttpNews {
    public static void main(String... args){
        getNews("http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html");

    }
    public static void getNews(String url){

        OkHttpUtils.ResultCallback<String> loadnewsCallback=new OkHttpUtils.ResultCallback<String>(){
            @Override
            public void onSuccess(String response) {
                List<NewsBean> newsBeanList= NewsJsonUtils.readJsonNewsBeans(response,"T1348647909107");
                NewsBean newsBean=newsBeanList.get(0);
                System.out.println(newsBean.getImgsrc());

            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        OkHttpUtils.get(url,loadnewsCallback);

    }
}
