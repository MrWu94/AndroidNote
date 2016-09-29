package com.hansheng.studynote.recyclerview.data;

import android.support.annotation.DrawableRes;

/**
 * Created by hansheng on 16-9-29.
 */

public class Data {
    private String num;
    private String time;
    @DrawableRes
    private int resImage;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @DrawableRes
    public int getResImage() {
        return resImage;
    }

    public void setResImage(@DrawableRes int resImage) {
        this.resImage = resImage;
    }
}