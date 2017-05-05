package com.hansheng.studynote.ui.fragment.viewpagerandfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hansheng.studynote.R;

/**
 * Created by wfq on 2016/12/5.
 */

public class Fragment_3 extends Fragment {

    private final String TAG = "Fragment_3";


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        log("setUserVisibleHint="+isVisibleToUser);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        log("   1-->onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("    2-->onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        log("    3-->onCreateView");
        return inflater.inflate(R.layout.fragment_3,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log("   4-->onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        log("   5-->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        log("   6-->onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        log("   7-->onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        log("   8-->onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log("   9-->onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log("   10-->onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        log("   11-->onDetach");
    }

    private void log (String methodName){
        Log.e(TAG,"-------->"+methodName);
    }
}