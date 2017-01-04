package com.hansheng.studynote.fragment.FragmentInstance;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-1-4.
 * Fragment 类提供有startActivityForResult()方法用于 Activity 间的页面跳转和数据回传，其实内部也是调用 Activity 的对应方法。
 * 但是在页面返回时需要注意 Fragment 没有提供 setResult() 方法，可以通过宿主 Activity 实现。
 */

public class FragmentInstanceActivity extends Fragment {

    private View view = null;
    private Context context;

    public static FragmentInstanceActivity newInstance() {

        Bundle args = new Bundle();

        FragmentInstanceActivity fragment = new FragmentInstanceActivity();
        args.putString("hansheng", "hansheng");
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * getActivity() 引用问题
     * 可能会出现内存泄露问题
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_1, container, false);
        if (getArguments() != null) {
            getArguments().get("hansheng");
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }
}
