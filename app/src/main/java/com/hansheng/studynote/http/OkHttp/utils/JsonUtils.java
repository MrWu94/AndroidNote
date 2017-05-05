package com.hansheng.studynote.http.OkHttp.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

/**
 * Created by hansheng on 2016/7/6.
 */
public class JsonUtils {

    private static Gson  gson=new Gson();

    public static <T> String serialize(T object){
        return gson.toJson(object);
    }

    public static <T> T deserialize(String json,Class<T> clz){
        return gson.fromJson(json,clz);
    }

    public static <T> T deserialize(JsonObject jsonobject, Class<T> clz){
        return gson.fromJson(jsonobject,clz);
    }

    public static <T> T deserialize(String json,Type type){
        return gson.fromJson(json,type);
    }
}
