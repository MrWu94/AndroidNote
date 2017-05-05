package com.hansheng.studynote.sqlite.searchview;

/**
 * 搜索记录的操作对象
 * Created by Administrator on 2016-11-20.
 */

public class History {
    /**
     * id 主键，自增
     */
    private int id;
    /**
     * 搜索的内容
     */
    private String content;
    /**
     * 搜索的时间
     */
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
