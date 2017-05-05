package com.hansheng.studynote.http.Retrofit.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hansheng on 2016/7/5.
 */
public class GankMeiResult {
    public boolean error;

    @SerializedName("results")
    public List<GankMeiziInfo> gankMeizis;
}
