package com.hansheng.studynote.Http.OkHttp.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hansheng.studynote.Http.OkHttp.entity.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 2016/7/6.
 */
public class NewsJsonUtils {

    private static final String TAG="NewsJsonUtils";

    public static List<NewsBean> readJsonNewsBeans(String res,String value){
        List<NewsBean> beans=new ArrayList<NewsBean>();

        JsonParser parser=new JsonParser();
        JsonObject jsonObject=parser.parse(res).getAsJsonObject();
        JsonElement jsonElement=jsonObject.get(value);
        if(jsonElement==null){
            return null;
        }
        JsonArray jsonArray=jsonElement.getAsJsonArray();
        for(int i=1;i<jsonArray.size();i++){
            JsonObject jo=jsonArray.get(i).getAsJsonObject();
            if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
                continue;
            }
            if (jo.has("TAGS") && !jo.has("TAG")) {
                continue;
            }

            if (!jo.has("imgextra")) {
                NewsBean news = JsonUtils.deserialize(jo, NewsBean.class);
                beans.add(news);
            }
        }
        return beans;

    }
}
