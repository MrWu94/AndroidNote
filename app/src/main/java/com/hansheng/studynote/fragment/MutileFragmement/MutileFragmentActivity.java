package com.hansheng.studynote.fragment.MutileFragmement;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-16.
 */

public class MutileFragmentActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mutile_fragment);
//        toolbar= (Toolbar) findViewById(R.id.tool);
//        toolbar.setTitle("FragmentA");
//        setSupportActionBar(toolbar);
        Fragment fragment = new FragmentA();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }
    @Override
    public void onBackPressed() {
        FragmentManager fm = this.getFragmentManager();

        if (fm.getBackStackEntryCount() == 0) {
            System.out.println("====="+fm.getBackStackEntryCount());
            this.finish();
        } else {
            System.out.println("=====---");
            fm.popBackStack();
        }
    }

}
