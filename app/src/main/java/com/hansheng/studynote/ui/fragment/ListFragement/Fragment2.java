package com.hansheng.studynote.ui.fragment.ListFragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-10-14.
 */

public class Fragment2 extends Fragment {

    public static Fragment1 newInstance() {

        Bundle args = new Bundle();

        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("INFO1", "onCreate..");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("INFO1", "onCreateView..");
        return inflater.inflate(R.layout.fragment2, container, false);

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("INFO1", "MyFragment pause...");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("INFO1", "MyFragment Destroy...");

    }
}
