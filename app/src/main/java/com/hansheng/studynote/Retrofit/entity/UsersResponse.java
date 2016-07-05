package com.hansheng.studynote.Retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by hansheng on 2016/7/5.
 */
public class UsersResponse {
    private List<User> users;
}
