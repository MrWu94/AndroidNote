package com.hansheng.studynote.fragment.FragmentSecure;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;
import com.hansheng.studynote.fragment.DisplayFragment;

/**
 * Created by hansheng on 17-1-4.
 */

public class FragmentSecureActivity extends AppCompatActivity {
    private DisplayFragment displayFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentmanager_main);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (savedInstanceState != null) {
            displayFragment = (DisplayFragment) fragmentManager.findFragmentByTag("displayFragment");
        } else {
            displayFragment = new DisplayFragment();
            fragmentTransaction.add(R.id.framelayout,displayFragment,"displayFragment");
        }


    }
}
