package com.hansheng.linkblockqueue;

import java.util.UUID;

/**
 * Created by hansheng on 16-9-27.
 */

public class CommodityObj {

    private String objId;

    public CommodityObj() {
        this.objId = UUID.randomUUID().toString();
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    @Override
    public String toString() {
        return "Obj{" +
                "objId='" + objId + '\'' +
                '}';
    }
}
