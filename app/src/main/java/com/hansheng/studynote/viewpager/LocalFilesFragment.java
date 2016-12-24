package com.hansheng.studynote.viewpager;

import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-21.
 */

public class LocalFilesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_test1,container,false);
    }
}

