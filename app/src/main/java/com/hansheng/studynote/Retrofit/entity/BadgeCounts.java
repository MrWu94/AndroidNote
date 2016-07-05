package com.hansheng.studynote.Retrofit.entity;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 * Created by hansheng on 2016/7/5.
 */
public class BadgeCounts {

    private int bronze;
    private int silver;
    private int gold;

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
