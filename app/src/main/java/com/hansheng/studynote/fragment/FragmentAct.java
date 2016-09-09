package com.hansheng.studynote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-9.
 */

public class FragmentAct extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frgament_dynamic);
        Display display=getWindowManager().getDefaultDisplay();
        if(display.getWidth()>display.getHeight()){
            fragment fragment=new fragment();
            getFragmentManager().beginTransaction().replace(R.id.main_layout,fragment).commit();
        }
        else{
            fragment1 fragment1=new fragment1();
            getFragmentManager().beginTransaction().replace(R.id.main_layout,fragment1).commit();
        }
    }
}
