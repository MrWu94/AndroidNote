package com.hansheng.studynote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-9.
 * 用法非常简单，直接在Fragment类中重写上述方法即可。
 * 当isVisibleToUser=true的时候，执行相应的操作，例如：开辟线程和后台服务器进行交互获取数据，
 * 进行界面数据加载显示等，或者进行数据统计，统计当前Fragment打开过几次等等。
 * 当isVisibleToUser= false的时候，说明当前Fragment不可见，大家可以根据需求执行响应的操作。
 */

public class fragment extends Fragment {

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //可见时执行的操作
        } else {
            //不可见时执行的操作
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }
}
