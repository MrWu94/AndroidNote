package com.hansheng.studynote.fragment.add;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hansheng.studynote.R;
import com.hansheng.studynote.fragment.SoundFragment;
import com.hansheng.studynote.fragment.fragment1;

/**
 * Created by hansheng on 16-12-1.
 */

public class FragmentAddActivity extends AppCompatActivity {
    Fragment mTempFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_main);



//        SoundFragment targetFragment;
//        DisplayFragment hideFragment;
//
//        if (savedInstanceState != null) {  // “内存重启”时调用
//            targetFragment = getSupportFragmentManager().findFragmentByTag(SoundFragment.class.getName);
//            hideFragment = getSupportFragmentManager().findFragmentByTag(DisplayFragment.class.getName);
//            // 解决重叠问题
//            getFragmentManager().beginTransaction()
//                    .show(targetFragment)
//                    .hide(hideFragment)
//                    .commit();
//        }else{  // 正常时
//            targetFragment = SoundFragment.newInstance();
//            hideFragment = DisplayFragment.newInstance();
//
//            getFragmentManager().beginTransaction()
//                    .add(container, targetFragment, targetFragment.getClass().getName())
//                    .add(R.id.container,hideFragment,hideFragment.getClass().getName())
//                    .hide(hideFragment)
//                    .commit();
//        }

        mTempFragment = new SoundFragment();
        getFragmentManager().beginTransaction().add(R.id.frame, mTempFragment).commit();
    }

    public void switch1(View view) {
        mTempFragment = new SoundFragment();
        switchFragment(mTempFragment);

    }
    public void switcha(View view){
        Fragment fragment=new fragment1();
        switchFragment(fragment);
    }

    private void switchFragment(Fragment fragment) {
        if (fragment != mTempFragment) {
            if (!fragment.isAdded()) {
                getFragmentManager().beginTransaction().hide(mTempFragment)
                        .add(R.id.frame, fragment).commit();
            } else {
                getFragmentManager().beginTransaction().hide(mTempFragment)
                        .show(fragment).commit();
            }
            mTempFragment = fragment;
        }
    }
}
