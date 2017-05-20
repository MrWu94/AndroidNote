package com.hansheng.studynote.material.material;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.hansheng.studynote.R;
import com.hansheng.studynote.ui.activity.BaseActivity;

/**
 * Created by hansheng on 17-5-19.
 */

public class NavationActivity extends BaseActivity {
    private NavigationView navigationView;
    @Override
    protected int initContentView() {
        return R.layout.acticity_navation;
    }

    @Override
    protected void initView() {
        navigationView= (NavigationView) findViewById(R.id.navation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navation_item_home:
                        break;

                }
                return false;
            }
        });

    }
}
