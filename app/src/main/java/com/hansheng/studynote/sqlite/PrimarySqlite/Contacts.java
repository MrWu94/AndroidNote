package com.hansheng.studynote.sqlite.PrimarySqlite;

/**
 * 常量
 * Created by Administrator on 2016-11-19.
 */

public class Contacts {

    public static final String TAG = "SQLite";
    /**
     * 数据库表名
     */
    public static final String DATABASE_NAME = "info.db";
    public static final String DATABASE_NAME2 = "info2.db";
    public static final String DATABASE_NAME3 = "info3.db";
    /**
     * 数据库版本号
     */
    public static final int DATABASE_VERSION = 1;

    /***
     * 表名
     */
    public static final String TABLE_NAME = "user";

    /**
     * 数据表user表结构字段
     * 主键：id
     * 名字：name
     * 年龄：age:
     */
    public static class TABLE_USER {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AGE = "age";
    }
}
