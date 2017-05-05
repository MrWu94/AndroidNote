package com.hansheng.studynote.activity.thrid.logger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

import java.util.List;

/**
 * Created by hansheng on 17-4-13.
 */

public class LoggerActvity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_main);
        RequestManager.get(getName(), "http://gank.io/api/data/休息视频/1/1", true, new CallBack<List<GanHuo>>() {
            @Override
            public void onSuccess(List<GanHuo> result) {
            }
        });

        RequestManager.get(getName(), "http://gank.io/api/data/福利/1/1", true, new CallBack<List<GanHuo>>() {
            @Override
            public void onSuccess(List<GanHuo> result) {

            }
        });

    }

    protected String getName() {
        return LoggerActvity.class.getName();
    }


}
