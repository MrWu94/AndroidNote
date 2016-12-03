package com.hansheng.studynote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-9.
 */
public class SoundFragment extends Fragment {

    //三个一般必须重载的方法
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        System.out.println("NewFragment--onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("NewFragment--onCreateView");
        return inflater.inflate(R.layout.sound_framgent, container, false);

    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        System.out.println("NewFragment--onPause");
    }

}