package com.hansheng.studynote.BasicTransition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/10/3.
 */

public class TransitionActivity extends AppCompatActivity {

    private static final String TAG="TransitionActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_main);
        if(savedInstanceState==null){
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            BasicTransitionFragment fragment = new BasicTransitionFragment();
            fragmentTransaction.replace(R.id.sample_content_fragment, fragment);
            fragmentTransaction.commit();

        }
    }
}
