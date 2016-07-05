package com.hansheng.studynote.Retrofit.entity;

import java.util.List;

/**
 * Created by hansheng on 2016/7/5.
 */
public class Gank {

    public boolean error;

    public List<GankInfo> results;


    public class GankInfo
    {

        public String _id;

        public String createdAt;

        public String desc;

        public String publishedAt;

        public String source;

        public String type;

        public String url;

        public boolean used;

        public String who;
    }
}
