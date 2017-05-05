package com.hansheng.studynote.androidutils;

import java.util.HashMap;

/**
 * Created by hansheng on 2016/7/10.
 */
public class ApiParams extends HashMap<String,String> {
    private static final long serialVersionUID = 8112047472727256876L;
    public ApiParams with(String key,String value){
        put(key,value);
        return this;
    }
}
